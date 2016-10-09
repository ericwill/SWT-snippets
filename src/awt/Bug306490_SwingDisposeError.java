package awt;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.DeviceData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug306490_SwingDisposeError {
	
	static void createSwing() {
		JFrame frame = new JFrame("Swing, close me (before or after closing the other window)");
		frame.setBounds(0, 0, 600, 100);

		frame.addWindowListener(new WindowAdapter() { // same bug with using setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE) instead
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
				System.out.println("Swing: disposed");
			}
		});
		
		// remark 1: comment out the following line (do not add textArea) and all works fine 
		frame.getContentPane().add(new JTextArea());
		frame.setVisible(true);
	}
	
	static void createSwt() {
		Display display = new Display();
		Shell shell = new Shell(display, SWT.TITLE);
		shell.setBounds(0, 200, 600, 100);
		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
					display.sleep();
		
		display.dispose();
		System.out.println("SWT: disposed");
	}
	
	public static void main(String[] args) throws Exception {		
		 //remark 2: change order of following two instantiations and all works fine
		createSwing();
		Thread.sleep(1000);
		new Thread() { public void run() {createSwt();} }.start(); // also createSwt() alone doesn't work
	}
}