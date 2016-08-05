package testsnippets;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Bug449000_TableDrawingColumnMoved {

	public static void main(String[] args) {
		final Display display = new Display();
		Shell shell = new Shell(display);
		GridLayout layout = new GridLayout();
		shell.setLayout(layout);

		final Table table = new Table(shell, SWT.NONE);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("Column 1");
		column1.setMoveable(true);

		TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setText("Column 2");
		column2.setMoveable(true);

		TableColumn column3 = new TableColumn(table, SWT.NONE);
		column3.setText("Column 3");
		column3.setMoveable(true);

		column3.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				int height = table.getClientArea().height;
			}
		});

		TableItem item = new TableItem(table, SWT.NONE);
		item.setText(new String[] { "column 1 text", "column 2 text",
				"column 3 text" });

		for (TableColumn column : table.getColumns()) {
			column.pack();
		}

		shell.setMinimumSize(200, 200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
