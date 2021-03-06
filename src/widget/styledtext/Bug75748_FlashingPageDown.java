package widget.styledtext;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug75748_FlashingPageDown {

	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setLayout(new FillLayout());
		StyledText s = new StyledText(shell, SWT.H_SCROLL |SWT.V_SCROLL);
		s.setFont(new Font(display, "Monospace", 10, SWT.NONE));
		String mytext = new String();
		int i, n = 256;
		for(i = 0; i < n; i++) {
			for(int j = 0; j < 72; j++) mytext += (j % 10);
			mytext += " [" + i + "]\n";
		}
		s.setText(mytext);
		shell.setSize(400,400);
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
	
}
