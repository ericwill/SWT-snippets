package widget.tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class Bug476419_TreeWideIcons {
	public static void main(String [] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		
		int W = 22, H = 16;
		final Image xImage = new Image (display, W, H);
		GC gc = new GC(xImage);
		gc.setForeground(display.getSystemColor(SWT.COLOR_RED));
		gc.drawLine(0, 0,     W - 1, H -1);
		gc.drawLine(0, H - 1, W - 1, 0);
		gc.drawOval(1, 1,     W - 2, H - 2);
		gc.dispose();
		
		final Tree tree = new Tree(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		TreeItem lastItem = new TreeItem(tree, SWT.NONE);
		lastItem.setImage(xImage);
		lastItem.setText("root item");
		
		for (int i = 0; i < 3; i++) {
			TreeItem newItem = new TreeItem(lastItem, SWT.NONE);
			newItem.setText("descendant A" + i);
			newItem.setImage(xImage);
			
			newItem = new TreeItem(lastItem, SWT.NONE);
			newItem.setText("descendant B" + i);
			newItem.setImage(xImage);
			
			lastItem.setExpanded(true);
			lastItem = newItem;
		}

//		widget.tree.addListener(SWT.PaintItem, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				TreeItem item = (TreeItem)event.item;
//				Rectangle ib = item.getImageBounds(0);
//				You can add an offset here to make the issue more visible:
//				event.gc.drawImage(xImage, ib.x, ib.y + 10);
//				event.gc.drawImage(xImage, ib.x, ib.y);
				
//				Rectangle tb = item.getTextBounds(0);
//				You can add an offset here to make the issue more visible:
//				event.gc.drawText(item.getText(), tb.x + 10, tb.y);
//				event.gc.drawText(item.getText(), tb.x, tb.y);
//			}
//		});

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		xImage.dispose();
		display.dispose();
	}

}
