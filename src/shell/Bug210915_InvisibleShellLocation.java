package shell;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Bug210915_InvisibleShellLocation {

public static void main(String[] args) {
    Display display = new Display();
    final Shell shell = new Shell(display);
    shell.setText("Main");
    shell.setLayout(new RowLayout());

    final Button button = new Button(shell, SWT.PUSH);
    button.setText("Show");
    button.addSelectionListener(new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
            showHover(shell, button);
        }
    });
    shell.setBounds(100, 100, 300, 200);

    shell.open();
    while (!shell.isDisposed()) {
        if (!display.readAndDispatch())
            display.sleep();
    }
    display.dispose();
}

static void showHover(final Shell shell, final Button button) {
    final Display display = shell.getDisplay();
    Rectangle bb = button.getBounds();
    final Point pos = button.toDisplay(bb.x, bb.y + bb.height + 5);

    final Shell hover = new Shell(shell, SWT.ON_TOP | SWT.NO_TRIM);
    hover.setBackground(display.getSystemColor(SWT.COLOR_YELLOW));
    hover.setBounds(-30000, 0, 100, 50);
    hover.setVisible(true);

    display.asyncExec(new Runnable() {
        public void run() {
            System.out.println("before setLocation:" + hover.getLocation());
            hover.setLocation(pos);
            System.out.println("after setLocation:" + hover.getLocation());
        }
    });

    display.timerExec(2000, new Runnable() {
        public void run() {
            Point loc = hover.getLocation();
            System.out.println("before setVisible(false): " + loc);
            hover.setVisible(false);
            Point loc2 = hover.getLocation();
            System.out.println("after setVisible(false): " + loc2);
        }
    });

    display.timerExec(4000, new Runnable() {
        public void run() {
            Point loc = hover.getLocation();
            System.out.println("before setVisible(true): " + loc);
            hover.setVisible(true);
            Point loc2 = hover.getLocation();
            System.out.println("after setVisible(true): " + loc2);
        }
    });
}
}