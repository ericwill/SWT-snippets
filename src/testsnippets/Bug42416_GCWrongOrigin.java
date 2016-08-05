package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Bug42416_GCWrongOrigin {
	
	public static void main(String[] args) {
		Display display = new Display();
		final Color red = display.getSystemColor(SWT.COLOR_RED);
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		final Table table = new Table(shell, SWT.BORDER);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		TableColumn column1 = new TableColumn(table, SWT.NONE);
		TableColumn column2 = new TableColumn(table, SWT.NONE);
		TableColumn column3 = new TableColumn(table, SWT.NONE);
		for (int i = 0; i < 10; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] {"asdadas", "asdadasd", "rrwrwrw"});
		}
		column1.pack();
		column2.pack();
		column3.pack();
		table.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				System.out.println("mouse down at "+e.x+" "+e.y);
				GC gc = new GC(table);
				gc.setForeground(red);
				gc.drawRectangle(e.x, e.y, 2, 2);
				gc.dispose();
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
