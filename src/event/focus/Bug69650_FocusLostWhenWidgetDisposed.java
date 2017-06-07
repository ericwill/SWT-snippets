package event.focus;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Bug69650_FocusLostWhenWidgetDisposed {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		display.addFilter(SWT.KeyDown, new Listener() {
			int count = 0;
			public void handleEvent(Event event) {
				System.out.println("Got a key down: " + count);
				count++;
			}
		});
		Text text = new Text(shell, SWT.BORDER);
		text.setBounds(20, 20, 200, 40);
		
		final Text text2 = new Text(shell, SWT.BORDER);
		text2.setBounds(80, 80, 200, 40);
		text2.addListener(SWT.KeyDown, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("ok, here goes");
				text2.dispose();
			}
		});
		
		shell.open();
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}
