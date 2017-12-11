package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/*
 * Title:  [GTK3][Webkit2] Implement webkit2 support for browser function (Part 2: Java return a value from callback.)
 * How to run: Snippet to execute javascript via input prompt, to test Browser Func return value ability.
 * Bug description:
 * Expected results:
 * GTK Version(s):
 */
public class snip_Browser_two_instances {

	static int count = 0;
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(500, 600);
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		shell.setLayout(gridLayout);
		
		{
			Browser b1 = new Browser(shell, SWT.None);
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
			gridData.widthHint = SWT.DEFAULT;
			gridData.heightHint = SWT.DEFAULT;
			b1.setLayoutData(gridData);
			
			b1.setText("instance1");
			b1.setSize(500, 300);
			new CustomFunction (b1, "theJavaFunction");
		}
		{
			Browser b2 = new Browser(shell, SWT.None);
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
			gridData.widthHint = SWT.DEFAULT;
			gridData.heightHint = SWT.DEFAULT;
			b2.setLayoutData(gridData);
			
			b2.setText("instance 2");
			b2.setSize(500, 300);
			new CustomFunction (b2, "theJavaFunction");
		}
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

//	/**
//	 * @param leftBrowser
//	 * @return
//	 */
//	private static Browser makeBrowserWithConsole(Composite leftBrowser, String funcName) {
//		GridLayout gridLayout = new GridLayout();
//		leftBrowser.setLayout(gridLayout);
//
//		final Text jsConsole = new Text(leftBrowser, SWT.BORDER);
//		jsConsole.setText("document.body.innerHTML = " + funcName + "(123)"); // Case where there are no paramaters.
//		jsConsole.setSelection(jsConsole.getText().length());
//		GridData data = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
//		jsConsole.setLayoutData(data);
//
//		final Browser browser = new Browser(leftBrowser, SWT.NONE);
//		browser.setText("hello <b>world!</b>");
//		data = new GridData(SWT.FILL, SWT.FILL, true, true);
//		browser.setLayoutData(data);
//
//		jsConsole.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.keyCode == 13) { // 13 = Enter
//					browser.execute(jsConsole.getText());
//				}
//			}
//		});
//
//		Button loadNewPage = new Button(leftBrowser, SWT.PUSH);
//		loadNewPage.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
//		loadNewPage.setText("Load new Page");
//		loadNewPage.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				browser.setText("New page!" + count++);
//			}
//		});
//		return browser;
//	}

	static class CustomFunction extends BrowserFunction { // copied from snippet 307
		CustomFunction (Browser browser, String name) {
			super (browser, name);
		}
		@Override
		public Object function (Object[] arguments) {
			System.out.println ("theJavaFunction() called from javascript with args:");
			for (int i = 0; i < arguments.length; i++) {
				Object arg = arguments[i];
				if (arg == null) {
					System.out.println ("\t-->null");
				} else {
					System.out.println ("\t-->" + arg.getClass ().getName () + ": " + arg.toString ());
				}
			}
			return arguments;
		}
	}

}
