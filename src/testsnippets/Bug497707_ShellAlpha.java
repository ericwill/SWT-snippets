package testsnippets;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug497707_ShellAlpha {

	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell(display);
		// Set alpha to whatever
		int alpha = 128;
		shell.setAlpha(alpha);
		System.out.println("Alpha is set to " + alpha);
		System.out.println("Shell.getAlpha() returns: " + shell.getAlpha());
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}
