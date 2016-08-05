package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Bug97527_LabelSizing {
	private static Text text;
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		GridLayout layout = new GridLayout(2,false);
		layout.numColumns = 2;
		shell.setLayout(layout);
		Label label = new Label(shell, SWT.WRAP);
		label.setText("here is a long piece of text which might wrap or might not depending on the platform at runtime" +
		" here is a long piece of text which might wrap or might not depending on the" + 
		"platform at runtime");
		final GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1);
		label.setLayoutData(gridData);

		shell.pack();
		System.out.println(shell.getSize());
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}
