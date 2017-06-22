package event.mouse;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Bug516480_KeydownRussianlayout {
	/**
	 * @author Thomas Singer
	 */
	public static void main(String[] args) {
		final Display display = new Display();

		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		final StyledText text = new StyledText(shell, SWT.BORDER);
		text.setText("hello world");

		text.addListener(SWT.KeyDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				System.out.println("event.character = " + (int)event.character);
				System.out.println("event.keyCode = " + event.keyCode);
			}
		});

		shell.setSize(300, 200);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}
}
