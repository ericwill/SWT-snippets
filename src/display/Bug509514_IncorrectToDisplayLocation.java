package display;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

/**
 * @author Thomas Singer
 */
public class Bug509514_IncorrectToDisplayLocation {

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);

		final RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.fill = true;
		shell.setLayout(layout);

		final Combo combo = new Combo(shell, SWT.BORDER);
		installPopup(combo);

		final Text text = new Text(shell, SWT.BORDER);
		installPopup(text);

		shell.setSize(400, 300);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	private static void installPopup(Control control) {
		final Listener listener = new Listener() {
			private Shell shell;

			@Override
			public void handleEvent(Event event) {
				if (event.type == SWT.KeyDown) {
					if (event.character == ' ') {
						shell = new Shell(control.getShell(), SWT.POP_UP | SWT.ON_TOP | SWT.BORDER);
						final Point location = control.toDisplay(0, control.getSize().y);
						shell.setBounds(location.x, location.y, 100, 100);
						shell.setVisible(true);
					}
				} else if (event.type == SWT.FocusOut) {
					if (shell != null) {
						shell.dispose();
						shell = null;
					}
				}
			}
		};
		control.addListener(SWT.KeyDown, listener);
		control.addListener(SWT.FocusOut, listener);
	}
}
