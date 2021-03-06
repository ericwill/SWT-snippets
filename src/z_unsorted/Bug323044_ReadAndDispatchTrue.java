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
package z_unsorted;

/*
 * UI Automation (for testing tools) snippet: post key events
 *
 * For a widget.list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.0
 */
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

public class Bug323044_ReadAndDispatchTrue {

public static void main(String[] args) {
    final Display display = new Display();
    final Shell shell = new Shell(display);
    final Text text = new Text(shell, SWT.BORDER);
    text.setSize(text.computeSize(150, SWT.DEFAULT));
    shell.pack();
    shell.open();
    new Thread(){
        public void run(){
            Event keyEvent = new Event();
            Event charEvent = new Event();
            System.out.println("STARTING");
            for (int i = 0; i < 5000; i++) {
                System.out.println(i);
                keyEvent.type = SWT.KeyDown;
                keyEvent.keyCode = SWT.MOD3;
                display.post(keyEvent);    

                charEvent.type = SWT.KeyDown;
                charEvent.character = 'a';
                display.post(charEvent);

                charEvent.type = SWT.KeyUp;
                charEvent.character = 'a';
                display.post(charEvent);

                keyEvent.type = SWT.KeyUp;
                keyEvent.keyCode=SWT.MOD3;
                display.post(keyEvent);
            }
        }    
    }.start();
    while (!shell.isDisposed()) {
        boolean readAndDispatch = display.readAndDispatch();
        System.out.println("readAndDispatch: "+ readAndDispatch);
        if (!readAndDispatch) display.sleep();
    }



    display.dispose();
}
}