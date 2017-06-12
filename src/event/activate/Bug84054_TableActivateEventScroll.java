package event.activate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class Bug84054_TableActivateEventScroll {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setBounds(10,10,400,400);
		final Table table = new Table(shell, SWT.NONE);
		table.setBounds(10,10,100,100);
		for (int i = 0; i < 99; i++) {
			new TableItem(table, SWT.NONE).setText("item " + i);
		}
		
		final Table table2 = new Table(shell, SWT.NONE);
		table2.setBounds(10,130,100,100);
		for (int i = 0; i < 99; i++) {
			new TableItem(table2, SWT.NONE).setText("item " + i);
		}
		
		table.addListener(SWT.Activate, new Listener() {

			public void handleEvent(Event arg0) {
				System.out.println(" 1 activated");
				
			}});
		
		table.addListener(SWT.Deactivate, new Listener() {

			public void handleEvent(Event arg0) {
				System.out.println(" 1 deactivated");
				
			}});
		
		table2.addListener(SWT.Activate, new Listener() {

			public void handleEvent(Event arg0) {
				System.out.println(" 2 activated");
				
			}});
		
		table2.addListener(SWT.Deactivate, new Listener() {

			public void handleEvent(Event arg0) {
				System.out.println(" 2 deactivated");
				
			}});
		
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}
