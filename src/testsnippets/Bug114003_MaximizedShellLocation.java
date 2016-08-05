package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug114003_MaximizedShellLocation
{
	public static void main(String[] args)
	{
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new RowLayout());
		shell.setMaximized(true);
		Button b = new Button(shell, SWT.NONE);
		b.setLayoutData (new RowData (100, SWT.DEFAULT));

		b.setText("hide");
		b.addSelectionListener(new SelectionAdapter()
		{

			public void widgetSelected(SelectionEvent arg0)
			{
				shell.setVisible(false);
				new Thread()
				{
					public void run()
					{
						try
						{
							Thread.sleep(4000);
						}
						catch (InterruptedException e)
						{
						}
						
						Display.getDefault().asyncExec(new Runnable()
						{
							public void run()
							{
								shell.setVisible(true);
							}
						});
						
					}
				}.start();
			}
		});

		shell.open();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
		System.err.println("ended");
	}
}