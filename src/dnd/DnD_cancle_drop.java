package dnd;
import org.eclipse.swt.*;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.layout.FillLayout;

public class DnD_cancle_drop {
	static Display display = new Display ();
	static Shell shell = new Shell (display);
	static {
		shell.setLayout(new FillLayout());
	}
	
	static Label dragLabel = null;
	static Label dropLabel = null;
	static Transfer [] transferTypes = new Transfer[] {TextTransfer.getInstance ()};
	
	
	public static void main(String[] args) {
		createDragLabel (shell);
		createDropLabel (shell);		
		swtMainLoop ();
	}

	private static void createDragLabel (Shell shell) {
		dragLabel = new Label(shell, SWT.BORDER);
		dragLabel.setText ("Source Widget");
	
		//Create a dragsource with the widget as argument:
		DragSource source = new DragSource(dragLabel, DND.DROP_MOVE);
		source.setTransfer (transferTypes);
		
		//Add a drag listener. 
		source.addDragListener (new DragSourceListener() {
			@Override
			public void dragStart (DragSourceEvent event) {
				//only start if there is text in label 
				if (dragLabel.getText ().length() == 0) {
					event.doit = false;
				}
			}
			@Override
			public void dragSetData (DragSourceEvent event) {
				//provide data for dnd. 
				if (TextTransfer.getInstance().isSupportedType (event.dataType)) {
					event.data = dragLabel.getText ();
				}
			}
			@Override
			public void dragFinished (DragSourceEvent event) {
				//If move was performed, remove data from source
				if (event.detail == DND.DROP_MOVE) {
					dragLabel.setText ("");
				}
			}
		});
	}


	private static void createDropLabel (Shell shell) {
		dropLabel = new Label (shell, SWT.BORDER);
		dropLabel.setText ("Drag target");
		
		//Say that we can drop things on it.
		DropTarget target = new DropTarget(dropLabel, DND.DROP_MOVE);
		
		target.setTransfer (transferTypes);
		target.addDropListener (new DropTargetListener() {
			
			@Override
			public void dragEnter (DropTargetEvent event) {
			}
			@Override
			public void dragOver (DropTargetEvent event) {
			}
			
			@Override
			public void dragOperationChanged (DropTargetEvent event) {}


			@Override
			public void dropAccept (DropTargetEvent event) {
				event.detail = DND.DROP_NONE;                                   /// <<<<<<<<<<<<<<<<< Cancle the drop.
			}
			
			@Override
			public void drop (DropTargetEvent event) {
				if (transferTypes[0].isSupportedType (event.currentDataType)) {
					String textString = (String) event.data;
					dropLabel.setText (textString);
				}
			}
			
			@Override
			public void dragLeave (DropTargetEvent event) {}
		});
	}


	private static void swtMainLoop () {
			shell.setSize (600, 400);
			shell.open ();
			while (!shell.isDisposed ()) {
				if (!display.readAndDispatch ()) display.sleep ();
			}
			display.dispose ();
		}
}