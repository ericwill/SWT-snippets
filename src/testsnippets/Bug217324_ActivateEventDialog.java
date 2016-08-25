package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

public class Bug217324_ActivateEventDialog
{
	
    public static void main(String[] args)
    {
        Display display = new Display();
        display.addFilter(SWT.Activate, new Listener()
        {
            public void handleEvent(Event event)
            {
                System.out.println("activate: " + event);
            }
        });

        display.addFilter(SWT.Deactivate, new Listener()
        {
            public void handleEvent(Event event)
            {
                System.out.println("deactivate: " + event);
            }
        });

        Shell shell = new Shell(display);
        shell.setText("Shell");
        shell.setSize(200, 200);
        System.out.println("before shell open");
        shell.open();
        System.out.println("after shell open");

        System.out.println("set visible false before");
        shell.setVisible(false);
        System.out.println("set visible false after");

        Shell dialog = new Shell(shell);
        dialog.setText("Dialog");
        dialog.setSize(200, 200);
        System.out.println("before dialog open");
        dialog.open();
        System.out.println("after dialog open");

        // When dialog is closed no activate event

        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}