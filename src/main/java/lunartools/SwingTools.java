package lunartools;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwingTools {
	private static Logger logger = LoggerFactory.getLogger(SwingTools.class);
	public static final double SECTIOAUREA=1.6180339887;

	private static final String[] PROGRAM_ICON_PATHS=new String[] {
			"/icons/ProgramIcon16.png",
			"/icons/ProgramIcon24.png",
			"/icons/ProgramIcon32.png",
			"/icons/ProgramIcon40.png",
			"/icons/ProgramIcon48.png",
			"/icons/ProgramIcon56.png",
			"/icons/ProgramIcon64.png",
			"/icons/ProgramIcon90.png",
			"/icons/ProgramIcon128.png"
	};

	public static List<Image> getDefaultIconImages() {
		List<Image> icons=new ArrayList<Image>();
		for(String pathIcon:PROGRAM_ICON_PATHS) {
			try {
				icons.add(ImageTools.createImageFromResource(pathIcon));
			} catch (IOException e) {
				logger.warn("error loading program icon: "+pathIcon,e);
			}
		}
		return icons;
	}

	/**
	 * Determines the program version, which is the <code>version</code> tag of the pom.xml, assuming
	 * that the calling application contains a <code>project.properties</code> and an appropriate resource
	 * filter in the pom.xml
	 * 
	 * @return The program version, which is the <code>version</code> tag of the pom.xml.
	 */
	public static String determineProgramVersion() {
		String versionProgram="";
		try(InputStream inputStream=SwingTools.class.getResourceAsStream("/project.properties")){
			if(inputStream==null) {
				logger.error("project.properties not found");
				return "";
			}
			Properties properties = new Properties();
			properties.load(inputStream);
			versionProgram=properties.getProperty("version");
			if("${project.version}".equals(versionProgram)) {
				versionProgram="";
			}
		} catch (IOException e) {
			logger.error("error loading project.properties",e);
		}
		return versionProgram;
	}

	/**
	 * Returns bounds that place the given <code>Dimension</code> in the middle of the default screen device.
	 * 
	 * @param dimensionToBeCentered The <code>Dimension</code>, i.e. default size of a <code>Frame</code>.
	 * @return The bounds that place the <code>Dimension</code> in the middle of the default screen device.
	 */
	public static Rectangle getBoundsForCenteredDimension(Dimension dimensionToBeCentered) {
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();  
		GraphicsDevice defaultGraphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		Rectangle graphicsDeviceBounds = defaultGraphicsDevice.getDefaultConfiguration().getBounds();
		int marginX=(graphicsDeviceBounds.width-dimensionToBeCentered.width)>>1;
		int marginY=(graphicsDeviceBounds.height-dimensionToBeCentered.height)>>1;
		return new Rectangle(graphicsDeviceBounds.x+marginX,graphicsDeviceBounds.y+marginY,dimensionToBeCentered.width,dimensionToBeCentered.height);
	}

	public static Rectangle fixScreenBounds(Rectangle viewBounds, Dimension defaultFrameSize) {
		int centerX=viewBounds.x+viewBounds.width>>1;
		int centerY=viewBounds.y+viewBounds.height>>1;
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();  
		GraphicsDevice[] graphicsDevices = graphicsEnvironment.getScreenDevices();
		int numberOfGraphicsDevices=graphicsDevices.length;
		for(int i=0;i<numberOfGraphicsDevices;i++){
			GraphicsDevice graphicsDevice=graphicsDevices[i];
			Rectangle graphicsDeviceBounds = graphicsDevice.getDefaultConfiguration().getBounds();
			if(
				centerX>=graphicsDeviceBounds.x &&
				centerY>=graphicsDeviceBounds.y &&
				centerX<=graphicsDeviceBounds.x+graphicsDeviceBounds.width &&
				centerY<=graphicsDeviceBounds.y+graphicsDeviceBounds.height
				) {
					return viewBounds;
			}
		}
		Rectangle graphicsDeviceBounds = graphicsDevices[0].getDefaultConfiguration().getBounds();
		int marginX=(graphicsDeviceBounds.width-defaultFrameSize.width)>>1;
		int marginY=(graphicsDeviceBounds.height-defaultFrameSize.height)>>1;
		return new Rectangle(graphicsDeviceBounds.x+marginX,graphicsDeviceBounds.y+marginY,defaultFrameSize.width,defaultFrameSize.height);
	}

}
