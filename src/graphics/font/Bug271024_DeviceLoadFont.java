package graphics.font;

import org.eclipse.swt.widgets.Display;

public class Bug271024_DeviceLoadFont {

	public static void main(String[] args) {
		Display display = new Display();
		if(display.loadFont("garbage.ttf")) {
			System.out.println("Should not have got here!");
		} else {
			System.out.println("OK, this graphics.font does not exist.");
		}
		display.dispose();
	}

}