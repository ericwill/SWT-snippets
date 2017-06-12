package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class snip_browser_mouseHover {

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLocation(0, 0);
		shell.setLayout(new FillLayout());
		final Browser browser = new Browser(shell, SWT.NONE);
		StringBuilder longhtml = new StringBuilder();
		for (int i = 0; i < 200; i++) {
			longhtml.append("widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text widget.text<br>");
		}
		
		
		browser.setText("<a href='http://localhost'>" + longhtml + "</a>");
		browser.addStatusTextListener(new StatusTextListener() {
			@Override
			public void changed(StatusTextEvent event) {
				System.out.println("Move hovered over");
			}
		});
		browser.addProgressListener(new ProgressListener() {
			
			@Override
			public void completed(ProgressEvent event) {
				System.out.println("completed");
				Point cachedLocation = display.getCursorLocation();
				display.setCursorLocation(20, 10);
				browser.getBounds();
				for (int i = 5; i < 200; i++) {
					display.setCursorLocation(20, i);
					runLoopTimer(1, shell);
				}
				display.setCursorLocation(cachedLocation);
				
			}
			
			@Override
			public void changed(ProgressEvent event) {
				
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}
	
	static void runLoopTimer(final int milliseconds, Shell shell) {
		final boolean[] exit = {false};
		new Thread() {
			@Override
			public void run() {
				try {Thread.sleep(milliseconds);} catch (Exception e) {}
				exit[0] = true;
				/* wake up the event loop */
				Display display = Display.getDefault();
				if (!display.isDisposed()) {
					display.asyncExec(() -> {
						if (!shell.isDisposed()) shell.redraw();
					});
				}
			}
		}.start();
		shell.open();
		Display display = Display.getCurrent();
		while (!exit[0] && !shell.isDisposed()) if (!display.readAndDispatch()) display.sleep();
	}


}
