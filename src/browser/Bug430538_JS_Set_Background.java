package browser;

import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Copied and adapted from Snippet128
 * 
 * Web-Browser that loads a static HTML page.
 * Then it uses some JavaScript to modify background.
 * @author lufimtse
 *
 */

public class Bug430538_JS_Set_Background {
	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		shell.setLayout(gridLayout);
		
		Button executeJS = new Button(shell, SWT.PUSH);
		executeJS.setText("Execute JS (Red background)");
		
		Button evauateJS = new Button(shell, SWT.PUSH);
		evauateJS.setText("Evaluate JS (Green with return val)");
		
		final Browser browser = new Browser(shell, SWT.WEBKIT);
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		browser.setLayoutData(gd);
		String html = "<body>Hello<br>world</body>";
		browser.setText(html);
		
		// Append Webkit version into snippet title.
		String SWT_WEBKITGTK_VERSION = "org.eclipse.swt.internal.webkitgtk.version"; //$NON-NLS-1$
		Properties sp = System.getProperties();
		String webkitGtkVer = sp.getProperty(SWT_WEBKITGTK_VERSION);
		shell.setText("Webkit version: " + webkitGtkVer);
		
		// EXECUTE 
		executeJS.addListener(SWT.MouseDown, ev -> {
			String script = "document.body.style.backgroundColor = 'red'; function hullo() {return 'hi'}; hullo()";
			browser.execute(script);
		});
		
		
		// EVALUATE << NOT EXECUTE.
		evauateJS.addListener(SWT.MouseDown, ev -> {
			String script = "document.body.style.backgroundColor = 'green'; function hullo() {return 'hi'}; hullo()";
			browser.evaluate(script);
		});


 	    shell.open();
	    while (!shell.isDisposed ()) {
	        if (!display.readAndDispatch ()) display.sleep ();
	    }
	    display.dispose ();
	}
}
