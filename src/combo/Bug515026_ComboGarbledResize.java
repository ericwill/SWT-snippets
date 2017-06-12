package combo;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Bug515026_ComboGarbledResize {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));

		Label label = new Label(shell, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,false, 1, 1));
		label.setText("Testing Label");

		Combo text = new Combo(shell, SWT.READ_ONLY);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text.add("This is a very long long long long long box");

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}