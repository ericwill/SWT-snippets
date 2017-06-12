package widget.shell;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class Bug404946_GetActiveShellBug {
	
	public static void main (String [] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.open();
		System.out.println(display.getActiveShell() == shell);
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}