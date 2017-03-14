package browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_close {


	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(500, 200);

		Browser browser = new Browser(shell, SWT.NONE);
		
		browser.addCloseWindowListener(event -> {
			System.out.println("PASSED : Close listener was triggered");
		});
		
		browser.setText("You should not be able to see this because browser should have closed by javascript");
		browser.execute("window.close()");
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}