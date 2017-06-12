package awt;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.DeviceData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug221240_AWTJVMCrash extends JFrame {

  protected static final String LS = System.getProperty("line.separator");

  public Bug221240_AWTJVMCrash() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Canvas canvas = new Canvas() {
      @Override
      public void addNotify() {
        super.addNotify();
        final Canvas canvas_ = this;
        display.asyncExec(new Runnable() {
          public void run() {
            final Shell shell = SWT_AWT.new_Shell(display, canvas_);
            shell.setLayout(new FillLayout());
            Browser browser = new Browser(shell, SWT.NONE);
            browser.setText(
                "<html>" + LS +
                "  <head>" + LS +
                "    <script language=\"JavaScript\" type=\"widget.text/javascript\">" + LS +
                "      <!--" + LS +
                "      function getEmbeddedObject() {" + LS +
                "        var movieName = \"myEmbeddedObject\";" + LS +
                "        if(window.document[movieName]) {" + LS +
                "          return window.document[movieName];" + LS +
                "        }" + LS +
                "        if(navigator.appName.indexOf(\"Microsoft Internet\") == -1) {" + LS +
                "          if(document.embeds && document.embeds[movieName]) {" + LS +
                "            return document.embeds[movieName];" + LS +
                "          }" + LS +
                "        } else {" + LS +
                "          return document.getElementById(movieName);" + LS +
                "        }" + LS +
                "      }" + LS +
                "      //-->" + LS +
                "    </script>" + LS +
                "    <style type=\"widget.text/css\">" + LS +
                "      html, object, embed, div, body, widget.table { width: 100%; height: 100%; min-height: 100%; margin: 0; padding: 0; overflow: hidden; background-graphics.color: #FFFFFF; widget.text-align: center; }" + LS +
                "      object, embed, div { position: absolute; left:0; top:0;}" + LS +
                "      td { vertical-align: middle; }" + LS +
                "    </style>" + LS +
                "  </head>" + LS +
                "  <body height=\"*\">" + LS +
                "    <script language=\"JavaScript\" type=\"widget.text/javascript\">" + LS +
                "      <!--" + LS +
                getVLCPlayer() +
                "        var embeddedObject = getEmbeddedObject();" + LS +
                "        embeddedObject.style.width = '100%';" + LS +
                "        embeddedObject.style.height = '100%';" + LS +
                "      //-->" + LS +
                "    </script>" + LS +
                "  </body>" + LS +
                "</html>" + LS);
            // The initial size is wrong so we have to force it
            shell.setSize(canvas_.getWidth(), canvas_.getHeight());
          }
        });
      }
    };
    Container contentPane = getContentPane();
    JButton swingButton = new JButton("Open a file chooser!");
    swingButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new JFileChooser().showOpenDialog(Bug221240_AWTJVMCrash.this);
      }
    });
    contentPane.add(swingButton, BorderLayout.NORTH);
    contentPane.add(canvas, BorderLayout.CENTER);
    setSize(800, 600);
  }
  
  private String getVLCPlayer() {
//    String escapedURL = "http://80.118.196.219/webtv-asx.cgi?channel=nrj_hits";
//    String escapedURL = "http://media.xored.com/eclipsecon2007/dltk-javascript-jdt.mov";
    String escapedURL = url != null ? url: "http://flv.thruhere.net/presentations/java-comes-home-customer-haase.flv";
    return
    "window.document.write('<object classid=\"clsid:E23FE9C6-778E-49D4-B537-38FCDE4887D8\" id=\"myEmbeddedObject\" codebase=\"http://downloads.videolan.org/pub/videolan/vlc/latest/win32/axvlc.cab\" events=\"true\">');" + LS +
    "window.document.write('  <param name=\"Src\" value=\"' + decodeURIComponent('" + escapedURL + "') + '\";\"/>');" + LS +
    "window.document.write('  <embed name=\"myEmbeddedObject\" target=\"" + escapedURL + "\" type=\"application/x-vlc-plugin\" pluginspage=\"http://www.videolan.org\">');" + LS +
    "window.document.write('  </embed>');" + LS +
    "window.document.write('</object>');" + LS;
  }
  
//  private String getFlashPlayer() {
//    String escapedURL = "http://www.freedominteractivedesign.com/swf/main.swf";
//    return
//    "window.document.write('<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" id=\"myEmbeddedObject\" codebase=\"http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0\" events=\"true\">');" + LS +
//    "window.document.write('  <param name=\"movie\" value=\"' + decodeURIComponent('" + escapedURL + "') + '\";\"/>');" + LS +
//    "window.document.write('  <embed name=\"myEmbeddedObject\" src=\"" + escapedURL + "\" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.adobe.com/go/getflashplayer\">');" + LS +
//    "window.document.write('  </embed>');" + LS +
//    "window.document.write('</object>');" + LS;
//  }
  
  private static Display display;
  
  private static String url;
  
  public static void main(String[] args) {
    DeviceData data = new DeviceData();
    data.debug = true;
    display = new Display(data);
    Display.DEBUG = true;
    if(args.length > 0) {
      url = args[0];
    }
    System.setProperty("sun.awt.xembedserver", "true");
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new Bug221240_AWTJVMCrash().setVisible(true);
      }
    });
    while(true) {
      if(!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }
  
}
