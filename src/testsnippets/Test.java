package testsnippets;

import java.lang.management.ManagementFactory;

import org.eclipse.swt.internal.gtk.OS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test {

	Display display;
	Shell shell;
	
	public Test() {
		this.display = new Display ();
	    this.shell = new Shell (display);
	    
	    shell.setText(System.getProperty("sun.java.command") + " " +  Test.getGtkVersion());
	}
	
	
	public void displayLoop() {
 	    shell.open();
	    while (!shell.isDisposed ()) {
	        if (!display.readAndDispatch ()) display.sleep ();
	    }
	    display.dispose ();
	}
	
	
	public static String getGtkVersion () {
		return "Gtk: " + OS.gtk_major_version() + "." + OS.gtk_minor_version() + "." + OS.gtk_micro_version() + "  pid:"
				+ ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
	}
	
}
