package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug510972_WinClearedSignal_auto {
	static int count = 0;

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final Browser browser = new Browser(shell, SWT.NONE);
		
		class CustomFunction extends BrowserFunction { // Note: Local class defined inside method.
			CustomFunction(Browser browser, String name) {
				super(browser, name);
			}

			@Override
			public Object function(Object[] arguments) {
				System.out.println( this.getName() + " called from javascript");
				if (count == 0) {
					browser.setText("2nd page load");
					count++;
				} else {
					System.out.println("Test passed.");
				}
				return null;
			}
		}
		browser.setText("1st (initial) page load");
		new CustomFunction(browser, "callCustomFunction");
		browser.execute("callCustomFunction()");
		
		
		browser.addProgressListener(new ProgressListener() {
			@Override
			public void completed(ProgressEvent event) {
				System.out.println("load finished.");
				browser.execute("document.body.style.backgroundColor = 'red'");
				browser.execute("callCustomFunction()");
			}
			
			@Override
			public void changed(ProgressEvent event) {
			}
		});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
