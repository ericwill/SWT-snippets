package z_unsorted;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Bug62247_KDEToolTip {


public static void main (String [] args) {
	Display display = new Display ();
	Shell shell = new Shell (display);
	shell.setLayout(new FillLayout());
	Composite c = new Composite(shell, SWT.NONE);
	c.addListener(SWT.Paint, new Listener() {
		public void handleEvent(Event e) {
			System.out.println("Paint");
		}
	});
	shell.open ();
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
	}
	display.dispose ();
}
}
