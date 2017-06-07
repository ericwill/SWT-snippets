package widget.shell;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class Bug286244_ShellActivateLoop {

    public static void main(String[] args) {
        final Display display= new Display();
        final Shell shell= new Shell(display);
        shell.addShellListener(new ShellAdapter() {
            public void shellDeactivated(ShellEvent e) {
                System.out.println("shellDeactivated()");
                display.timerExec(2000, new Runnable() {
                    public void run() {
                        System.out.println("forcing active");
                        shell.forceActive();
                        System.out.println("widget.shell is active: "
                                + (display.getActiveShell() == shell));
                        System.out.println("forced active");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void shellActivated(ShellEvent e) {
                System.out.println("shellActivated()");
            }
        });

        shell.setSize(160, 100);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}