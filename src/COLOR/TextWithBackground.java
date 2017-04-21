package COLOR;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextWithBackground {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell();
		shell.setLayout(new FillLayout());
		Text text = new Text(shell, SWT.SINGLE);
		text.setText("hello world");
		text.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
