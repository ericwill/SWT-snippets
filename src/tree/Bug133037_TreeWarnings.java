package tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

public class Bug133037_TreeWarnings {

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setBounds(10, 10, 200, 200);
		final Tree tree = new Tree(shell, SWT.NONE);
		tree.setBounds(10,10,150,150);
		tree.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				new TreeColumn(tree, SWT.NONE).setWidth(100);
				new TreeItem(tree, SWT.NONE);
			}
		});
		new TreeItem(tree, SWT.NONE);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
