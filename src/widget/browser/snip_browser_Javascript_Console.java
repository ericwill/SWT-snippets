package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class snip_browser_Javascript_Console {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(500, 600);
		
		GridLayout gridLayout = new GridLayout ();
		shell.setLayout (gridLayout);

		final Text jsConsole = new Text (shell, SWT.BORDER);
		jsConsole.setText ("document.body.style.backgroundColor = 'red'; window.myCFunction();");
		jsConsole.setSelection(jsConsole.getText().length());
		GridData data = new GridData (SWT.FILL, SWT.BEGINNING, true, false);
		jsConsole.setLayoutData (data);

		final Browser browser = new Browser (shell, SWT.NONE);
		browser.setText("hello <b>world!</b>");
		data = new GridData (SWT.FILL, SWT.FILL, true, true);
		browser.setLayoutData (data);
		
		jsConsole.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) { //13 = Enter
					browser.execute(jsConsole.getText());
				}
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
