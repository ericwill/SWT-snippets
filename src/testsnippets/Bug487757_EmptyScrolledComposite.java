package testsnippets;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.internal.forms.widgets.TitleRegion;

/**
 * Creates and opens a wizard dialog with two simple wizard pages.
 */
public class Bug487757_EmptyScrolledComposite {

	
	
	/* Snippet bits */
	private static void makeSWTSnippet() {
		
		// Copied from Snippet 5.
		Display display = new Display ();
	    Shell shell = new Shell (display);
	    shell.setLayout(new FillLayout());

	    ScrolledComposite c2 = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    Button b2 = new Button(c2, SWT.PUSH);
	    b2.setText("expanding button");
	    c2.setContent(b2);
	    c2.setExpandHorizontal(true);
	    c2.setExpandVertical(true);
	    c2.setMinSize(400, 400);

	    // MODIFICATION from Snippet 5.
	    c2.setVisible(false);
	    c2.setVisible(true);
	    
	    shell.setSize(600, 300);
	    shell.open ();
	    while (!shell.isDisposed ()) {
	        if (!display.readAndDispatch ()) display.sleep ();
	    }
	    display.dispose ();
	}
	
	public static void main(String[] args) {
		// Config:
		int to_run = 2;
		
		// Execution:
		if (to_run == 1)
			makeWizzard();
		else if (to_run == 2)
			makeSWTSnippet();
		
	}

	/* Wizzard bits */
		static class WizardPageOne extends WizardPage {
	
			public WizardPageOne(String pageName, String title, ImageDescriptor titleImage) {
				super(pageName, title, titleImage);
			}
	
			public WizardPageOne(String pageName) {
				super(pageName);
			}
			
			@Override
			public void createControl(Composite parent) {
	
				// Using Scrolled Composite ===> Gtk3: Button does not fill Scrolled composite. Gtk2: Button fills Scrolled composite.
				ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
				sc.setExpandHorizontal(true);
				sc.setExpandVertical(true);
				Button button = new Button(sc, SWT.PUSH);
				button.setText("hello world!");
				sc.setContent(button);
				setControl(sc);
				
				// Original jFace From layout.
	//     	FormToolkit toolkit = new FormToolkit(parent.getDisplay());
	//			ScrolledForm form = toolkit.createScrolledForm(parent);
	//			System.out.println("hello world");
	//			form.setText("Sample form");
	//			//form.getBody().setLayout(new GridLayout());
	//			//toolkit.createButton(form.getBody(), "Checkbox", SWT.CHECK);
	//			form.reflow(true);
	//			setControl(form);     /// <<< Note
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

		private static void makeWizzard() {
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