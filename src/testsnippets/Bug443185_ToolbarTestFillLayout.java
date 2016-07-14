package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class Bug443185_ToolbarTestFillLayout {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(200, 400);
		shell.setLayout(new GridLayout());
		
	    // Create the tabs
	    CTabFolder tabFolder = new CTabFolder(shell, SWT.TOP|SWT.BORDER);
	    tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true) );
	    tabFolder.setMaximizeVisible(true);
	    tabFolder.setMinimizeVisible(true);
	    
	    CTabItem item=new CTabItem(tabFolder, SWT.BORDER);
	    item.setText("Tab (1)");
	    item.setShowClose(true);
	    Label content=new Label(tabFolder,SWT.NONE);
	    content.setText("bla");
	    item.setControl(content);
	    
	    Composite composite = new Composite(tabFolder, SWT.NONE);
	    composite.setLayout(new FillLayout(SWT.HORIZONTAL));
	    
	    
	    ToolBar toolBar=new ToolBar(composite, SWT.FLAT|SWT.RIGHT|SWT.WRAP);
	    for(int i=0;i<15;i++){
		    ToolItem toolItem=new ToolItem(toolBar, SWT.PUSH);
		    toolItem.setText("test_"+i);
		    if(i%5==0){
		    	new ToolItem(toolBar, SWT.SEPARATOR);
		    }
	    }
	    tabFolder.setTopRight(composite,SWT.RIGHT | SWT.WRAP);
	    
	    
	    //SWT Loop
		shell.open();
		while (!shell.isDisposed()) {
		  if (!display.readAndDispatch())
		   {
		    display.sleep();
		   }
		}
		display.dispose(); 
	}

}