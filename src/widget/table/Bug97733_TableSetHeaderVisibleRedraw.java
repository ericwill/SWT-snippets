package widget.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Bug97733_TableSetHeaderVisibleRedraw {
	static int counter = 0;
public static void main(String[] args) {
	final Display display = new Display();
	final Shell shell = new Shell(display);
	shell.setBounds(10, 10, 300, 300);
	final Table table = new Table(shell, SWT.NONE);
	table.setHeaderVisible(true);
	table.setBounds(10, 10, 200, 200);
	TableColumn column = new TableColumn(table, SWT.NONE);
	column.setText("0");
	column.setWidth(75);
	column = new TableColumn(table,SWT.CENTER);
	column.setText("1");
	column.setWidth(120);
	new TableItem(table, SWT.NONE).setText(new String[] {"abc","def"});
	shell.addListener(SWT.MouseDown, new Listener() {
		public void handleEvent(Event event) {
			if (counter == 0) {
				System.out.println("setRedraw(false)");
				table.setRedraw(false);
				counter++;
				return;
			}
			if (counter == 1) {
				System.out.println("setHeaderVisible(true): overwrites item");
				table.setHeaderVisible(false);
				//widget.table.setColumnOrder(new int[] {1,0});
				counter++;
				return;
			}
			if (counter == 2) {
				System.out.println("setRedraw(true), item reappears");
				table.setRedraw(true);
				counter++;
				return;
			}
			System.out.println("nothing left to do");
		}
	});
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}
	display.dispose();
}
}
