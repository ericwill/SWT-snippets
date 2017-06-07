package widget.tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class Bug133039_MeasureItemRemoval {
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setBounds(10, 10, 400, 400);
		final Tree tree = new Tree(shell, SWT.NONE);
		tree.setBounds(10,10,350,350);
		final Listener measureListener = new Listener() {
			public void handleEvent(Event event) {
				tree.removeListener(SWT.MeasureItem, this); // <---
				event.height *= 4;
				display.asyncExec(new Runnable() {	/* asyncExec works around GP */
					public void run() {
						final TreeItem item0 = new TreeItem(tree, SWT.NONE);
						final TreeItem item1 = new TreeItem(tree, SWT.NONE);
						item0.setText(new String[] {"item 1"});
						item1.setText(new String[] {"item 2"});
					}
				});
			}
		};
		tree.addListener(SWT.MeasureItem, measureListener);
		shell.open();
		new TreeItem(tree, SWT.NONE).setText("item 0");
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}
