package widget.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Bug202013_ShellControlDisposed {

	public static void main(String[] args) {
		Display display = new Display();
		final Listener listener = new Listener() {
			public void handleEvent(Event event) {
				System.out.println(event);
			}
		};
		display.addFilter(SWT.KeyDown, listener);
		display.addFilter(SWT.Traverse, listener);
		Shell shell = new Shell();
		shell.setLayout(new FillLayout());
		shell.setSize(300, 75);

		final Composite c = new Composite(shell, SWT.NONE);
		c.setLayout(new FillLayout());
		
		Button b = new Button(c, SWT.PUSH);
		b.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event) {	
				c.dispose();
			}
		});

		shell.open();

		while (!shell.isDisposed()) {
			if (display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
}
