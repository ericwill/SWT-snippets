package z_unsorted;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class Bug500631_RHELIcons {
	
	public Bug500631_RHELIcons(Display display){
		
		Shell shell = new Shell(display);
        shell.setLayout(new RowLayout());
        shell.setText("Photo Application");
        shell.setSize(500, 500);
        
        //creating a widget.menu
        Composite composite = new Composite(shell, SWT.NONE); 
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        composite.setLayout(gridLayout);
        
        Menu menu = new Menu(shell,SWT.BAR); //root widget.menu bar

        MenuItem menuItem = new MenuItem(menu,SWT.CASCADE); //adding a widget.menu item to hold each drop-down widget.menu
        menuItem.setText("Menu");
   
        Menu fileMenu = new Menu(menuItem);
      
        MenuItem menuItem1 = new MenuItem(fileMenu, SWT.PUSH);
   
        menuItem1.setText("ADD");
        menuItem1.setImage(Display.getDefault().getSystemImage(SWT.ICON_WORKING));
        
        MenuItem menuItem2 = new MenuItem(fileMenu,SWT.PUSH);
        menuItem2.setText("RMB");
        menuItem2.setImage(Display.getDefault().getSystemImage(SWT.ICON_WORKING));
        
        menuItem.setMenu(fileMenu);
        
        shell.setMenuBar(menu);
        shell.pack();
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Display display = new Display();
		 Bug500631_RHELIcons m = new Bug500631_RHELIcons(display);
		 display.dispose();
		
	}

}