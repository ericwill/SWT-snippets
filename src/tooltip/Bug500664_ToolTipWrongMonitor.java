package tooltip;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolTip;

public class Bug500664_ToolTipWrongMonitor {
	 public static void main(String[] args) {
	    	Display display = new Display();
	    	Shell shell = new Shell(display);
	    	shell.setBounds(10,10,200,200);
	    	final Text text = new Text(shell, SWT.MULTI | SWT.WRAP |
	    SWT.V_SCROLL);
	    	text.setBounds(10,10,150,150);
	    	text.setText("TextField");
	    	text.addModifyListener(new ModifyListener(){

				@Override
				public void modifyText(ModifyEvent e) {
					ToolTip t = new ToolTip(shell, SWT.BALLOON);
					t.setText("Balloon tooltip");
					t.setLocation(shell.getLocation());
					t.setVisible(true);
				}

	    	});
	    	shell.open();
	    	while (!shell.isDisposed()) {
	    		if (!display.readAndDispatch()) display.sleep();
	    	}
	    	display.dispose();
	    }

}