package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug500814_GLCanvasCrash {

	
	 public static void main(String[] args) {
		 Display display = new Display();
		 Shell shell = new Shell(display); 
		 
		 GLCanvas canvas;
		 GLData data = new GLData();
         data.doubleBuffer = true;
         data.depthSize = 24;
         data.alphaSize = 8;
         data.blueSize = 8;
         data.redSize = 8;
         data.greenSize = 8;
         data.stencilSize = 8;
         data.sampleBuffers = 1;
         data.samples = 4;
         canvas = new GLCanvas(shell, SWT.NONE, data);
         canvas.setCurrent(); // <-- it crashes here...
  }
}
