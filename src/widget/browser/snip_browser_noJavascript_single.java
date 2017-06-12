package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


/** Observation: Webkit1, each instance has it's own settings. */
public class snip_browser_noJavascript_single {
	
	static int pageLoadCount = 1;

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final Browser browser = new Browser(shell, SWT.NONE);
		browser.addProgressListener(new ProgressAdapter() {
			@Override
			public void completed(ProgressEvent event) {
				if (pageLoadCount == 1) {
					Boolean bool = (Boolean) browser.evaluate("return true");
					System.out.println("This should return 'true':" + bool);
					browser.setJavascriptEnabled(false);
					browser.setText("Second page with JS dissabled");
				} else if (pageLoadCount == 2) {
					Boolean bool = (Boolean) browser.evaluate("return true"); // should never execute.
					System.out.println("this should be null: " + bool);
				} else {
					System.out.println("this should never happen.");
				}
				pageLoadCount++;
			}
		});

		browser.setText("First page with javascript enabled");
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
