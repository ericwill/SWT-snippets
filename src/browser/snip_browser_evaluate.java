package browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_evaluate {

	public static void main(String[] args) {
		final String html = "<html><title>Snippet</title><body><p id='myid'>Best Friends</p><p id='myid2'>Cat and Dog</p></body></html>";
//		final String html = "<html><title>Snippet</title><body><a href=\"http://help.eclipse.org/kepler/topic/org.eclipse.jdt.doc.isv/reference/api/org/eclipse/jdt/annotation/NonNull.html\"><code>@NonNull</code> in 1.1.0</a>.</body></html>";
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final Browser browser;
		try {
			browser = new Browser(shell, SWT.NONE);
			browser.setJavascriptEnabled(false);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			display.dispose();
			return;
		}
		browser.addProgressListener(new ProgressListener() {
			@Override
			public void changed(ProgressEvent event) {
			}
			@Override
			public void completed(ProgressEvent event) {
//				String script = "document.body.style.backgroundColor = 'red'";
//				browser.evaluate(script);
				
//				browser.execute(script);
//				String value = (String)browser.evaluate("return document.getElementById('myid').childNodes[0].nodeValue;");
//				String value = (String)browser.evaluate("return 'hello'");
//				Double value = (Double) browser.evaluate("return 123");
//				Double value = (Double) browser.evaluate("return 123.456");
//				Boolean value = (Boolean) browser.evaluate("return false");
//				Boolean value = (Boolean) browser.evaluate("return true");
//				Boolean value = (Boolean) browser.evaluate("return null");
//				System.out.println("Returned: " + value.toString());
//				
//				Object [] value = (Object[]) browser.evaluate("return new Array(\"Saab\", \"Volvo\", \"BMW\")");
//				System.out.println("Node value: "+ value[0] + " " + value[1]);

//				Object [] value = (Object[]) browser.evaluate("return new Array(1, 2, new Array(3.1, 3.2)");
//				System.out.println("1: " + value[0] + " 2:" + value[1] + " 3:" + value[2]);
//
//				browser.evaluate("throw 'meh has occured'");
				browser.evaluate("return myUndefinedFunction()");
//				try {
//				Boolean value = (Boolean) browser.evaluate("return new Date()");
//				} catch (SWTException e) {
//					System.out.println("Meh: " +  e.getLocalizedMessage());
//				}

				shell.dispose();
			}
		});
		/* Load an HTML document */
		browser.setText(html);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
