package widget.shell;

//import static org.junit.Assert.*;

import org.eclipse.swt.widgets.Shell;
//import org.junit.Test;

public class Bug336238_ShellBoundsTest {

  static int cycles = 5;
 	
//  @Test
  public void testSetBounds() {
		
    int x;
    int y;
    int width = 100;
    int height = 100;
		
    for (int i=0; i < cycles; i++) {
			
      x = (new Double(Math.random()*1000)).intValue();
      y = (new Double(Math.random()*1000)).intValue();
			
      Shell testShell = new Shell();
      testShell.open();
			
      testShell.setBounds(x, y, width, height);
			
//      assertEquals(x, testShell.getLocation().x);
//      assertEquals(y, testShell.getLocation().y);
      testShell.close();
    }		
  }	
}