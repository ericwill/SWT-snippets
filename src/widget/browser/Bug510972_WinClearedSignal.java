package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug510972_WinClearedSignal {
	static int count = 0;

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final Button loadNewPage = new Button(shell, SWT.PUSH);
		final Button execFunc = new Button(shell, SWT.PUSH);
		final Browser browser = new Browser(shell, SWT.NONE);
		
		
		class CustomFunction extends BrowserFunction { // Note: Local class defined inside method.
			CustomFunction(Browser browser, String name) {
				super(browser, name);
			}

			@Override
			public Object function(Object[] arguments) {
				System.out.println( this.getName() + " called from javascript");
				return null;
			}
		}
		new CustomFunction(browser, "callCustomFunction");
		
		// Button
		loadNewPage.setText("load new page");
		loadNewPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				browser.setText("Count is: " + count++);
			}
		});
		
		// Button 
		execFunc.setText("Call java function");
		execFunc.addListener(SWT.MouseDown, ev -> {
			System.out.println("mouse down.");
			browser.execute("callCustomFunction()");
		});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
