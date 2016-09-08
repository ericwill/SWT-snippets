package z_unsorted;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class TabFolderDisposedBug {
	
	private static TabItem tab1;
	private static TabItem tab2;
	private static TabItem tab3;
	private static Composite cmp1;
	private static Composite cmp2;
	private static Composite cmp3;
	private static TabFolder folder;

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("SWT Tabitem dispose bug");
		shell.setLayout(new GridLayout(1,false));

		Button btn1 = new Button(shell, SWT.PUSH);
		btn1.setText("Show/Hide tab1");
		btn1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		btn1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tab1==null){
					tab1=new TabItem(folder, SWT.NONE, 0);
					tab1.setText("Tab 1");
					tab1.setControl(cmp1);
				}
				else {
					tab1.dispose();
					tab1=null;
				}
			}
		});
		
		folder = new TabFolder(shell, SWT.NONE);
		folder.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));

		tab1 = new TabItem(folder, SWT.NONE);
		tab1.setText("Tab 1");
		cmp1 = new Composite(folder, SWT.NONE);
		cmp1.setLayout(new FillLayout());
		Label lbl1 = new Label(cmp1,SWT.NONE);
		lbl1.setText("Inside tab1");
		tab1.setControl(cmp1);

		tab2 = new TabItem(folder, SWT.NONE);
		tab2.setText("Tab 2");
		cmp2 = new Composite(folder, SWT.NONE);
		cmp2.setLayout(new FillLayout());
		Label lbl2 = new Label(cmp2,SWT.NONE);
		lbl2.setText("Inside tab2");
		tab2.setControl(cmp2);
		
		tab3 = new TabItem(folder, SWT.NONE);
		tab3.setText("Tab 3");
		cmp3 = new Composite(folder, SWT.NONE);
		cmp3.setLayout(new FillLayout());
		Label lbl3 = new Label(cmp3,SWT.NONE);
		lbl3.setText("Inside tab3");
		tab3.setControl(cmp3);

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
