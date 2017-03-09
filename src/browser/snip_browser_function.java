package browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_function {


	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(500, 200);

		class CustomFunction extends BrowserFunction { // Note: Local class defined inside method.
			CustomFunction(Browser browser, String name) {
				super(browser, name);
			}

			@Override
			public Object function(Object[] arguments) {
				System.out.println( this.getName() + " called from javascript");
				return null;
			}
		}
		
		final Browser browser;
		browser = new Browser(shell, SWT.NONE);

		browser.setText(createBasicCallback());
		new CustomFunction(browser, "javacustomfunction");

		browser.addProgressListener(new ProgressListener() {
			@Override
			public void changed(ProgressEvent event) {}
			@Override
			public void completed(ProgressEvent event) {
//				Object evalResult = browser.execute("document.body.style.backgroundColor = 'red'");
				browser.execute("callCustomFunction()");
			}
		});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	static String createBasicCallback() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><head>\n"
				+ "<script language=\"JavaScript\">\n"
				+ "function callCustomFunction() {\n"  // Define a javascript function.
				+ "     document.body.style.backgroundColor = 'red'\n"
				+ "		javacustomfunction()\n"        // This calls the javafunction that we registered.
				+ "}"
				+ "</script>\n"
				+ "</head>\n"
				+ "<body> Upon compleation of load, this should print something in java console </body>\n"
				+ "</html>\n");
		return buffer.toString();
	}

}
