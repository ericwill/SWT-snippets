package widget.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tracker;

public class Bug302961_TableItemTrackerUpdating {

	public static void main (String [] args) {
	    Display display = new Display ();
	    final Shell shell = new Shell (display);
	    final Label label = new Label(shell, SWT.NONE);
	    label.setBounds(10,10,100,30);
	    label.setText("widget.label");
	    Table table = new Table(shell, SWT.NONE);
	    table.setBounds(10,50,100,50);
	    final TableItem item = new TableItem(table, SWT.NONE);
	    shell.open ();
	    shell.addListener (SWT.MouseDown, new Listener () {
	        public void handleEvent (Event e) {
	            Tracker tracker = new Tracker (shell, SWT.NONE);
	            tracker.setRectangles (new Rectangle [] {
	             new Rectangle (e.x, e.y, 100, 100),
	            });
	            tracker.addListener(SWT.Move, new Listener() {
	                public void handleEvent(Event event) {
	                    System.out.println(event.x);
	                    String string = String.valueOf(event.x);
	                    label.setText(string);
	                    item.setText(string);
	                }
	            });
	            tracker.open ();
	        }
	    });
	    while (!shell.isDisposed()) {
	        if (!display.readAndDispatch ()) display.sleep ();
	    }
	    display.dispose ();
	}
	
}
