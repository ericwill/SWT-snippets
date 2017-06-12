package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_evaluate_with_buttons {

	public static void main(String[] args) {
		final String html = "Try setting and getting javascript variables.";
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final Button setVarButton = new Button(shell, SWT.NONE);
		final Button getVarButton = new Button(shell, SWT.NONE);
		
		final Browser browser;
		browser = new Browser(shell, SWT.NONE);
		
		setVarButton.setText("SetVarbutton");
		setVarButton.addListener(SWT.MouseDown, event -> {
			System.out.println("Setting var pressed");
			String script = "myVar = true";
			browser.evaluate(script);
		});
		
		getVarButton.setText("GetVarButton");
		getVarButton.addListener(SWT.MouseDown, event -> {
			System.out.print("Getting var: ");
			try {
				Boolean retVal = (Boolean) browser.evaluate("return myVar");
				System.out.println(retVal);
			} catch (SWTException e) {
				System.out.println("Variable not set yet");
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
