package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SystemColorSnippet {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell();
		
		System.out.println("\n COLOR_WIDGET_* \n");
		System.out.println("COLOR_WIDGET_BACKGROUND: " + display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		System.out.println("COLOR_WIDGET_FOREGROUND: " + display.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND));
		
		System.out.println("\n COLOR_INFO_* \n");
		System.out.println("COLOR_INFO_BACKGROUND: " + display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		System.out.println("COLOR_INFO_FOREGROUND: " + display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
		
		System.out.println("\n COLOR_WIDGET_SHADOWS \n");
		System.out.println("COLOR_WIDGET_DARK_SHADOW: " + display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		System.out.println("COLOR_WIDGET_LIGHT_SHADOW: " + display.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		System.out.println("COLOR_WIDGET_NORMAL_SHADOW: " + display.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		System.out.println("COLOR_WIDGET_HIGHLIGHT_SHADOW: " + display.getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		
		System.out.println("\n COLOR_LIST_* \n");
		System.out.println("COLOR_LIST_FOREGROUND: " + display.getSystemColor(SWT.COLOR_LIST_FOREGROUND));
		System.out.println("COLOR_LIST_BACKGROUND: " + display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));
		
		System.out.println("\n COLOR_LIST_SELECTION_* \n");
		System.out.println("COLOR_LIST_SELECTION: " + display.getSystemColor(SWT.COLOR_LIST_SELECTION));
		System.out.println("COLOR_LIST_SELECTION_INACTIVE: cannot be fetched");
		System.out.println("COLOR_LIST_SELECTION_TEXT: " + display.getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));
		System.out.println("COLOR_LIST_SELECTION_TEXT_INACTIVE: cannot be fetched");
		
		System.out.println("\n COLOR_TITLE_* \n");
		System.out.println("COLOR_TITLE_BACKGROUND: " + display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND));
		System.out.println("COLOR_TITLE_FOREGROUND: " + display.getSystemColor(SWT.COLOR_TITLE_FOREGROUND));
		System.out.println("COLOR_TITLE_BACKGROUND_GRADIENT: " + display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		System.out.println("COLOR_TITLE_INACTIVE_FOREGROUND: " + display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		System.out.println("COLOR_TITLE_INACTIVE_BACKGROUND: " + display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		System.out.println("COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT: " + display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
