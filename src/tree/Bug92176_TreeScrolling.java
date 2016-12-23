package tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class Bug92176_TreeScrolling {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("PR");

		final Tree tree = new Tree (shell, SWT.BORDER);
		tree.setSize (100, 100);
		shell.setSize (200, 200);

	final TreeItem[] items = new TreeItem[100];
		items[0] = new TreeItem(tree, 0);
		items[0].setText("elt"+0);
		int n = 50;
		for (int i = 1; i < n; i++) {
			items[i] = new TreeItem(items[i-1], 0);
			items[i].setText("child"+i);
		}
		for (int i = n; i < items.length; i++) {
			items[i] = new TreeItem(tree, 0);
			items[i].setText("elt "+i);
		}

		Button button = new Button(shell, SWT.PUSH);
		button.setBounds(120,0,50,20);
		button.setText("show");
		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				tree.showItem(items[45]);
			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}		
	}
}
