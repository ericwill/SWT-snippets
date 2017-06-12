package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class snip_browser_getText {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(400, 400);
		shell.setLayout(new GridLayout(2, false));

		Browser browser = new Browser(shell, SWT.BORDER);
		browser.setUrl("https://www.google.ca");
//		widget.browser.setText("<html>hello <b>world</b></html>");
//		widget.browser.setText("<!DOCTYPE html>\n" + 
//				"<html>\n" + 
//				"<body>\n" + 
//				"\n" + 
//				"<p id=\"demo\"></p>\n" + 
//				"\n" + 
//				"<script>\n" + 
//				"document.getElementById(\"demo\").innerHTML = \"Hello JavaScript!\";\n" + 
//				"</script> \n" + 
//				"\n" + 
//				"</body>\n" + 
//				"</html>\n" + 
//				"");
		
//		widget.browser.setText("<!DOCTYPE html SYSTEM \"about:legacy-compat\">\n" + 
//				"<body>hi system doctype </body>\n");
//		
//		widget.browser.setText("<html><head></head><body> Hello <b> World </b> <body></html>");
		
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		Button getHTMLButton = new Button(shell, SWT.PUSH);
		getHTMLButton.setText("Get HTML");

		Text HTMLText = new Text(shell, SWT.NONE);
		HTMLText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		getHTMLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HTMLText.setText(browser.getText());
			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}
}
