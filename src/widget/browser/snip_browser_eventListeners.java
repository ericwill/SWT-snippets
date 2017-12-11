package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_eventListeners {


	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(500, 200);
		Browser browser = new Browser(shell, SWT.NONE);
		
		// [] Webkit2 (not working)
		// [] Webkit1 (to be tested)
		browser.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("Browser Focus lost " + e.toString());
			}
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("Browser Focus gained " + e.toString());
			}
		});
		
		// [] Webkit2 (not working)
		// [] Webkit1 (to be tested)
		browser.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				System.out.println("Browser Mouse Up " + e.toString());
			}
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Browser Mouse Down " + e.toString());
				
			}
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				System.out.println("Browse Mouse Double click " + e.toString());
			}
		});
		
//		// [x] Webkit2/1
//		browser.addKeyListener(new KeyListener() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				System.out.println("Browser key released " + e.toString());
//			}
//			@Override
//			public void keyPressed(KeyEvent e) {
//				System.out.println("Browser key pressed " + e.toString());
//			}
//		});
//
//		// [x] Webkit2/1
//		browser.addMouseMoveListener(new MouseMoveListener() {
//			@Override
//			public void mouseMove(MouseEvent e) {
//				System.out.println("Browser mouse moved " + e.toString());
//			}
//		});
//
//		// [x] Webkit2/1
//		browser.addMouseWheelListener(new MouseWheelListener() {
//			@Override
//			public void mouseScrolled(MouseEvent e) {
//				System.out.println("Browser scroll event " + e.toString());
//			}
//		});
		
		
		
		
		Button button = new Button(shell, SWT.PUSH);
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		button.setText("Button");
		
		Button button2 = new Button(browser, SWT.PUSH);
		button2.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		button2.setText("Button 2");
		
		
		
		

		browser.setText("Hello world");
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}
