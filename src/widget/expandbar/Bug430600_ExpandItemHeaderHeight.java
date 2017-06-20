package widget.expandbar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
public class Bug430600_ExpandItemHeaderHeight {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		ExpandBar expandBar = new ExpandBar(shell, SWT.NONE);

		final ExpandItem xpndtmTest = new ExpandItem(expandBar, SWT.NONE);
		xpndtmTest.setExpanded(true);
		
		System.out.println(xpndtmTest.getHeaderHeight()); //Correct 
		xpndtmTest.setHeight(100);
		System.out.println(xpndtmTest.getHeaderHeight()); //Some negative number
	}
}