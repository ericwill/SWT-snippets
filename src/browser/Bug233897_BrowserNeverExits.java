package browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug233897_BrowserNeverExits {

	public static void main(String[] args) {
		Display display = new Display ();
		Shell shell = new Shell(display);
		Browser browser = new Browser( shell, SWT.None );
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) 
				display.sleep ();
		}
		display.dispose ();
		System.out.println("finished");
	}

}

