package awt;


// This snippet causes errors on a regular setup. Commenting out for now.
//
//import java.awt.Frame;
//
//import javax.help.HelpSet;
//import javax.help.HelpSetException;
//import javax.help.JHelp;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.awt.SWT_AWT;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//
//public class Bug268105_JavahelpCrash {
//
//	public static void main(String[] args) {
//		HelpSet helpSet;
//		Display display;
//		Shell shell;
//		Composite composite;
//		Frame frame;
//		JHelp help;
//
//		try {
//			helpSet = new HelpSet(null, HelpSet.findHelpSet(null, "Animals.hs"));
//		} catch (HelpSetException e) {
//			e.printStackTrace();
//			return;
//		}
//
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return;
//		}
//
//		display = new Display();
//		shell = new Shell(display);
//		shell.setLayout(new FillLayout());
//		composite = new Composite(shell, SWT.NO_BACKGROUND | SWT.EMBEDDED);
//		frame = SWT_AWT.new_Frame(composite);
//		help = new JHelp(helpSet);
//		frame.add(help);
//
//		frame.setVisible(true);
//		shell.open();
//
//		while (!shell.isDisposed())
//			if (!display.readAndDispatch())
//				display.sleep();
//
//		display.dispose();
//	}
//
//}