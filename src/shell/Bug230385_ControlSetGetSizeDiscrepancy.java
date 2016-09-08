package shell;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug230385_ControlSetGetSizeDiscrepancy {

	private static final int SIZE_X = 500;
	private static final int SIZE_Y = 350;
	
	public static void main(String[] args) {

	    final Display display = new Display();
	    final Shell shell = new Shell();
	    
	    shell.setSize(SIZE_X, SIZE_Y);
	    shell.open();
	    
	    System.out.println("Size should be  : " + SIZE_X + "x" + SIZE_Y);
	    System.out.println("Size actually is: " + shell.getSize().x + "x" +
	        shell.getSize().y + " [as returned by Control.getSize()]");
	    
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch()) {
	        display.sleep();
	      }
	    }
	    
	    display.dispose();
	    
	  }
	
}
