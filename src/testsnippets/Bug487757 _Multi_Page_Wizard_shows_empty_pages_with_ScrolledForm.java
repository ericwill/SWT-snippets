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
public class Bug494115_Multi_Page_Wizard_shows_empty_pages_with_ScrolledForm {

	
	
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

	
	
	/* Snippet bits */
	private static void makeSWTSnippet() {
		
		// Copied from Snippet 5.
		 Display display = new Display ();
	    Shell shell = new Shell (display);
	    shell.setLayout(new FillLayout());

	    // this button is always 400 x 400. Scrollbars appear if the window is resized to be
	    // too small to show part of the button
	    ScrolledComposite c1 = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    Button b1 = new Button(c1, SWT.PUSH);
	    b1.setText("fixed size button");
	    b1.setSize(400, 400);
	    c1.setContent(b1);

	    // this button has a minimum size of 400 x 400. If the window is resized to be big
	    // enough to show more than 400 x 400, the button will grow in size. If the window
	    // is made too small to show 400 x 400, scrollbars will appear.
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
		int to_run = 1;
		
		// Execution:
		if (to_run == 1)
			makeWizzard();
		else if (to_run == 2)
			makeSWTSnippet();
		
	}
}

/* ******************** Old wizzard code **********/
// Using Composite ===> ALL WORKS
//Composite composite = new Composite(parent, SWT.NONE);
//GridLayout gl = new GridLayout();
//composite.setLayout(gl);
//Button compButton = new Button(composite, SWT.PUSH);
//GridData gd = new GridData();
//gd.grabExcessHorizontalSpace = true;
//gd.grabExcessVerticalSpace = true;
//gd.horizontalAlignment = GridData.FILL;
//gd.verticalAlignment = GridData.FILL;
//compButton.setLayoutData(gd);
//compButton.setText("CompositeButton");
//setControl(composite);




/* ********************** Old Snippet code ***********/


//final Shell shellChild = new Shell(shell, SWT.RESIZE);
//shellChild.setLayout(new GridLayout());
//
//// Composite - FormLayout; GridData to fill Shell.
//Composite comp1 = new Composite(shellChild, SWT.DOUBLE_BUFFERED);
//comp1.setLayout(new FormLayout());
//GridData comp1gd = new GridData();
//comp1gd.horizontalAlignment = SWT.FILL;
//comp1gd.verticalAlignment = SWT.FILL;
//comp1gd.grabExcessHorizontalSpace = true;
//comp1gd.grabExcessVerticalSpace = true;
//comp1.setLayoutData(comp1gd);
//
//
//// Scrollod composite - FormData to fill composite.
//ScrolledComposite sc = new ScrolledComposite(comp1, SWT.V_SCROLL | SWT.H_SCROLL);
////sc.setLayout(new FillLayout());
//sc.setExpandHorizontal(true);
//sc.setExpandVertical(true);
//FormData scfd = new FormData();
//scfd.top = new FormAttachment(0);
//scfd.bottom = new FormAttachment(100);
//scfd.left = new FormAttachment(0);
//scfd.right = new FormAttachment(100);
//sc.setLayoutData(scfd);
//
//
////TitleRegion - FormAttachement to fill Composite.
//TitleRegion titleRegion = new TitleRegion(sc);
//titleRegion.setText("This is a title");
//Color color = new Color (display, 138, 226, 255);
//titleRegion.setBackground(color);
//sc.setContent(titleRegion);



//FormData trFormData = new FormData();
//trFormData.top = new FormAttachment(0);
//trFormData.bottom = new FormAttachment(100);
//trFormData.right = new FormAttachment(100);
//trFormData.left = new FormAttachment(0);
//titleRegion.setLayoutData(trFormData);


// TODO - research from Fill layouts.
//GridData trgd = new GridData();
//trgd.grabExcessHorizontalSpace = true;
//trgd.grabExcessVerticalSpace = true;
//trgd.horizontalAlignment = GridData.FILL;
//trgd.verticalAlignment = GridData.FILL;
//titleRegion.setLayoutData(trgd);




//ScrolledComposite sc = new ScrolledComposite(shell, SWT.V_SCROLL | SWT.H_SCROLL);
//sc.setExpandHorizontal(true);
//sc.setExpandVertical(true);
//
//Composite comp = new Composite(sc, SWT.DOUBLE_BUFFERED);
//comp.setLayout(new FillLayout());
//Button button = new Button(comp, SWT.PUSH);
//button.setText("hello world!");
//
//sc.setContent(comp);
//
//GridData scGridData = new GridData();
//scGridData.grabExcessHorizontalSpace = true;
//scGridData.grabExcessVerticalSpace = true;
//scGridData.horizontalAlignment = GridData.FILL;
//scGridData.verticalAlignment = GridData.FILL;
//comp.setLayoutData(scGridData);
//
// Composite 
	// Style: DOUBLE_BUFFERED
	// Layout: FormLayout
	// LayoutData: GridData {horizontalAlignment =SWT.FILL, grabExcessHozSpace = true, vertAlign=SWT.FILL, grabExecVertSpc=true

// ... 

// .. ScrolledForm = ScrolledComposite 
// Style: HORIZONTAL ? | VERTICAL ? | DOUBLE_BUFFERED
// Layout: custom.ScrolledCompositeLayout
// LayoutData: null 


//Form, ui.forms.widgets.Form$FormLayout
//FormHeading,   FormHeadingLayout
// TitleRegion,    TitleRegionLayout



