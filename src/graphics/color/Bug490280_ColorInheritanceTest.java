package graphics.color;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * @author Thomas Singer
 */
public class Bug490280_ColorInheritanceTest {

	public static void main(String[] args) {
		final Display display = new Display();

		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		final Composite panel = new Composite(shell, SWT.NO_RADIO_GROUP);
		panel.setLayout(new FillLayout());
		panel.setBackgroundMode(SWT.INHERIT_DEFAULT);
		panel.setBackground(display.getSystemColor(SWT.COLOR_CYAN));

		final Label textLabel = new Label(panel, SWT.LEFT);
		textLabel.setText("hello");

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
