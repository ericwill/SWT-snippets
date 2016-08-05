package testsnippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Bug115206_SplashScreenDisplayError {
	public static void main(String[] args) {
	// QUICK SETUP SPLASH SCREEN
			final java.util.Date startSplashDate = new java.util.Date();
			final Shell splash = new Shell(SWT.MODELESS);
			GridLayout gridLayout = new GridLayout();
			gridLayout.numColumns = 1;
			gridLayout.marginWidth = 40;
			gridLayout.marginHeight = 40;
			gridLayout.horizontalSpacing = 10;
			gridLayout.verticalSpacing = 25;
			splash.setLayout(gridLayout);
			Color white = display.getSystemColor(SWT.COLOR_WHITE);
			splash.setBackground(white);
			//
			Image visassistImage = new Image(display, "images/visassist_logo_33.png");
			Label label = new Label(splash, SWT.NONE);
			label.setImage(visassistImage);
			GridData gridData = new GridData();
			gridData.horizontalAlignment = GridData.CENTER;
			label.setLayoutData(gridData);
			//
			Image cuelinereaderImage = new Image(display, "images/cuelinereader_logo_33.png");
			label = new Label(splash, SWT.NONE);
			label.setImage(cuelinereaderImage);
			gridData = new GridData();
			gridData.horizontalAlignment = GridData.CENTER;
			label.setLayoutData(gridData);
			//
			label = new Label(splash, SWT.NONE);
			Font splashFont = new Font(display, "Arial", 18, SWT.NORMAL);
			label.setFont(splashFont);
			label.setText(version.getVersion() + " Version, Release " + RELEASE);
			label.setBackground(white);
			gridData = new GridData();
			gridData.horizontalAlignment = GridData.CENTER;
			label.setLayoutData(gridData);
			splash.pack();
			Rectangle splashSize = splash.getBounds();
			int x = (appRect.width - splashSize.width) / 2;
			int y = (appRect.height - splashSize.height) / 2;
			splash.setLocation(x, y);
			splash.open();
			//
			display.asyncExec(new Runnable() {
				public void run() {
					// SETUP MAIN APP WINDOW HERE
	....
					updateDisplay();
					// READY TO LET GO OF SPLASH SCREEN NOW
					// Insure splash up at least 3 seconds
					long milliSec = 3000 - ((new java.util.Date()).getTime() -
	startSplashDate.getTime());
					if (milliSec > 0) {
						try {
							Thread.sleep(milliSec);
						} catch (Throwable e) {}
					}
					splash.dispose();
					shell.open();
	}
}
