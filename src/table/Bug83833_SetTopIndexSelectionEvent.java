package table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class Bug83833_SetTopIndexSelectionEvent {
	public static void main(String [] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		Table table = new Table(shell, SWT.NONE);
		ScrollBar scrollBar = table.getVerticalBar();
		scrollBar.setEnabled(false);
		scrollBar.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {}
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Selection event");
			}
		});
		for(int i = 0; i < 500; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, "This is item " + i);
		}
		table.setTopIndex(20);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}
