package lunartools;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageTools {
	private static Logger logger = LoggerFactory.getLogger(ImageTools.class);

	/**
	 * Returns an image resource as ImageIcon.
	 * 
	 * @param iconpath
	 * @return
	 */
	public static ImageIcon createImageIcon(String iconpath) {
		URL url = ImageTools.class.getResource(iconpath);
		if (url != null) {
			return new ImageIcon(url);
		} else {
			logger.error("Error loading image icon: " + iconpath);
			return null;
		}
	}

	/**
	 * Returns an image resource as Image
	 * 
	 * @param imagepath
	 * @param context
	 * @return
	 * @throws IOException
	 */
	public static Image createImage(String imagepath) throws IOException {
		InputStream inputStream = ImageTools.class.getResourceAsStream(imagepath);
		byte[] imagedata=FileTools.readBytearrayFromInputStream(inputStream);
		Image image=Toolkit.getDefaultToolkit().createImage(imagedata);
		return image;
	}

}
