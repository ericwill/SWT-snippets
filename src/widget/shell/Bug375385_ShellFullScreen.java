package widget.shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug375385_ShellFullScreen
{
    public static void main(String[] args)
    {
        Display display = new Display();
        
        Shell parentShell = new Shell(display);
        parentShell.setText("Parent widget.shell");
        parentShell.setSize(400, 300);
                
        final Shell childShell = new Shell(parentShell, SWT.APPLICATION_MODAL | SWT.RESIZE | SWT.CLOSE);
        childShell.setSize(300, 200);
        childShell.setText("Child widget.shell");        
        
        childShell.addMouseListener(new MouseListener()
        {   
            @Override
            public void mouseUp(MouseEvent event)
            {   
                
            }
            
            @Override
            public void mouseDown(MouseEvent event)
            {
                
            }
            
            @Override
            public void mouseDoubleClick(MouseEvent event)
            {
                // Toggle full screen mode: works on Mac and Windows, won't work on Linux
                System.out.println("Full screen: " + childShell.getFullScreen());
                
                childShell.setFullScreen(! childShell.getFullScreen());
                
                System.out.println("Full screen: " + childShell.getFullScreen());                
            }
        });
        
        parentShell.open();
        childShell.open();
                
        while (! parentShell.isDisposed())
        {
            if (! display.readAndDispatch())
            {
                display.sleep();
            }
        }
        display.dispose();
    }    
}