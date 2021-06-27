package lunartools;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public class ScreenTools {
	private static ScreenTools instance;
	private Rectangle[] bounds;
	
	private ScreenTools() {
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] graphicsDevices=graphicsEnvironment.getScreenDevices();
		bounds=new Rectangle[graphicsDevices.length];
		for(int i=0;i<graphicsDevices.length;i++) {
			bounds[i]=graphicsDevices[i].getDefaultConfiguration().getBounds();
		}
	}

	public static ScreenTools getInstance() {
		if(instance==null) {
			createInstance();
		}
		return instance;
	}
	
	private static synchronized void createInstance() {
		if(instance==null) {
			instance=new ScreenTools();
		}
	}
	
	public static boolean isInBounds(Rectangle bounds, int x, int y) {
		return x>=bounds.x && x<bounds.x+bounds.width && y>=bounds.y && y<bounds.y+bounds.height;
	}
	
	private Rectangle getBoundsOfCurrentScreen(Rectangle rectangleFrame) {
		int centerFrameX=rectangleFrame.x+(rectangleFrame.width>>1);
		int centerFrameY=rectangleFrame.y+(rectangleFrame.height>>1);
		Rectangle rectangle;
		for(int i=0;i<bounds.length;i++) {
			rectangle=bounds[i];
			if(
					centerFrameX>=rectangle.x &&
					centerFrameX<=rectangle.x+rectangle.width &&
					centerFrameY>=rectangle.y &&
					centerFrameY<=rectangle.y+rectangle.height
					) {
				return rectangle;
			}
		}
		return bounds[0];
	}
	
	/**
	 * If a program resizes (enlarges) it´s screen bounds, there is a chance, on a multi monitor system,
	 * that the new size oberlaps to a second monitor.
	 * <br>In that case, this method tries to move the program window so that it is shown on one monitor only.
	 * 
	 * @param rectangleOldBounds The current bounds of a program window
	 * @param rectangleNewBounds  The new bounds of the program window, while resizing the program window
	 * @return The bounds of the resized program window
	 */
	public Rectangle optimizeBounds(Rectangle rectangleOldBounds,Rectangle rectangleNewBounds) {
		Rectangle currentScreenBounds=getBoundsOfCurrentScreen(rectangleOldBounds);
		int deltaX=currentScreenBounds.x+currentScreenBounds.width-rectangleNewBounds.x-rectangleNewBounds.width;
		int deltaY=currentScreenBounds.y+currentScreenBounds.height-rectangleNewBounds.y-rectangleNewBounds.height;
		if(deltaX<0) {
			rectangleNewBounds.x+=deltaX;
			if(rectangleNewBounds.x<currentScreenBounds.x) {
				rectangleNewBounds.x=currentScreenBounds.x;
			}
		}
		if(deltaY<0) {
			rectangleNewBounds.y+=deltaY;
			if(rectangleNewBounds.y<currentScreenBounds.y) {
				rectangleNewBounds.y=currentScreenBounds.y;
			}
		}
		return rectangleNewBounds;
	}
	
}
