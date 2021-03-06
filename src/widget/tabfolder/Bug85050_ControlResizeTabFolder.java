package widget.tabfolder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class Bug85050_ControlResizeTabFolder {
	public static void main(String [] args) {
		final Display display = new Display();
		Shell shell = new Shell(display);
		
		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		System.out.println(tabFolder.computeTrim(0,0,0,0));
		tabFolder.setFont(new Font(display, "Arial", 20, SWT.ITALIC));
		System.out.println(tabFolder.computeTrim(0,0,0,0));
		final TabItem item2 = new TabItem(tabFolder, SWT.NONE);
		Button button = new Button(tabFolder, SWT.PUSH);
		button.setText("george");
		item2.setControl(button);
		Button button2 = new Button(tabFolder, SWT.PUSH);
		button2.setText("ralph");
		item.setControl(button2);
		button.addSelectionListener(new SelectionListener () {
			public void widgetDefaultSelected(SelectionEvent e) {}
			public void widgetSelected(SelectionEvent e) {
				System.out.println("before: " + tabFolder.computeTrim(0,0,0,0));
				//tabFolder.setFont(new Font(display, "Arial", 2, SWT.ITALIC));
				item2.setImage(new Image(display, 60, 60));
				System.out.println("after: " + tabFolder.computeTrim(0,0,0,0));
				//item2.dispose();
			}
		});	
		tabFolder.setBounds(0,0,200,200);
		
		shell.open();
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}
