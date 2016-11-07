package table;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.custom.*;

public class Bug166720_TableEditorFlicker {
public static void main(String[] args) {
    Display display = new Display ();
    Shell shell = new Shell (display);
    shell.setLayout (new GridLayout ());
    Table table = new Table (shell, SWT.BORDER | SWT.MULTI);
    GridData gd = new GridData(GridData.FILL_BOTH);
    gd.heightHint = 200;
    gd.widthHint = 200;
    table.setLayoutData(gd);

    TableColumn column = new TableColumn(table, SWT.NONE);
    column.setWidth (100);
    for (int i=0; i<100; i++) {
        new TableItem (table, SWT.NONE);
    }
    TableItem [] items = table.getItems ();
    for (int i=0; i<items.length; i++) {
        TableEditor editor = new TableEditor (table);
        editor = new TableEditor (table);
        Text text = new Text (table, SWT.NONE);
        text.setText("Text" + i);
        editor.grabHorizontal = true;
        editor.setEditor(text, items[i], 0);
        editor = new TableEditor (table);
    }
    shell.pack ();
    shell.open ();
    while (!shell.isDisposed ()) {
        if (!display.readAndDispatch ()) display.sleep ();
    }
    display.dispose ();
}
}