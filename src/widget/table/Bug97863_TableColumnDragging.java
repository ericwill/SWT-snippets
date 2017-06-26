package widget.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class Bug97863_TableColumnDragging {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setBounds(10,10,400,400);
		final Table tree = new Table(shell, SWT.NONE);
		tree.setBounds(10,10,300,300);
		tree.setHeaderVisible(true);
		ControlListener controlListener = new ControlListener() {
			public void controlResized(ControlEvent e) {
				System.out.println("column resized: " + e.widget);
			}
			public void controlMoved(ControlEvent e) {
				System.out.println("column moved: " + e.widget);
			}
		};
		TableColumn column = new TableColumn(tree, SWT.NONE);
		column.setWidth(50);
		column.setText("col 0");
		column.setMoveable(true);
		column.addControlListener(controlListener);
		column = new TableColumn(tree, SWT.NONE);
		column.setWidth(50);
		column.setText("col 1");
		column.setMoveable(true);
		column.addControlListener(controlListener);
		column = new TableColumn(tree, SWT.NONE);
		column.setWidth(50);
		column.setText("col 2");
		column.setMoveable(true);
		column.addControlListener(controlListener);
		column = new TableColumn(tree, SWT.NONE);
		column.setWidth(50);
		column.setText("col 3");
		column.setMoveable(true);
		column.addControlListener(controlListener);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}