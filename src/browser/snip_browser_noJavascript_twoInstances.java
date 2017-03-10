package browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


/** Observation: Webkit1, each instance has it's own settings. */
public class snip_browser_noJavascript_twoInstances {
	
	static int i1page = 1;
	static int i2page = 1;

	/**
	 * Test that turning off JS in one browser instance doesn't turn off JS execution in another insance.
	 *
	 * Logic:
	 * 2 Browser instances.
	 * Instance 1: Javascript is dissabled. Page reloaded. Expected behaviour: Javascript should not run on second page.
	 * Instance 2: Javascript is left as is. Page is reloaded (by instance 1). Expected behaviour: Javascript should execute.
	 * 
	 * @param args
	 */
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
					browser.setText("Instance1 page 2 without javascript");
					browser2.setText("Instance2 page 2 with javascript.");
					
				} else if (i1page == 2) {
					String str = (String) browser.evaluate("return 'ERROR, SHOULD NOT HAVE RETURNED'");
					System.out.println("Instance1: Page 2 evaluation expected:null, actual: " + str);
				}
			}
		});
		browser2.addProgressListener(new ProgressAdapter() {
			@Override
			public void completed(ProgressEvent event) {
				if (i2page == 2) {
					String str2 = (String) browser2.evaluate("return 'shouldReturn'");
					System.out.println("Instance2: Page 2 evaluation expected:'shouldReturn', actual:" + str2);
				}
			}
		});
		
		String str = (String) browser.evaluate("return 'shouldReturn'");
		System.out.println("Instance1: Page 1 evaluation, expected:shouldReturn actual:" + str);
		String str2 = (String) browser2.evaluate("return 'shouldReturn'");
		System.out.println("Instance2: Page 1 evaluation, expected:shouldReturn actual: " + str2);

		browser.setText("Instance 1: page 1 with javascript");
		browser2.setText("Instance 2: page 1 with javascript");
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
