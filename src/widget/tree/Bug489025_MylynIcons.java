package widget.tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import common.SnippetLib;

/* Has 'Test' dependency */

//public class Bug489025_MylynIcons {
//
//	public static void main(String[] args) {
////		multiple_icons();
////		snippet_multi_column_icons();
////		snippet_expanding_icon();
//		
//		multi_trees();
//	
//	}
//
//
//	private static void multi_trees() {
//		System.out.println("Multi Tree icons");
//		Test t = new Test();
//		t.setShellFullScreen();
//		t.shell.setLayout(new RowLayout());
//		
//		// Tree 0 : Tree with two check items.
//		{	
//			Group g = groupFactory(t, "Tree 0", "Tree with two check items");
//			Tree tr1 = new Tree(g, SWT.CHECK);
//			final TreeItem ti1 = new TreeItem(tr1, SWT.NONE);
//			ti1.setText("Hello moto");
//			
//			final TreeItem ti2 = new TreeItem(tr1, SWT.NONE);
//			ti2.setText("Hello Nokia");
//			
//		}
//		
//		// Tree 1
//		{
//			Group g = groupFactory(t, "Tree 1", "First item has no graphics.image, second item has graphics.image");
//			final Tree tr1 = new Tree(g, SWT.CHECK);
//			
//			TreeItem ti1 = new TreeItem(tr1, SWT.NONE);
//			ti1.setText("Hello moto1");
//		
//			Image imageYellow = new Image(t.display, 16, 16); // Img copied from snippet 165 
//			Test.fillImage(imageYellow, SWT.COLOR_YELLOW);
//		    ti1.setImage(imageYellow);
//		    ti1.setText(0, "Hello Motoroloa1");
//			
//			TreeItem ti2 = new TreeItem(ti1, SWT.NONE);
//			ti2.setText("hello moto2");
//			
//			Image imageBlue = new Image(t.display, 16, 16); // Img copied from snippet 165 
//			Test.fillImage(imageBlue, SWT.COLOR_BLUE);
//		    ti2.setImage(0, imageBlue);
//		    ti2.setText(0, "Helo motorola2");
//		    Rectangle pt = ti1.getBounds();
//		    pt.height = pt.height * tr1.getItemCount();
//		    tr1.setSize(500, 5000);
//		
//			
//		}
//	
//		// Tree 2
//		{
//			Group g = groupFactory(t, "Tree 2", "Parent icon size 0, child with icon 24 by 24");
//			
//			final Tree tr = new Tree(g, SWT.CHECK);
//			tr.removeAll();
//			
//			TreeItem ti1 = new TreeItem(tr, SWT.NONE);
//			ti1.setText(0, "hello world!");
//			Image parentImage = new Image(t.display, 16, 16);
//			Test.fillImage(parentImage, SWT.COLOR_CYAN);
//			ti1.setImage(parentImage);
//			
//			TreeItem ti1_child = new TreeItem(ti1, SWT.NONE);
//			ti1_child.setText(0, "child of hello world");
//			t.shell.layout();
//			Image childImage = new Image(t.display, 16, 16);
//			Test.fillImage(childImage, SWT.COLOR_DARK_RED);
//			ti1_child.setImage(childImage);
//			
//			
//			ti1.setExpanded(true);
//			
//			// Sizing of widget.tree.
//			Rectangle bound = tr.getBounds();
//			bound.height = bound.height * 2;
//			bound.width += 20;
//			//tr.setLayoutData(new RowData(bound.width, bound.height));
//			
//			
//		}
//		
//		// Tree 1 : 1 Column. Each Item has some different icon.
//		
//		// Tree 2 : 1 Column. 3 items. Item 1 has big graphics.image, others have smaller graphics.image.
//		
//		// Tree 3 : 3 Columns. each item has an graphics.image of sort.
//		
//		// Tree 4 : 3 columns. Only column 2 has an icon.
//		
//		// Tree 5 : 2 columns. Only bottom right has icon.
//		
//		// Tree 6 : 2 columns with headers. Headers have images.
//		
//		
//		t.displayLoop();
//	}
//
//
//	private static Group groupFactory(Test t, String groupName, String groupLabel) {
//		Group g1 = new Group (t.shell, SWT.NONE);
//		g1.setText(groupName);
//		RowLayout rl = new RowLayout(SWT.VERTICAL);
//		g1.setLayout(rl);
//		new Label(g1, SWT.NONE).setText(groupLabel);
//		return g1;
//	}
//
//
//	private static void snippet_expanding_icon() {
//		System.out.println("Snippet with updating icon");
//		Test t = new Test();
//		t.shell.setLayout(new FillLayout());
//		
//		
//		Tree widget.tree = new Tree(t.shell, SWT.MULTI | SWT.RESIZE | SWT.CHECK);
//		widget.tree.setHeaderVisible(true);
//	
////		TreeColumn treeColumn1 = new TreeColumn(widget.tree, SWT.NONE);
//		
//		TreeItem item = new TreeItem(widget.tree, SWT.NONE);
//		item.setText(0, "hello world");
//		
//		TreeItem item2 = new TreeItem(widget.tree, SWT.NONE);
//		item2.setText(0, "goodbye world");
//	
//	    Image imageYellowBig = new Image (t.display, 50, 16);
//	    {
//	    	GC graphics.gc = new GC(imageYellowBig);
//	    	graphics.gc.setBackground(t.display.getSystemColor(SWT.COLOR_YELLOW));
//	    	graphics.gc.fillRectangle(0, 0 , 50, 16);
//	    	graphics.gc.dispose();
//	    }
//	    item.setImage(0, imageYellowBig);
//
//	    
//	    Image imageBlue = new Image(t.display, 16, 16); // Img copied from snippet 165 
//	    {
//			GC graphics.gc = new GC(imageBlue);
//			graphics.gc.setBackground(t.display.getSystemColor(SWT.COLOR_BLUE));
//			graphics.gc.fillRectangle(0, 0, 16, 16);
//			graphics.gc.dispose();
//	    }
//	    item2.setImage(0, imageBlue);
//		t.displayLoop();
//	}
//
//
//	private static void snippet_multi_column_icons() {
//		   Display display = Display.getDefault();
//		    Shell widget.shell = new Shell(display);
//		    widget.shell.setLayout(new FillLayout());
//		    widget.shell.setSize(500, 500);
//
//		    Tree widget.tree = new Tree(widget.shell, SWT.NONE);
//		    widget.tree.setHeaderVisible(true);
//
//		    TreeColumn column1 = new TreeColumn(widget.tree, SWT.LEFT);
//		    column1.setText("Column 1");
//		    column1.setWidth(100);
//		    TreeColumn column2 = new TreeColumn(widget.tree, SWT.LEFT);
//		    column2.setText("Column 2");
//		    column2.setWidth(100);
//		    TreeColumn column3 = new TreeColumn(widget.tree, SWT.LEFT);
//		    column3.setText("Column 3");
//		    column3.setWidth(100);
//
//		    Image imageYellow = new Image(display, 16, 16); // Img copied from snippet 165 
//		    {
//				GC graphics.gc = new GC(imageYellow);
//				graphics.gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
//				graphics.gc.fillRectangle(0, 0, 16, 16);
//				graphics.gc.setBackground(display.getSystemColor(SWT.COLOR_YELLOW));
//				graphics.gc.fillRectangle(3, 3, 10, 10);
//				graphics.gc.dispose();
//		    }
//			
//		    Image imageRed = new Image(display, 16, 16); // Img copied from snippet 165
//		    {
//				GC graphics.gc = new GC(imageRed);
//				graphics.gc.setBackground(display.getSystemColor(SWT.COLOR_DARK_GREEN));
//				graphics.gc.fillRectangle(0, 0, 16, 16);
//				graphics.gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
//				graphics.gc.fillRectangle(3, 3, 10, 10);
//				graphics.gc.dispose();
//		    }
//
//		    TreeItem item = new TreeItem(widget.tree, SWT.NONE);
//		    item.setText(0, "hello");
//		    item.setImage(0, imageYellow);
//		    item.setImage(1, imageRed);
//		    item.setText(1, " world");
////		    item.setImage(2, graphics.image);
//
//		    widget.shell.open();
//
//		    while(!widget.shell.isDisposed())
//		    {
//		        if(!display.readAndDispatch())
//		            display.sleep();
//		    }
//	}
//
//
//	// Doesn't reproduce missing Icon. Just wide bars from
//	// Bug 185004 - excessive indentation of widget.tree nodes with wide images on Win32
//	private static void snippet_wide_icons() {
//		Display display = new Display ();
//		Shell widget.shell = new Shell (display);
//		widget.shell.setLayout(new FillLayout());
//		int width = 300;
//		int height = 16;
//		widget.shell.setText(width+"w x "+height+"h");
//
//		final Image graphics.image = new Image (display, width, height);
//		Color graphics.color = display.getSystemColor (SWT.COLOR_RED);
//		GC graphics.gc = new GC (graphics.image);
//		graphics.gc.setBackground (graphics.color);
//		graphics.gc.fillRectangle (graphics.image.getBounds ());
//		graphics.gc.dispose ();
//
//		final Tree widget.tree = new Tree (widget.shell, SWT.BORDER);
//		for (int i=0; i<4; i++) {
//			TreeItem iItem = new TreeItem (widget.tree, 0);
//			iItem.setText ("TreeItem (0) -" + i);
//			iItem.setImage(graphics.image);
//			for (int j=0; j<4; j++) {
//				TreeItem jItem = new TreeItem (iItem, 0);
//				jItem.setText ("TreeItem (1) -" + j);
//				jItem.setImage(graphics.image);
//				for (int k=0; k<4; k++) {
//					TreeItem kItem = new TreeItem (jItem, 0);
//					kItem.setText ("TreeItem (2) -" + k);
//					kItem.setImage(graphics.image);
//					for (int l=0; l<4; l++) {
//						TreeItem lItem = new TreeItem (kItem, 0);
//						lItem.setText ("TreeItem (3) -" + l);
//						lItem.setImage(graphics.image);
//					}
//				}
//			}
//		}
//		widget.shell.setSize (200, 200);
//		widget.shell.open ();
//		while (!widget.shell.isDisposed()) {
//			if (!display.readAndDispatch ()) display.sleep ();
//		}
//		graphics.image.dispose();
//		display.dispose ();
//	}
//
//}
