package browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.VisibilityWindowAdapter;
import org.eclipse.swt.browser.VisibilityWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_visibilityEvent {


	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(500, 200);

		Browser browser = new Browser(shell, SWT.NONE);
		
		browser.addOpenWindowListener(event -> {
			System.out.println("Open win listener started");
			Shell childShell = new Shell(display);
			childShell.setText("Child popup");
			childShell.setLayout(new FillLayout());
			Browser childBrowser = new Browser(childShell, SWT.NONE);
			event.browser = childBrowser;
			
			childBrowser.addVisibilityWindowListener(new VisibilityWindowAdapter() {
				@Override
				public void show(WindowEvent event) {
					System.out.println("Visibility listener was triggered");

					Browser childBrowser = (Browser)event.widget;
					final Shell childShell = childBrowser.getShell();
					if (event.location != null) childShell.setLocation(event.location); // w1: 0/0,  w2: 0/0
					if (event.size != null) {	// w1: event.size = 1200, 675  , w2: 0/0
						Point size = event.size;
						childShell.setSize(childShell.computeSize(size.x, size.y));
					}
					childShell.open();
					System.out.println("fin loading2" + childBrowser.getSize().toString());
				}
			});
			childBrowser.addProgressListener(new ProgressAdapter() {
				@Override
				public void completed(ProgressEvent event) {
					System.out.println("fin loading" + childBrowser.getSize().toString());
				}
			});
			
		});



		browser.addProgressListener(new ProgressAdapter() {
			@Override
			public void completed(ProgressEvent event) {
				System.out.println("Progress finished");
//				browser.execute("window.open(\"https://www.w3schools.com\", 'height=300,width=500')");
//				browser.execute("window.open(\"https://www.google.com\")");
//				browser.execute("window.open(\"https://www.w3schools.com\")");
				browser.execute("window.open('javascript:\"<h1>Simple New Window 3</h2><p>bounds: height=200,width=500<p>\"', 'childWin', 'height=200,width=500')");
			}
		});

		browser.setText("This should open a new child browser");
//		browser.setUrl("http://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}
