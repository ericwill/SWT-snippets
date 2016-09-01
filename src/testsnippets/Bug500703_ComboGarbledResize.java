package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Bug500703_ComboGarbledResize {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));

		final Label l = new Label(shell, SWT.None);
		l.setText("Select item:");
		
		final Combo combo = new Combo(shell, SWT.READ_ONLY | SWT.BORDER);
//		A Combo without SWT.READ_ONLY works
//		final Combo combo = new Combo(shell, SWT.BORDER);

		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		combo.setLayoutData(gd);
		String[] items = new String[] { "A", "B", "C 12345678901234567890123456789012345678901234567890" };
		combo.setItems(items);
		combo.select(2);

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}

}