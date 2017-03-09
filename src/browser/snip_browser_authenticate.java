package browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.AuthenticationEvent;
import org.eclipse.swt.browser.AuthenticationListener;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_authenticate {

	public static void main(String[] args) {
		final String html = "<html><title>Snippet</title><body><p id='myid'>Best Friends</p><p id='myid2'>Cat and Dog</p></body></html>";
//		final String html = "<html><title>Snippet</title><body><a href=\"http://help.eclipse.org/kepler/topic/org.eclipse.jdt.doc.isv/reference/api/org/eclipse/jdt/annotation/NonNull.html\"><code>@NonNull</code> in 1.1.0</a>.</body></html>";
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final Browser browser = new Browser(shell, SWT.NONE);
		
		browser.addAuthenticationListener(event -> {
			System.out.println("Authentication request");
			event.user = "httpwatch";
			event.password = "foo";
			}
		);
		browser.setUrl("https://www.httpwatch.com/httpgallery/authentication/#showExample10");
//		browser.setUrl("https://nominationsdemo.elia.be/B2C");
		
		/* Load an HTML document */
//		browser.setText(html);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
