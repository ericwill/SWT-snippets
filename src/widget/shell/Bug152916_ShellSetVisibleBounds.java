package widget.shell;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug152916_ShellSetVisibleBounds {
	public static void main(String[] args) {
	    Display display = new Display();
	    
	    Shell shell = new Shell(display);
	    shell.open();
	    shell.setBounds(200, 200, 200, 200);
	    System.out.println("1=" + shell.getBounds());
	    shell.setVisible(false);
	    System.out.println("2=" + shell.getBounds());
	    shell.setVisible(true);
	    System.out.println("3=" + shell.getBounds());
	    
	    display.dispose();
	  }
}
