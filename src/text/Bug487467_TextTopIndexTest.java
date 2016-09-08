/*******************************************************************************
 * Copyright (c) 2000, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package text;
 
/*
 * Text example snippet: set the selection (start, end),
 * and then scroll the selection to the top of the client area
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Bug487467_TextTopIndexTest {

public static void main (String [] args) {
	Display display = new Display ();
	Shell shell = new Shell (display);
	Text text = new Text (shell, SWT.BORDER | SWT.V_SCROLL);
	Rectangle clientArea = shell.getClientArea ();
	text.setBounds (clientArea.x + 10, clientArea.y + 10, 100, 100);
	for (int i=0; i<50; i++) {
		text.append ("Line " + i + "\n");
	}
	shell.open ();

//	text.setSelection (0, 5);
//	text.setTopIndex(20);
//	System.out.println(text.getTopIndex());
//	System.out.println ("selection=" + text.getSelection ());
//	System.out.println ("selection text=" + text.getSelectionText ());
//	System.out.println ("caret position=" + text.getCaretPosition ());
//	System.out.println ("caret location=" + text.getCaretLocation ());
//	while (!shell.isDisposed ()) {
//		if (!display.readAndDispatch ()) display.sleep ();

//	text.setSelection (0, 5);
	text.setTopIndex(20);
	int perm = text.getTopIndex();
//	System.out.println ("selection=" + text.getSelection ());
//	System.out.println ("selection text=" + text.getSelectionText ());
//	System.out.println ("caret position=" + text.getCaretPosition ());
//	System.out.println ("caret location=" + text.getCaretLocation ());
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
		if (text.getTopIndex() != 20) {
			System.out.println("initial " + perm + " " + "current " + text.getTopIndex());
		}

	}
	display.dispose ();
} 
}