package shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

/**
 * @author Thomas Singer
 */
public class Bug496639_DialogFullScreenShell {

    public static void main(String[] args) {
        Display display = new Display();

        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout());

        Menu menuBar = new Menu(shell, SWT.BAR);
        MenuItem fileMenuItem = new MenuItem(menuBar, SWT.CASCADE);
        fileMenuItem.setText("File");
        Menu fileMenu = new Menu(fileMenuItem);
        fileMenuItem.setMenu(fileMenu);

        MenuItem toggleFullScreenMenuItem = new MenuItem(fileMenu, SWT.CHECK);
        toggleFullScreenMenuItem.setText("Toggle full screen");
        toggleFullScreenMenuItem.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                shell.setFullScreen(toggleFullScreenMenuItem.getSelection());
            }
        });

        MenuItem showDialogMenuItem = new MenuItem(fileMenu, SWT.PUSH);
        showDialogMenuItem.setText("Show dialog");
        showDialogMenuItem.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                Shell dialogShell = new Shell(shell, SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
                dialogShell.setLayout(new FillLayout());

                Text text = new Text(dialogShell, SWT.BORDER);
                text.setText("Hello world!");

                // center on top of parent shell
                Point size = dialogShell.computeSize(SWT.DEFAULT, SWT.DEFAULT);
                Rectangle bounds = shell.getBounds();
                dialogShell.setBounds(bounds.x + (bounds.width - size.x) / 2,
                        bounds.y + (bounds.height - size.y) / 2,
                        size.x, size.y);
                dialogShell.open();
//                dialogShell.forceActive();
//                dialogShell.moveAbove(shell);
            }
        });

        shell.setMenuBar(menuBar);

        shell.setSize(300, 200);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }
}
