package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


/** Observation: Webkit1, each instance has it's own settings. */
public class snip_browser_ProgListenerCompletedTwice {
	
	static int i1page = 1;
	static int i2page = 1;

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final Browser browser = new Browser(shell, SWT.NONE);
		final Browser browser2 = new Browser(shell, SWT.NONE);
		
		browser.addProgressListener(new ProgressAdapter() {
			@Override
			public void completed(ProgressEvent event) {
				if (i1page == 1) {
					i1page = 2;
					i2page = 2;
					browser.setJavascriptEnabled(false);
					browser.setText("I1 page 2 without Javascript");
					browser2.setText("I2 page 2 with js.");
					
				} else if (i1page == 2) {
					String str = (String) browser.evaluate("return 'SHOULD NOT RETURN'");
					System.out.println("I1 : Page 2 evaluation: " + str);
				}
			}
		});
		browser2.addProgressListener(new ProgressAdapter() {
			@Override
			public void completed(ProgressEvent event) {
				if (i2page == 2) {
					String str2 = (String) browser2.evaluate("return 'Should return'");
					System.out.println("I2: Page 2 evaluation: " + str2);
				}
			}
		});
		
		String str = (String) browser.evaluate("return 'i1 page 1 js'");
		System.out.println("I1 Page 1 evaluation: " + str);
		String str2 = (String) browser2.evaluate("return 'i2 page 1 js'");
		System.out.println("I2 Page 1 evaluation: " + str2);

		browser.setText("Instance 1, page one with javascript");
		browser2.setText("Instance 2, page one with javascript");
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
