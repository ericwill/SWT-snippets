package EVENT.focus;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Bug227261_FocusTraverseException {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(new Point(300, 200));
		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setSize(new Point(300, 200));
		composite.setLayout(new GridLayout());
		Text text = new Text(composite, SWT.BORDER);
		text.addFocusListener(new FocusAdapter(){

			public void focusLost(FocusEvent e) {
				composite.dispose();
			}
			
		});
		Button button = new Button(composite, SWT.PUSH);
		button.setText("Focus?");
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
