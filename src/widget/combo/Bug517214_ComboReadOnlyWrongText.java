package widget.combo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug517214_ComboReadOnlyWrongText {
	public static void main (String [] args) {
	Display display = new Display ();
	Shell shell = new Shell (display);
	Combo combo = new Combo (shell, SWT.READ_ONLY);
	combo.setItems ("Alpha");
	Rectangle clientArea = shell.getClientArea ();
	combo.setBounds (clientArea.x, clientArea.y, 200, 200);
	MouseAdapter clickListener = new MouseAdapter() {
		@Override
		public void mouseDown(MouseEvent e) {
			System.out.println("hello");
		}
	};
	shell.addMouseListener(clickListener);
	shell.pack ();
	shell.open ();
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
	}
	display.dispose ();
}
}
