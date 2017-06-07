package widget.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class Bug209977_GCEraseItemEventBackground {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setBounds(10,10,200,200);
		final Table table = new Table(shell, SWT.NONE);
		table.setBounds(10,10,100,100);
		new TableItem(table, SWT.NONE).setText("selected item");
		Text text = new Text(shell, SWT.SINGLE);
		text.setBounds(10,140,100,30);
		table.addListener(SWT.EraseItem, new Listener() {
			public void handleEvent(Event event) {
				if ((event.detail & SWT.SELECTED) == 0) return;
				if (table.isFocusControl()) {
					System.out.print("selection background while focused: ");
				} else {
					System.out.print("selection background while not focused: ");
				}
				System.out.println(event.gc.getBackground());
			}
		});
		table.select(0);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
	
}
