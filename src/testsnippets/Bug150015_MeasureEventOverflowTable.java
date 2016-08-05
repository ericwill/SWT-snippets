package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class Bug150015_MeasureEventOverflowTable {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setBounds(10,10,200,200);
		final Table table = new Table(shell, SWT.NONE);
		table.setBounds(10,10,150,150);
		new TableItem(table, SWT.NONE).setText("item");
		table.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				int a = table.getClientArea().width;	// <---
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}
