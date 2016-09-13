package gc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;


public class Bug35644_GCPaintListener {
	
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		
		shell.addListener(SWT.Paint, new Listener() {
			public void handleEvent(Event e) {
				if (e.x == 0) return;
				GC gc1 = new GC(shell);
				gc1.drawLine(0, 0, 2000, 2000);
				gc1.dispose();
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}

}
