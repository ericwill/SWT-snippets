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
				if ((e.stateMask & SWT.MOD1) != 0 | ((e.stateMask & SWT.MOD4) != 0)) {
					System.out.println("Selection event" + "\n"
							+ "		Cocoa: Ctrl+Click (Mod4) or Cmd+Click (Mod1) \n"
							+ "		Gtk:   Ctrl+Click (mod1) \n"
							+ "		Win32: Ctrl+Click (mod1)\n");
				} else if ((e.stateMask & SWT.MOD2) != 0) {
					System.out.println("Selection event" + "\n"
							+ "		Gtk/Win32/Cocoa: Shift+Click (Mod2) \n");
				} else if ((e.stateMask & SWT.MOD3) != 0) {
					System.out.println("Selection event" + "\n"
							+ "		Gtk/Win32/Cocoa: Alt+click (Mod3)");     
				} else {  // Do not use (e.stateMask == 0), as on Win32/Cocoa StateMask is not 0 when no modifiers are pressed.
					System.out.println("Selection event" + "\n"
							+ "		Cocoa:  Arrow keys and only on double click. (This will be followed by default selection)\n"
							+ "		Gtk:    Arrow keys and single click \n"
							+ "		Win32:  Arrow keys only");
				}
			}

			/**
			 * Default selection triggered upon double click or 'enter' button.
			 */
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if ((e.stateMask & SWT.MOD1) != 0 | ((e.stateMask & SWT.MOD4) != 0)) {
					System.out.println("Default selection event" + "\n"
							+ "		Cocoa: Ctrl+Enter (Mod4) or Cmd+Enter/Cmd+DoubleClick (Mod1) \n"
							+ "		Gtk:   Ctrl+Enter or Ctrl+Double (mod1) \n"
							+ "		Win32: Ctrl+Enter does NOT trigger default selection.\n");
				} else if ((e.stateMask & SWT.MOD2) != 0) {
					System.out.println("Default selection event" + "\n"
							+ "		Gtk/Win32/Cocoa: Shift+Enter or Shift+DoubleClick(Mod2) \n");
				} else if ((e.stateMask & SWT.MOD3) != 0) {
					System.out.println("Default selection event" + "\n"
							+ "		Gtk/Win32/Cocoa: Alt+Enter or Alt+DoubleClick (Mod3)");     
				} else {
					System.out.println("Default selection event " + e.stateMask + "\n"
							+ "		Gtk/Win32/Cocoa: Enter or double click (no mod)");
				}
			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}
