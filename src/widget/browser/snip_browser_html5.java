package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_html5 {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(400, 400);
		shell.setLayout(new GridLayout(2, false));

		Browser browser = new Browser(shell, SWT.BORDER);
//		widget.browser.setUrl("https://www.google.ca");
		browser.setText("<!DOCTYPE html>\n" + 
				"<html lang=\"en\">\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Drawing a Circle on Canvas</title>\n" + 
				"<style type=\"widget.text/css\">\n" + 
				"	canvas{\n" + 
				"		border: 1px solid #000;\n" + 
				"	}\n" + 
				"</style>\n" + 
				"<script type=\"widget.text/javascript\">\n" + 
				"    window.onload = function(){\n" + 
				"        var canvas = document.getElementById(\"myCanvas\");\n" + 
				"        var context = canvas.getContext(\"2d\");\n" + 
				"        context.arc(150, 100, 70, 0, 2 * Math.PI, false);\n" + 
				"        context.stroke();\n" + 
				"    };\n" + 
				"</script>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"    <canvas id=\"myCanvas\" width=\"300\" height=\"200\"></canvas>\n" + 
				"</body>\n" + 
				"</html>       ");
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}
}
