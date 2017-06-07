package awt;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug164015_G2DDrawImage extends JPanel {
	private static final int TILE_SIZE = 200;

	private static final boolean USE_JAI_FOR_IMAGE_IO = true;

	private int viewport_x = 0, viewport_y = 0;

	/** constructor */
	public Bug164015_G2DDrawImage() {
		this.setDoubleBuffered(false); // this makes the bug show up faster

	}

	/** scroll the viewport */
	public void scroll(int x, int y) {
		viewport_x += x;
		viewport_y += y;
		this.repaint();
	}

	public BufferedImage loadTile(int x, int y) {
		String file_path = "tile_" + x + "_" + y + ".gif";
		BufferedImage bufferedImage = null;

		if (USE_JAI_FOR_IMAGE_IO) {

			try {			
				bufferedImage = ImageIO.read(new File(file_path));
			} catch (IOException e) {
				bufferedImage = null;
			}

		} else { // use AWT
			Image image = new ImageIcon(file_path).getImage();
			if (image.getWidth(null) <= 0 || image.getHeight(null) <= 0) {
				return null;
			}
			bufferedImage = new BufferedImage(image.getWidth(null), image
					.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			bufferedImage.getGraphics().drawImage(image, 0, 0, null);
		}

		return bufferedImage;
	}

	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2D = (Graphics2D) g;

		int firsttile_x = viewport_x - viewport_x % TILE_SIZE;
		int firsttile_y = viewport_y - viewport_y % TILE_SIZE;
		int lasttile_x = firsttile_x
				+ ((int) (this.getWidth() / TILE_SIZE) + 2) * TILE_SIZE;
		int lasttile_y = firsttile_y
				+ ((int) (this.getHeight() / TILE_SIZE) + 2) * TILE_SIZE;

		for (int currtile_y = firsttile_y; currtile_y < lasttile_y; currtile_y += TILE_SIZE) {
			for (int currtile_x = firsttile_x; currtile_x < lasttile_x; currtile_x += TILE_SIZE) {

				BufferedImage currentImage = loadTile(currtile_x, currtile_y);
				if (currentImage != null) {
					System.err.print("drawing graphics.image... ");
					g2D.drawImage(currentImage, currtile_x - viewport_x,
							currtile_y - viewport_y, null);
					System.err.println("done");
				}
				
				g2D.setColor(Color.RED); // outline the tiles
				g2D.drawRect(currtile_x - viewport_x, currtile_y - viewport_y,
						TILE_SIZE, TILE_SIZE);

			}
		}

	}

	public static void main(String[] args) {
		/** create SWT GUI */
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("AWT_SWT Test");

		Composite swtAwtComponent = new Composite(shell, SWT.EMBEDDED);
		java.awt.Frame frame = SWT_AWT.new_Frame(swtAwtComponent);
		final Bug164015_G2DDrawImage myJPanel = new Bug164015_G2DDrawImage();
		frame.add(myJPanel);

		frame.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					myJPanel.scroll(20, 0);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					myJPanel.scroll(-20, 0);
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					myJPanel.scroll(0, 20);
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					myJPanel.scroll(0, -20);
				}
			}

		});

		shell.setLayout(new FillLayout());
		shell.pack();
		shell.open();
		shell.setMaximized(true);

		try {
			while (!shell.isDisposed()) { 
				if (!display.readAndDispatch()) 
					display.sleep(); 
			}

		} finally {
			display.dispose();
			System.err.println("EXITING NORMALLY!!");		
		}
	}

}
