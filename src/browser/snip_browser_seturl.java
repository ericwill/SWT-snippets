package browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_seturl {


	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(500, 200);

		Browser browser = new Browser(shell, SWT.NONE);
		
		browser.setUrl("de.wikipedia.org/wiki/Charlie");
		
		if (2 == 1+1)
			throw new SWTException();
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}
