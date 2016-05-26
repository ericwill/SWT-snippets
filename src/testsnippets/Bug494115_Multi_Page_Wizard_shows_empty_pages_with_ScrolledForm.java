package testsnippets;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * Creates and opens a wizard dialog with two simple wizard pages.
 */
public class Bug494115_Multi_Page_Wizard_shows_empty_pages_with_ScrolledForm {

	static class WizardPageOne extends WizardPage {

		public WizardPageOne(String pageName, String title, ImageDescriptor titleImage) {
			super(pageName, title, titleImage);
		}

		public WizardPageOne(String pageName) {
			super(pageName);
		}
		
		@Override
		public void createControl(Composite parent) {
			FormToolkit toolkit = new FormToolkit(parent.getDisplay());
			ScrolledForm form = toolkit.createScrolledForm(parent);
			System.out.println("hello world");
			form.setText("Sample form");
			//form.getBody().setLayout(new GridLayout());
			//toolkit.createButton(form.getBody(), "Checkbox", SWT.CHECK);
			form.reflow(true);
			setControl(form);
		}
	}

	static class SampleWizard extends Wizard {

		@Override
		public void addPages() {			
			addPage(new WizardPageOne("First page"));
		}

		@Override
		public String getWindowTitle() {
			return "title meh.";
		}

		@Override
		public boolean performFinish() {
			return true;
		}

	}

	public static void main(String[] args) {
		Display display = Display.getCurrent();
		final Shell shell = new Shell(display);
		//shell.setSize(350, 200);
		//shell.open();
		
		
		IWizard wizard = new SampleWizard();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
		// The SWT event loop
		while (dialog.getShell() != null && !dialog.getShell().isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
