package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_evaluate_with_button {

	public static void main(String[] args) {
		final String html = "<html><title>Snippet</title><body><p id='myid'>Best Friends</p><p id='myid2'>Cat and Dog</p></body></html>";
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final Button evalButton = new Button(shell, SWT.NONE);
		final Browser browser;
		browser = new Browser(shell, SWT.NONE);
		
		evalButton.setText("Make background red");
		evalButton.addListener(SWT.MouseDown, event -> {
			System.out.println("Button pressed");
			String script = "document.body.style.backgroundColor = 'red'";
			browser.evaluate(script);
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
