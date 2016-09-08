package tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class Bug465054_PaintItemTestSnippet {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shellMain = new Shell(display, SWT.SHELL_TRIM);
		shellMain.setLayout(new FillLayout(SWT.VERTICAL));

		Tree tree = new Tree(shellMain, SWT.BORDER);
		new TreeItem(tree, SWT.NONE).setText("setText");
		for (int i = 0; i < 100; i++) {
			new TreeItem(tree, SWT.NONE).setText("setText " + i);
		}

		tree.addListener(SWT.PaintItem, new Listener() {
			@Override
			public void handleEvent(Event event) {
				System.out.println("PaintItem");
				Rectangle bounds = ((TreeItem) event.item).getBounds(event.index);
				event.gc.fillRectangle(bounds.x, bounds.y, bounds.width, bounds.height);
				event.gc.drawText(((TreeItem) event.item).getText(), bounds.x, bounds.y, true);
			}
		});
		

		shellMain.open();

		while (!shellMain.isDisposed()) {
			try {
				if (!display.readAndDispatch())
					display.sleep();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		display.dispose();
	}
}
