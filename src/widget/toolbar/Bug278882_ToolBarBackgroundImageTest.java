package widget.toolbar;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class Bug278882_ToolBarBackgroundImageTest {
	
	public static void main (String [] args) {
	
	final Display display = new Display();
	Shell shell = new Shell( display );
	//widget.shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
	shell.setLayout(new FillLayout());

	final ToolBar toolBar = new ToolBar(shell, SWT.FLAT);
	GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
	toolBar.setLayoutData(data);
	toolBar.addListener(SWT.Resize, new Listener() {
		public void handleEvent(Event event) {
			Image originalImage = toolBar.getBackgroundImage();

			Point p = toolBar.getSize();
			Image bg = new Image(display, p.x, p.y);
			
			GC gc = new GC(bg);
			gc.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
			gc.fillRectangle(0, 0, p.x, p.y);
			gc.dispose();
			
			toolBar.setBackgroundImage(bg);
			
			if (originalImage != null) {
				originalImage.dispose();
			}
		}	    	
	});
	shell.setSize( 400, 300 );
	shell.open();

	while( !shell.isDisposed() ) {
	  if( !display.readAndDispatch() )
	    display.sleep();
	}
	display.dispose();
	}
}
