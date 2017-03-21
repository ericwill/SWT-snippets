package browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_evaluate_multiLine {

	public static void main(String[] args) {
		final String html = "<html><title>Snippet</title><body><p id='myid'>Best Friends</p><p id='myid2'>Cat and Dog</p></body></html>";
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final Browser browser;
		browser = new Browser(shell, SWT.NONE);

		browser.setText(html);
		
		browser.addProgressListener(new ProgressAdapter() {
			@Override
			public void completed(ProgressEvent event) {
//				String script = "document.body.style.backgroundColor = 'red'; return true";
//				String script = "i = 1; document.body.style.backgroundColor = 'red';return true";
				String script = "tr = true; return tr";	// Works on Webkit1, but on webkit this throws an exception. Derp.
				Boolean shouldbeTrue = (Boolean) browser.evaluate(script);
				System.out.println("Should be 'true'. Actual returned: " + shouldbeTrue);
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
