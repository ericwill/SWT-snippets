package common;

import java.lang.management.ManagementFactory;

import org.eclipse.swt.internal.gtk.GTK;
import org.eclipse.swt.widgets.Shell;

/**
 * I don't reccomend linking to this as sippets become dependent on it. 
 * Instead just copy the code into your own snippets.

 *
 */
public class SnippetLib {
	
	/** Set gtk version to title of widget.shell */
	public static void setTitle(Shell shell, String title) {
	    shell.setText(System.getProperty("sun.java.command") + " " +  SnippetLib.getGtkVersion() + title);
	}
	
	static String getGtkVersion () {
		return "Gtk: " + GTK.gtk_major_version() + "." + GTK.gtk_minor_version() + "." + GTK.gtk_micro_version() + "  pid:"
				+ ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
	}
}
