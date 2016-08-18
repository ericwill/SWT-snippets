package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class Bug249689_MeasureEventWrongBounds {

	public static void main(String[] args) {
	    final Display display = new Display();
	    Image image = new Image(display, "/home/ericwill/downloads/yrv9.png");
	    Shell shell = new Shell(display);
	    shell.setBounds(10,10,800,200);
	    shell.setLayout(new FillLayout());
	    final Table table = new Table(shell, SWT.CHECK);
	    TableItem item = new TableItem(table, SWT.NONE);
	    item.setText(new String[] {"a","a2"});
	    table.addListener(SWT.MeasureItem, new Listener() {
	        public void handleEvent(Event event) {
	            event.gc.setForeground(display.getSystemColor(SWT.COLOR_RED));
	            event.gc.drawRectangle(event.x, event.y, event.width, event.height);
	        }
	    });
	    shell.open();
	    while (!shell.isDisposed()) {
	        if (!display.readAndDispatch()) display.sleep();
	    }
	    image.dispose();
	    display.dispose();
	}
}
