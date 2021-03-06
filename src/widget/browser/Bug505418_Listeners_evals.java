package widget.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.TitleEvent;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.browser.VisibilityWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Bug505418_Listeners_evals {
	
	public static int count = 0;

	public static void main(String[] args) {
//		locationChange();  // new url
		progressListener(); // make progress.
//		statusTextListener();
//		closeWindowListener();
//		openWindowListener();
//		titleListener();
//		visibilityWindowListener(); // Quircky on webkit2.
//		miscSWTListeners();
	}

	private static void locationChange() {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Button button = new Button(shell, SWT.PUSH);
		final Browser browser = new Browser(shell, SWT.NONE);
		button.setText("Click to increase count.");
		button.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {}
			public void mouseDown(MouseEvent e) {
				shell.setText("Count: " + count++);
			}
			public void mouseDoubleClick(MouseEvent e) {}
		});
		
		browser.setText("<html><title>Snippet</title><body><a href=\"https://eclipse.org/\">Eclipse.org  Disposing on link change causes hang</a></body></html>");
		browser.addProgressListener(new ProgressListener() {
			@Override
			public void completed(ProgressEvent event) {
				browser.addLocationListener(new LocationListener() {
					@Override
					public void changing(LocationEvent event) {
						System.out.println ("changing...");
						
						String value = (String)browser.evaluate("return 'Changing eval...'");
						System.out.println("changing returned: " + value);
						
//						widget.browser.evaluate("return 'Changing eval...'");

//						event.doit = false; // Stop page load.
					}

					@Override
					public void changed(LocationEvent event) {
						System.out.print("Changed!");
						String value = (String)browser.evaluate("return 'finished callback'");
						System.out.println("Changed returned: " + value);
					}
				});
			}
			@Override
			public void changed(ProgressEvent event) {}
		});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	private static void progressListener () {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Button button = new Button(shell, SWT.PUSH);
		final Browser browser = new Browser(shell, SWT.NONE);
		button.setText("Click to increase count.");
		button.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {}
			public void mouseDown(MouseEvent e) {
				shell.setText("Count: " + count++);
			}
			public void mouseDoubleClick(MouseEvent e) {}
		});
		
		browser.setText("<html><title>Snippet</title><body><a href=\"https://eclipse.org/\">Eclipse.org  Disposing on link change causes hang</a></body></html>");
		browser.addProgressListener(new ProgressListener() {
			
			@Override
			public void completed(ProgressEvent event) {
				System.out.println("compleated!! " + event.current);
				evalTest(browser, "");
			}
			
			@Override
			public void changed(ProgressEvent event) {
				System.out.println("changing..." + event.current);
				evalTest(browser, "");
				
				
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	static void statusTextListener() {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Button button = new Button(shell, SWT.PUSH);
		final Browser browser = new Browser(shell, SWT.NONE);
		button.setText("Click to increase count.");
		button.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {}
			public void mouseDown(MouseEvent e) {
				shell.setText("Count: " + count++);
			}
			public void mouseDoubleClick(MouseEvent e) {}
		});
		
		browser.setText("<html><title>Snippet</title><body><a href=\"https://eclipse.org/\">Eclipse.org  Disposing on link change causes hang</a></body></html>");
		browser.addStatusTextListener(new StatusTextListener() {
			
			@Override
			public void changed(StatusTextEvent event) {
				System.out.println("Status has changed");
				evalTest(browser, "Status changed");
			}
		});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	static void closeWindowListener() {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Button button = new Button(shell, SWT.PUSH);
		Button closeBrowserButton = new Button(shell, SWT.PUSH);
		final Browser browser = new Browser(shell, SWT.NONE);
		closeBrowserButton.setText("Close widget.browser button");
		closeBrowserButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				browser.execute("window.close()");
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		
		button.setText("Click to increase count.");
		button.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {}
			public void mouseDown(MouseEvent e) {
				shell.setText("Count: " + count++);
			}
			public void mouseDoubleClick(MouseEvent e) {}
		});
		browser.setText("<html><title>Snippet</title><body><a href=\"https://eclipse.org/\">Eclipse.org  Disposing on link change causes hang</a></body></html>");
		browser.addCloseWindowListener(new CloseWindowListener() {
			
			@Override
			public void close(WindowEvent event) {
				System.out.println("closing...");
				evalTest(browser, "closing...");
				System.out.println("Browser getText(): "+ browser.getText());
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	static void openWindowListener() {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Button button = new Button(shell, SWT.PUSH);
		Button closeBrowserButton = new Button(shell, SWT.PUSH);
		final Browser browser = new Browser(shell, SWT.NONE);
		closeBrowserButton.setText("Close widget.browser button");
		closeBrowserButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				browser.execute("window.open()");
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		
		button.setText("Click to increase count.");
		button.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {}
			public void mouseDown(MouseEvent e) {
				shell.setText("Count: " + count++);
			}
			public void mouseDoubleClick(MouseEvent e) {}
		});
		browser.setText("<html><title>Snippet</title><body><a href=\"https://eclipse.org/\">Eclipse.org  Disposing on link change causes hang</a></body></html>");
		browser.addOpenWindowListener(new OpenWindowListener() {
			
			@Override
			public void open(WindowEvent event) {
				System.out.println("opening...");
				evalTest(browser, "opening...");
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	static void titleListener() {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Button button = new Button(shell, SWT.PUSH);
		final Browser browser = new Browser(shell, SWT.NONE);
		browser.addTitleListener(new TitleListener() {
			@Override
			public void changed(TitleEvent event) {
				System.out.println("Title has changed :" + event.title);
				evalTest(browser, "title change... ");
			}
		});
		
		button.setText("Click to increase count.");
		button.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {}
			public void mouseDown(MouseEvent e) {
				shell.setText("Count: " + count++);
			}
			public void mouseDoubleClick(MouseEvent e) {}
		});
		browser.setText("<html><title>Snippet</title><body><a href=\"https://eclipse.org/\">Eclipse.org  Disposing on link change causes hang</a></body></html>");
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	static void visibilityWindowListener() {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Button button = new Button(shell, SWT.PUSH);
		Button closeBrowserButton = new Button(shell, SWT.PUSH);
		final Browser browser = new Browser(shell, SWT.NONE);
		
		final Shell shell2 = new Shell(display);
		shell2.setLayout(new FillLayout());
		final Browser browser2 = new Browser(shell2, SWT.NONE);
		browser2.addVisibilityWindowListener(new VisibilityWindowListener() {
			
			@Override
			public void show(WindowEvent event) {
				System.out.println("showing...");
				shell2.open();
				evalTest(browser2, "browse2 show");
				evalTest(browser, "browse1 show");
			}
			
			@Override
			public void hide(WindowEvent event) {
			}
		});
		
		closeBrowserButton.setText("Close widget.browser button");
		closeBrowserButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				browser.execute("document.body.style.backgroundColor = 'red'");
				browser.execute("window.open('https://www.google.com', 'Dialog')");
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		
		button.setText("Click to increase count.");
		button.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {}
			public void mouseDown(MouseEvent e) {
				shell.setText("Count: " + count++);
			}
			public void mouseDoubleClick(MouseEvent e) {}
		});
		browser.setText("<html><title>Snippet</title><body><a href=\"https://eclipse.org/\">Eclipse.org  Disposing on link change causes hang</a></body></html>");
		browser.addOpenWindowListener(new OpenWindowListener() {
			
			@Override
			public void open(WindowEvent event) {
				System.out.println("opening...");
				event.browser = browser2;
				
			}
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	private static void miscSWTListeners() {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		final Browser browser = new Browser(shell, SWT.NONE);
		browser.setText("Hello world!");
		browser.addMouseMoveListener(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseEvent e) {
				System.out.println("mouse moved!");
				String str = (String) browser.evaluate("return 'hello world'");
				System.out.println("Evaluated: " + str);
			}
		});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	private static void evalTest(final Browser browser, Object msg) {
		String value = (String)browser.evaluate("return 'hello'");
		System.out.println("Returned: " + value);
		String script = "document.body.style.backgroundColor = 'red'";
		browser.execute(script);
	}

}
