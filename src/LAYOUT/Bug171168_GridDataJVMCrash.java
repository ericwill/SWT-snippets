package LAYOUT;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
 
public class Bug171168_GridDataJVMCrash {
 
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
 
		Button bz = new Button(shell, SWT.PUSH);
		bz.setLayoutData(new GridData(SWT.END, SWT.CENTER));
 
		shell.open();
 
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
 
		shell.dispose();
		display.dispose();
	}
}