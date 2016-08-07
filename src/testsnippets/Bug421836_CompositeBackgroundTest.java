package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/*
 * NOTE: this snippet has a JFace/Platform UI dependency. If you would like to
 * make use of this snippet, please checkout the necessary sources and modify
 * the classpath manually.
 */

public class Bug421836_CompositeBackgroundTest {

	public static void main(String[] args) {
		final Display display = new Display();

		Color cyan = new Color(display, new RGB(0, 255, 255));
		Color pink = new Color(display, new RGB(255, 0, 255));
		
		Shell shell = new Shell(display, SWT.CLOSE);
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		
		FormToolkit tk = new FormToolkit(display);
		Section section = new Section(shell, Section.DESCRIPTION | Section.TITLE_BAR);
		section.setBackground(pink);
		section.setTitleBarBackground(cyan);
		section.setText("looooooooooooooooooooooong Section title, My background is CYAN");
		section.setDescription("Section description with PINK background");
		Composite composite = tk.createComposite(section, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.VERTICAL));
		tk.createLabel(composite, "a label in a section");
		tk.createLabel(composite, "parent bg-mode=" + composite.getBackgroundMode());
		tk.createLabel(composite, "section bg-mode=" + section.getBackgroundMode());
		section.setClient(composite);
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in event queue
				display.sleep();
			}
		}
		cyan.dispose();
		pink.dispose();
		display.dispose();
	}
}