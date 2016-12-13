package table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class Snippet_DefaultSelectionEvent {
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

			/**
			 * Selection event occurs when clicking or using arrow keys.
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectionType = "Selection event";
				modifierLogic(e, selectionType);
			}

			/**
			 * Default selection triggered upon double click or 'enter' button.
			 */
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				String selectionType = "Default selection event"; 
				modifierLogic(e, selectionType);
			}

			/**
			 * @param e
			 * @param selectionType
			 */
			public void modifierLogic(SelectionEvent e, String selectionType) {
				
				if (e.stateMask == 0) {
					System.out.println(selectionType + "\n"
							+ "		Gtk/Win32/Cocoa: Enter or click (no mod)");
				}
				
				if ((e.stateMask & SWT.MOD1) != 0 | ((e.stateMask & SWT.MOD4) != 0)) {
					System.out.println(selectionType + "\n"
							+ "		Gtk:   Ctrl+Enter or Ctrl+Click (mod1) \n"
							+ "		Win32: Ctrl+Enter does not trigger default selection. Ctrl+Enter does\n"
							+ "		Cocoa: Ctrl+Enter/Click+Enter  (Mod4) or Cmd+Enter/Cmd+Click (Mod1) \n");
				}

				if ((e.stateMask & SWT.MOD2) != 0) {
					System.out.println(selectionType + "\n"
							+ "		Gtk/Win32/Cocoa: Shift+Enter or Shift+Click(Mod2) \n");
				}
				
				if ((e.stateMask & SWT.MOD3) != 0) {
					System.out.println(selectionType + "\n"
							+ "		Gtk/Win32/Cocoa: Alt+Enter or Alt+click (Mod3)");     
				}
			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}
