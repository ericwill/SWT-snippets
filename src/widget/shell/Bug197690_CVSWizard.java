package widget.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug197690_CVSWizard {
	public static void main(String[] args) {
	    final Display display = new Display();
	    final Shell shell1 = new Shell(display);
	    shell1.setLayout(new FillLayout());
	    shell1.setBounds(10, 10, 300, 300);
	    final DateTime dt = new DateTime(shell1, SWT.DATE);
	    dt.addSelectionListener(new SelectionAdapter(){
	        public void widgetSelected(SelectionEvent e) {
	            System.out.println(dt.getMonth());
	            System.out.println(dt.getDay());
	            System.out.println(dt.getYear());
	        }
	    });
	    shell1.open();
	    while (!shell1.isDisposed()) {
	        if (!display.readAndDispatch()) display.sleep();
	    }
	    display.dispose();
	}
}
