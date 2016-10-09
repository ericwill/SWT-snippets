package label;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;

public class Bug205199_LabelSetImageNull
{	
	public static void main(String[] args)
	{	
		new Bug205199_LabelSetImageNull();
	}

	private Image image;
	private Label label;
	private int state=0;

	public Bug205199_LabelSetImageNull()
	{	
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout( new FillLayout());

		// Create an image
		this.image = new Image (display, 16, 16);
		Color color = display.getSystemColor (SWT.COLOR_RED);
		GC gc = new GC (image);
		gc.setBackground (color);
		gc.fillRectangle (image.getBounds());
		gc.dispose ();

		this.label = new Label(shell,SWT.NONE);
		Button button = new Button(shell,SWT.PUSH);
		button.setText("click Me");
		button.addSelectionListener(
			new SelectionAdapter()
			{
				public void widgetSelected(SelectionEvent e)
				{
					if (state == 0)
					{
						label.setImage(image);
						label.setText("1. text");
					}
					else if (state == 1)
					{
						label.setText("2. text");
						label.setImage(image);
					}
					else if (state == 2)
					{
						label.setText("3. text");
						label.setImage(null);
					}
					state++;
				}
			}
		);

		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				 display.sleep();
	 }
}