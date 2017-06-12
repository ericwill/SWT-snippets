package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.VisibilityWindowAdapter;
import org.eclipse.swt.browser.VisibilityWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_disposeTwoChildShell {


	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(500, 200);

		Browser browser = new Browser(shell, SWT.NONE);
		
		
		// Open two child shells.
		// Close both child shells, on close try to close the parent.
		
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
					childBrowser.execute("setTimeout('window.close()', 2000)");
				}
			});
			childBrowser.addProgressListener(new ProgressAdapter() {
				@Override
				public void completed(ProgressEvent event) {
					System.out.println("fin loading" + childBrowser.getSize().toString());
					Browser childBrowser = (Browser)event.widget;
	
				}
			});
			childBrowser.addCloseWindowListener(new CloseWindowListener() {
				@Override
				public void close(WindowEvent event) {
					System.out.println("Trying to close parent widget.shell.");
					if (!shell.isDisposed())
						shell.close();
					
				}
			});
		});


		browser.setText("<script type='widget.text/javascript'>\n" + 
				"window.open('javascript:\"<h1>New Window 1</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=100,width=300\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 2</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
				"window.open('javascript:\"<h1>New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=70,width=500\")\n" + // W2 correct. W1 too big. 
//				"window.open('javascript:\"<h1>Simple New Window 1</h2><p>bounds: left=100,top=200,height=100,width=300<p>\"', \"\", \"left=100,top=200,height=100,width=300\")\n" +
//				"window.open('javascript:\"<h1>Simple New Window 3</h2><p>bounds: height=100,width=300<p>\"', \"\", \"height=100,width=300\")\n" +
//				"window.open('http://www.google.com')\n" +
				"</script> This should open a child window");
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}
