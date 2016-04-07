package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class DrawingBackgroundTest {
	public static void main(String[] args) {
        final Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setLayout(new GridLayout());

        final Label l = new Label(shell, SWT.NONE);
        l.setText("ASDQWE");
        l.addPaintListener(new PaintListener() {

            @Override
            public void paintControl(PaintEvent arg0) {
                arg0.gc.drawLine(0, 0, arg0.width, arg0.height);
                System.out.println("drawing?");
            }
        });

        final Button b = new Button(shell, SWT.PUSH);
        b.setText("CLICK");
        b.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                l.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
                // these don't help
                
//                l.redraw();
//                l.update();
                
                MessageBox mb = new MessageBox(shell);
                mb.setMessage("Background should not override GC drawing, but it does");
                mb.open();
            }
        });

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
