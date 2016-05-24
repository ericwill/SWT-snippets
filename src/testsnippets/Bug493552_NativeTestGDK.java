package testsnippets;

import org.eclipse.swt.internal.gtk.OS;

public class Bug493552_NativeTestGDK {

	
	public static void main(String[] args) {
		OS.gtk_init_check(new long[0], new long[0]);
	      /* create a new window */
	      long window = OS.gtk_window_new(OS.GTK_WINDOW_TOPLEVEL);
	      OS.gtk_window_set_title(window, OS.ascii("GTK Menu Test"));
	      long menu = OS.gtk_menu_new();
	      long root_menu = OS.gtk_image_menu_item_new_with_label(OS.ascii("Root Menu"));
	      OS.gtk_widget_show(root_menu);
	      for(int i = 0; i < 3; i++) {
	        String buf = "Test-undermenu -" + i;
	        long menu_items = OS.gtk_image_menu_item_new_with_label(OS.ascii(buf));
	        OS.gtk_menu_shell_insert(menu, menu_items, i);
	        OS.gtk_widget_show(menu_items);
	      }
	      /* Now we specify that we want our newly created "menu" to be the menu for the "root menu" */
	      OS.gtk_menu_item_set_submenu(root_menu, menu);
	      /* Create a menu-bar to hold the menus and add it to our main window*/
	      long menu_bar = OS.gtk_menu_bar_new();
<<<<<<< Upstream, based on branch 'master' of https://github.com/ericwill/SWT-snippets.git
	      long /*int*/ screen = OS.gdk_screen_get_default ();
//	      int monitorNumber = OS.gdk_screen_get_monitor_at_window (screen, OS.gtk_widget_get_window(window));
	      int monitorNumber = OS.gdk_screen_get_primary_monitor (screen);
	      System.out.println("monitorNumber " + monitorNumber);
	      OS.gtk_container_add(window, menu_bar);
	      OS.gtk_widget_show(menu_bar);
	      OS.gtk_menu_shell_insert(menu_bar, root_menu, 0);
	      OS.gtk_widget_show(window);
=======
	      OS.gtk_container_add(window, menu_bar);
	      OS.gtk_widget_show(menu_bar);
	      OS.gtk_menu_shell_insert(menu_bar, root_menu, 0);
	      OS.gtk_widget_show(window);
	      long /*int*/ screen = OS.gdk_screen_get_default ();
//	      int monitorNumber = OS.gdk_screen_get_monitor_at_window (screen, OS.gtk_widget_get_window(window));
	      int monitorNumber = OS.gdk_screen_get_primary_monitor (screen);
	      System.out.println("monitorNumber " + monitorNumber);
>>>>>>> 36aa95c Bug 493552: Test monitor results using PI interface
	      OS.gtk_main ();
	}
	
	
}
