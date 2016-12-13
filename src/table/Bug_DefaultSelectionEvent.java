package table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class Bug_DefaultSelectionEvent {
	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		Table table = new Table (shell, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		for (int i=0; i<12; i++) {
			TableItem item = new TableItem (table, 0);
			item.setText ("Item " + i);
		}
		Rectangle clientArea = shell.getClientArea ();
		table.setBounds (clientArea.x, clientArea.y, 400, 400);
		shell.setSize (400, 400);
		shell.open ();

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Table widget selection occured" + e.stateMask);
			}

			/**
			 * Pressed when En
			 */
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if ((e.stateMask & SWT.MOD1) != 0) {
					System.out.println("Default selection event. Mod1 Control held down");
				}
				if ((e.stateMask & SWT.MOD2) != 0) {
					System.out.println("Default selection event. Mod2 Shift held down");
				}
				if ((e.stateMask & SWT.MOD3) != 0) {
					System.out.println("Default selection event. Mod3 Alt held down");
				}
				if ((e.stateMask & SWT.MOD4) != 0) {
					System.out.println("Default selection event. Mod4 held down (not widley supported)");
				}

				if (e.stateMask == 0) {
					System.out.println("Default selection event. No modifiers pressed");
				}
			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}
