package widget.combo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug460017_ComboOverwrite {
    public static void main(String[] args) {
        final Display disp = Display.getDefault();
        Shell shell = new Shell(disp);
        shell.setLayout(new GridLayout());

        final Combo combo = new Combo(shell, SWT.READ_ONLY | SWT.BORDER);
        combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        combo.add("first", 0);
        combo.add("second", 1);
        combo.add("third", 1);
        combo.add("fourth", 1);

        shell.setText("Combo Test");
        shell.setSize(200, 200);
        shell.setLocation(0, 0);  
        shell.open();

        while (!shell.isDisposed()) {
            if (!disp.readAndDispatch()) {
                disp.sleep();
            }
        }
    }
}