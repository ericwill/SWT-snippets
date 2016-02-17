package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Bug465280_InvisibleControlAllocation {
	public static void main(String[] args) {
        String property = System.getenv("SWT_GTK3");
        System.err.println("GTK"+(property.equals("1")?"3":"2"));
        Display display = new Display ();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        Label descriptionHint = new Label(shell, SWT.WRAP);
        descriptionHint.setText("This is a very very very very very very long string");
        descriptionHint.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        descriptionHint.setVisible(false);
        shell.open();
        System.err.println(descriptionHint.getSize());
        while (!shell.isDisposed ()) {
                if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
}

}
