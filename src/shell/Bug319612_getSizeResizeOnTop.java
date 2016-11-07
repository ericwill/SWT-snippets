package shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug319612_getSizeResizeOnTop {

public static void main (String [] args) {
    Display display = new Display ();
    Shell shell = new Shell (display, SWT.RESIZE | SWT.ON_TOP);
    
    GridLayout layout= new GridLayout(1, false);
    layout.marginHeight= 0;
    layout.marginWidth= 0;
    shell.setLayout(layout);

    shell.setSize (200, 200);
    shell.open ();

    for (int i = 0; i < 50; i++) {
        while (display.readAndDispatch ());
    
        Point p = shell.getSize();
        shell.setSize(p.x, p.y);
        
        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    while (!shell.isDisposed()) {
        if (!display.readAndDispatch ()) display.sleep ();
    }
    display.dispose ();
}

}