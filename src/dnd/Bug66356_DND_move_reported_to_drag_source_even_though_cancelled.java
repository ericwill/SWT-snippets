package dnd;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug66356_DND_move_reported_to_drag_source_even_though_cancelled {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Button source = new Button(shell, SWT.PUSH);
		source.setText("Source");
		DragSource dragSource = new DragSource(source, DND.DROP_MOVE);
		dragSource.setTransfer(new Transfer[] {TextTransfer.getInstance()});
		dragSource.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {
				event.data = "hello";
			}
			public void dragFinished(DragSourceEvent event) {
				System.out.println("Move = "+(event.detail == DND.DROP_MOVE));
			}
		});
		Button dest = new Button(shell, SWT.PUSH);
		dest.setText("Dest");
		DropTarget dropTarget = new DropTarget(dest, DND.DROP_MOVE);
		dropTarget.setTransfer(new Transfer[] {TextTransfer.getInstance()});
//		dropTarget.addDropListener(new DropTargetAdapter() {
//			public void drop(DropTargetEvent event) {
//				event.detail = DND.DROP_NONE;
//			}
//		});
//		dropTarget.addDropListener(new DropTargetListener() {
//			
//			@Override
//			public void dropAccept(DropTargetEvent event) {
//				// TD Auto-generated method stub
//				event.detail = DND.DROP_NONE;
//				
//			}
//			
//			@Override
//			public void drop(DropTargetEvent event) {
//				// TD Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void dragOver(DropTargetEvent event) {
//				// TD Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void dragOperationChanged(DropTargetEvent event) {
//				// TD Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void dragLeave(DropTargetEvent event) {
//				// TD Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void dragEnter(DropTargetEvent event) {
//				// TD Auto-generated method stub
//				
//			}
//		});
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
