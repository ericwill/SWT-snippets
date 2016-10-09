package text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

public class Bug197785_TextSelectionBehavior
{
   public static void main(String[] args)
   {

      final Display display = new Display();
      final Shell shell = new Shell(display);
      final Text text = new Text(shell, SWT.V_SCROLL | SWT.MULTI);
      text.setText("1\n2\n3\n4\n5\n6\n7\n8\n9\n10");
      text.setBounds(5, 5, 50, 50);
      Text text1 = new Text(shell, SWT.None);
      text1.setBounds(5, 100, 50, 50);

      Button b = new Button(shell, SWT.PUSH);
      b.setText("Push me");
      b.setBounds(5, 200, 50, 20);
      b.addListener(SWT.Selection, new Listener(){
         public void handleEvent(Event e)
         {
            text.setSelection(0, -1);
            System.out.println("Caret line number " + text.getCaretLineNumber());
            System.out.println("Caret position " + text.getCaretPosition());

         }
      });

      shell.pack();
      shell.open();
      while (!shell.isDisposed())
      {
         if (!display.readAndDispatch())
            display.sleep();
      }
      display.dispose();

   }

}