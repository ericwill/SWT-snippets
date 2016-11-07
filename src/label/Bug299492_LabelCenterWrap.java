package label;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Bug299492_LabelCenterWrap {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);

        createLabel1(shell);
//        createLabel2(shell);

        shell.pack();
        shell.open();
        while(!shell.isDisposed()) {
            if(!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    // this is inspired from my real-world application code
    private static void createLabel1(Shell shell) {
        shell.setLayout(new GridLayout(1, true));
        Label label = new Label(shell, SWT.HORIZONTAL | SWT.CENTER | SWT.WRAP);
        label.setText("Very Very Very Long String");
        label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
//        createLabelInsideComposite(shell);
    }

    // this doesn't work either
    private static void createLabel2(Shell shell) {
        shell.setLayout(new FillLayout());
        
        Label label = new Label(shell, SWT.HORIZONTAL | SWT.CENTER | SWT.WRAP);
        label.setText("Very Very Very Long String");
//        createLabelInsideComposite(shell);
    }
    
    // this attempt to workaround the problem by creating two composites
    // to grab the extra horizontal space doesn't work
    private static void createLabelInsideComposite(Shell shell) {
        Composite parent = new Composite(shell, SWT.NONE);
        GridLayout gridLayout = new GridLayout(3, false);
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        parent.setLayout(gridLayout);
        
        Composite leftPanel = new Composite(parent, SWT.NONE);
        leftPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        Label label = new Label(parent, SWT.HORIZONTAL | SWT.CENTER | SWT.WRAP);
        label.setText("Very Very Very Long String");
        label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        
        Composite rightPanel = new Composite(parent, SWT.NONE);
        rightPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    }

}