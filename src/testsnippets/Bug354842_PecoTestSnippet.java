package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class Bug354842_PecoTestSnippet {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		CTabFolder folder = new CTabFolder(shell, SWT.NONE);
		CTabItem item = new CTabItem(folder, SWT.NONE);
		item.setText("Item");

		Text text = new Text(folder, SWT.NONE);
		text.setText("test item");
		item.setControl(text);

		Composite c = new Composite(folder, SWT.NONE);
		final ToolBar bar = new ToolBar(c, SWT.NONE);
		ToolItem titem = new ToolItem(bar, SWT.PUSH);
		titem.setText("Item");
		folder.setTopRight(c);
		c.setLayout(new FillLayout());

		shell.setLayout(new FillLayout());

		folder.addListener(SWT.Paint, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("Paint");
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
