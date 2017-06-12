package widget.tree;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;


public class Bug399522_TreeColumnNumber {
public static void main(String[] args) {
	Display display = new Display();
	Shell shell = new Shell(display);

	final Table table = new Table(shell, SWT.BORDER);
	new TableItem(table, SWT.NONE).setText("Item1");
	TableColumn column1 = new TableColumn(table, SWT.NONE);
	
	table.setSize(200, 200);
	
	shell.addListener(SWT.MouseDown, new Listener() {
		public void handleEvent(Event event) {
			System.out.println(table.computeSize(-1, -1));
		}
	});

	shell.setSize(300, 300);
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}
	display.dispose();
}
}