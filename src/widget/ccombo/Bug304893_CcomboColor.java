package widget.ccombo;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/*
 * Modified Snippet39
 */
public class Bug304893_CcomboColor {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());

		CCombo combo = new CCombo(shell, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		for (int i = 0; i < 5; i++) {
			combo.add("item" + i);
		}
		combo.setText("item0");

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}

}
