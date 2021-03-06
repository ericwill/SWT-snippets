package widget.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug266150_setDefaultButtonAPI {
	
	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, false));

		final Button one = new Button(shell, SWT.PUSH);
		one.setText("one"); //$NON-NLS-1$
		one.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("one set to default"); //$NON-NLS-1$
				shell.setDefaultButton(one);
			}
		});

		Button two = new Button(shell, SWT.PUSH);
		two.setText("two"); //$NON-NLS-1$
		two.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("null set to default"); //$NON-NLS-1$
				shell.setDefaultButton(null);
			}
		});

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
