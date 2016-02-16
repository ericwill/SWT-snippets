package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Creates and opens a wizard dialog with two simple wizard pages.
 */
public class Bug487522_TextPadding {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell();
		shell.setLayout(new GridLayout(1, true));
		Text text;

		text = new Text(shell, SWT.SEARCH);
		text.setMessage("search");
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

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
