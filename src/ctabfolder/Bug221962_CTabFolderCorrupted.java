package ctabfolder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug221962_CTabFolderCorrupted {

	public static final void main(final String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText( "CTabFolder Test" );
		shell.setLayout( new FillLayout() );
		CTabFolder folder = new CTabFolder( shell, SWT.BORDER|SWT.FLAT );
	
		folder.setSimple(false);
		CTabItem item;
		item = new CTabItem(folder,SWT.NONE);
		item.setText("First");
		item = new CTabItem(folder, SWT.CLOSE);
		item.setText("Second");
	
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
		  if (!display.readAndDispatch()) {
		    display.sleep();
		  }
		}

	}
}
