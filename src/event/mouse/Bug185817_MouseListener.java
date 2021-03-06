package event.mouse;

/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

/*
 * TableEditor example snippet: edit the widget.text of a widget.table item (in place)
 *
 * For a widget.list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Bug185817_MouseListener {
        
public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        final Table table = new Table(shell, SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
        TableColumn column1 = new TableColumn(table, SWT.NONE);
        TableColumn column2 = new TableColumn(table, SWT.NONE);
        for (int i = 0; i < 10; i++) {
                TableItem item = new TableItem(table, SWT.NONE);
                item.setText(new String[] {"item " + i, "edit this value"});
        }
        column1.pack();
        column2.pack();
        
        final TableEditor editor = new TableEditor(table);
        //The editor must have the same size as the cell and must
        //not be any smaller than 50 pixels.
        editor.horizontalAlignment = SWT.LEFT;
        editor.grabHorizontal = true;
        editor.minimumWidth = 50;
        // editing the second column
        final int EDITABLECOLUMN = 1;
        
        table.addMouseListener(new MouseListener() {

                public void mouseDoubleClick(MouseEvent e) {
//                	 // Clean up any previous editor control
//                    Control oldEditor = editor.getEditor();
//                    if (oldEditor != null) oldEditor.dispose();
//    
//                    // Identify the selected row
//                    TableItem item = (TableItem)widget.table.getItem(new Point(e.x,e.y));
//                    if (item == null) return;
//    
//                    // The control that will be the editor must be a child of the Table
//                    Text newEditor = new Text(widget.table, SWT.NONE);
//                    newEditor.setText(item.getText(EDITABLECOLUMN));
//                    newEditor.addModifyListener(new ModifyListener() {
//                            public void modifyText(ModifyEvent me) {
//                                    Text widget.text = (Text)editor.getEditor();
//                                    editor.getItem().setText(EDITABLECOLUMN,widget.text.getText());
//                            }
//                    });
//                    newEditor.selectAll();
//                    newEditor.setFocus();
//                    editor.setEditor(newEditor, item, EDITABLECOLUMN);
//                    newEditor.addFocusListener(new FocusAdapter() {
//
//						public void focusLost(FocusEvent e) {
//							Control oldEditor = editor.getEditor();
//	                        if (oldEditor != null) oldEditor.dispose();
//						}
//                    	
//                    });
                }

                public void mouseDown(MouseEvent e) {
                	if( e.count == 2 ) {
                		System.err.println("STARTING UP THE EDITOR");
                   	 // Clean up any previous editor control
                        Control oldEditor = editor.getEditor();
                        if (oldEditor != null) oldEditor.dispose();
        
                        // Identify the selected row
                        TableItem item = (TableItem)table.getItem(new Point(e.x,e.y));
                        if (item == null) return;
        
                        // The control that will be the editor must be a child of the Table
                        Text newEditor = new Text(table, SWT.NONE);
                        newEditor.setText(item.getText(EDITABLECOLUMN));
                        newEditor.addModifyListener(new ModifyListener() {
                                public void modifyText(ModifyEvent me) {
                                        Text text = (Text)editor.getEditor();
                                        editor.getItem().setText(EDITABLECOLUMN,text.getText());
                                }
                        });
                        newEditor.selectAll();
                        newEditor.setFocus();
                        editor.setEditor(newEditor, item, EDITABLECOLUMN);
                        newEditor.addFocusListener(new FocusAdapter() {

    						public void focusLost(FocusEvent e) {
    							System.err.println("LOOSING FOCUS");
    							Control oldEditor = editor.getEditor();
    	                        if (oldEditor != null) oldEditor.dispose();
    						}
                        	
                        });                		
                	}
                }

                public void mouseUp(MouseEvent e) {
                        // TODO Auto-generated method stub
                        
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