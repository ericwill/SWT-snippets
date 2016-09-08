package awt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug160691_SWTAWTShell {

	public static void main(String[] args) {
        java.awt.Frame frame = new java.awt.Frame("AWT Frame");
        java.awt.Button button = new java.awt.Button("AWT Button");
        frame.add(button, java.awt.BorderLayout.NORTH);
        java.awt.Canvas canvas = new java.awt.Canvas();
        frame.add(canvas, java.awt.BorderLayout.CENTER);

        frame.addNotify();
       
        Display display = new Display();
        Shell shell = SWT_AWT.new_Shell(display, canvas);
        shell.setLayout(new FillLayout());
        Button swtButton = new Button(shell, SWT.PUSH);
        swtButton.setText("SWT Button");
       
        frame.setBounds(20, 20, 300, 300);
        shell.layout();
        frame.setVisible(true);
       
        while (!shell.isDisposed()) {
                if (!display.readAndDispatch()) display.sleep();
        }
}
}
