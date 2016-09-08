package shell;

import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;



public class Bug215268_ShellSetVisibleFront {



	public static void main(String[] args) {

		Display display = new Display ();

		Shell shell = new Shell (display, SWT.SHELL_TRIM);

		shell.setText("Shell1");

		shell.open();

		try

		{

			Thread.sleep(2000);

		}

		catch(Throwable t)

		{

			t.printStackTrace();

		}

		Shell shell2 = new Shell (display, SWT.SHELL_TRIM);

		shell2.setText("Shell 2, opened with setVisible(true)");

		shell2.moveBelow(shell);

		shell2.setVisible(true);

		while (!shell.isDisposed ()) {

			if (!display.readAndDispatch ()) display.sleep ();

		}

		display.dispose ();



	}


}


