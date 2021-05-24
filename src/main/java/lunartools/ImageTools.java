package lunartools;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Some methods concerning images and pixel.
 * 
 * @author Thomas Mattel
 */
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
	 * @param resourcePath
	 * @param context
	 * @return
	 * @throws IOException
	 */
	public static Image createImage(String resourcePath) throws IOException {
		InputStream inputStream = ImageTools.class.getResourceAsStream(resourcePath);
		byte[] imagedata=FileTools.readBytearrayFromInputStream(inputStream);
		Image image=Toolkit.getDefaultToolkit().createImage(imagedata);
		return image;
	}

	/**
	 * Creates a BufferedImage in <code>TYPE_INT_RGB</code> format from the given File.
	 * 
	 * @param file The file to create the BufferedImage
	 * @return BufferedImage in <code>TYPE_INT_RGB</code> format
	 * @throws IOException
	 */
	public static BufferedImage getBufferedImage_intRGB(File file) throws IOException {
		BufferedImage imageByte=ImageIO.read(file);
		BufferedImage imageInt=getBufferedImage_intRGB(imageByte);
		imageByte.flush();
		return imageInt;
	}

	/**
	 * Returns a BufferedImage in <code>TYPE_INT_RGB</code> format from the given BufferedImage.
	 * <br>If the given BufferedImage is already in <code>TYPE_INT_RGB</code> format, it will be returned.
	 * <br>Otherwise, a new BufferedImage is created and returned.
	 * 
	 * @param bufferedImage Either the given BufferedImage if already in <code>TYPE_INT_RGB</code> format, or a new BufferedImage containing the same image.
	 * @return
	 */
	public static BufferedImage getBufferedImage_intRGB(BufferedImage bufferedImage){
		if(bufferedImage.getType()==BufferedImage.TYPE_INT_RGB) {
			return bufferedImage;
		}
		BufferedImage bufferedImageInt=new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(),BufferedImage.TYPE_INT_RGB);
		bufferedImageInt.getGraphics().drawImage(bufferedImage,0,0,null);
		return bufferedImageInt;
	}

	/**
	 * Returns an int array in RGB format from the given BufferedImage.
	 * <br>If the BufferedImage contains a <code>DataBufferInt</code>, it gets
	 * returned, ARGB masced to RGB.
	 * <br>A BufferedImage containing <code>DataBufferByte</code> is temporary not supported (commented out), because the
	 * implemention is suspected not to work on MacOS. Not easy to check without having MacOS :-)
	 * 
	 * @param bufferedImage
	 * @return
	 */
	public static int[] getRgbIntsFromBufferedImage(BufferedImage bufferedImage) {
		DataBuffer databuffer=bufferedImage.getRaster().getDataBuffer();
		if(databuffer instanceof DataBufferInt) {
			int[] intImagedata=((DataBufferInt)databuffer).getData();
			for(int i=0;i<intImagedata.length;i++) {
				intImagedata[i]=intImagedata[i]&0xffffff;
			}
			return intImagedata;
			//		}else if(databuffer instanceof DataBufferByte) {
			////			byte[] byteImagedata=((DataBufferByte)databuffer).getData().clone();
			////			int[] intImagedata=convertByteBGRtoIntRGB(byteImagedata);
			////			return intImagedata;
			//			return getRgbIntsFromBufferedImage(getBufferedImage_intRGB(bufferedImage));
		}else {
			throw new RuntimeException("databuffer not supported: "+databuffer.getClass().getName());
		}
	}

	/**
	 * Returns a byte array in RBG format from the given BufferedImage.
	 * <br>A BufferedImage containing <code>DataBufferByte</code> is temporary not supported (commented out), because the
	 * implemention is suspected not to work on MacOS. Not easy to check without having MacOS :-)
	 * 
	 * @param bufferedImage
	 * @return
	 */
	public static byte[] getRgbBytesFromBufferedImage(BufferedImage bufferedImage) {
		DataBuffer databuffer=bufferedImage.getRaster().getDataBuffer();
		if(databuffer instanceof DataBufferInt) {
			int[] intImagedata=((DataBufferInt)databuffer).getData();
			byte[] byteImagedata=createByteRGBfromIntRGB(intImagedata);
			return byteImagedata;
			//		}else if(databuffer instanceof DataBufferByte) {
			////			byte[] byteImagedata=((DataBufferByte)databuffer).getData().clone();
			////			byteRGBswapRB(byteImagedata);
			////			return byteImagedata;
			//			return getRgbBytesFromBufferedImage(getBufferedImage_intRGB(bufferedImage));
		}else {
			throw new RuntimeException("databuffer not supported: "+databuffer.getClass().getName());
		}
	}

	/**
	 * Writes the pixeldata in int RGB format to the given BufferedImage.
	 * <br>A BufferedImage containing <code>DataBufferByte</code> is temporary not supported (commented out), because the
	 * implemention is suspected not to work on MacOS. Not easy to check without having MacOS :-)
	 * 
	 * @param intsRGB
	 * @param bufferedImage
	 */
	public static void writeRgbIntsToBufferedImage(int[] intsRGB, BufferedImage bufferedImage) {
		DataBuffer databuffer=bufferedImage.getRaster().getDataBuffer();
		if(databuffer instanceof DataBufferInt) {
			int[] intImagedata=((DataBufferInt)databuffer).getData();
			if(intImagedata.length!=intsRGB.length) {
				throw new RuntimeException("pixel array size mismatch");
			}
			if(intImagedata==intsRGB) {
				return;
			}
			System.arraycopy(intsRGB, 0, intImagedata, 0, intsRGB.length);
			//		}else if(databuffer instanceof DataBufferByte) {
			//			//TO-DO: check if this DataBufferByte array is really in BGR format
			//			byte[] byteImagedata=((DataBufferByte)databuffer).getData();
			//			byte[] bytePixelData=convertIntRGBtoByteBGR(pixeldata);
			//			System.arraycopy(bytePixelData, 0, byteImagedata, 0, bytePixelData.length);
			//			throw new RuntimeException("DataBufferByte");
		}else {
			throw new RuntimeException("databuffer not supported: "+databuffer.getClass().getName());
		}
	}

	/**
	 * Creates an int array in RGB format from a given byte array in BGR format.
	 * 
	 * @param bytesBGR The byte array in BGR format
	 * @return A new int array in RGB format
	 */
	public static int[] createIntRGBfromByteBGR(byte[] bytesBGR) {
		int[] dataInt=new int[bytesBGR.length/3];
		int rgb;
		for(int i=0,index=0;i<dataInt.length;i++) {
			rgb=bytesBGR[index++]&0xff;//B
			rgb|=(bytesBGR[index++]&0xff)<<8;//G
			rgb|=(bytesBGR[index++]&0xff)<<16;//R
			dataInt[i]=rgb;
		}
		return dataInt;
	}

	/**
	 * Creates an byte array in RGB format from a given int array in RGB format.
	 * 
	 * @param intsRGB The int array in RGB format
	 * @return A new created byte array in RGB format
	 */
	public static byte[] createByteRGBfromIntRGB(int[] intsRGB) {
		byte[] bytes=new byte[intsRGB.length*3];
		for(int i=0,index=0;i<intsRGB.length;i++) {
			int pixel=intsRGB[i];
			bytes[index++]=(byte)(pixel>>16);//R
			bytes[index++]=(byte)(pixel>>8);//G
			bytes[index++]=(byte)pixel;//B
		}
		return bytes;
	}

	/**
	 * Creates an byte array in BGR format from a given int array in RGB format.
	 * 
	 * @param intsRGB The int array in RGB format
	 * @return A new created byte array in BGR format
	 */
	public static byte[] createByteBGRfromIntRGB(int[] intsRGB) {
		byte[] bytes=new byte[intsRGB.length*3];
		for(int i=0,index=0;i<intsRGB.length;i++) {
			int pixel=intsRGB[i];
			bytes[index++]=(byte)pixel;//B
			bytes[index++]=(byte)(pixel>>8);//G
			bytes[index++]=(byte)(pixel>>16);//R
		}
		return bytes;
	}

	/**
	 * Creates a byte array containing the greyscale values of the given int array.
	 * <br>The byte array contains the lowest bytes of the int array.
	 * 
	 * @param intRGB
	 * @return
	 */
	public static byte[] createByteGreyscaleFromIntGreyscale(int[] intRGB) {
		byte[] bytes=new byte[intRGB.length];
		for(int i=0;i<intRGB.length;i++) {
			bytes[i]=(byte)(intRGB[i]&0xff);
		}
		return bytes;
	}

	/**
	 * Every three bytes, the first and third byte is swapped, therefore the byte array is converted from RBG to BGR or BGR to RGB.
	 * 
	 * @param data
	 */
	public static void byteRGBswapRB(byte[] data) {
		byte b;
		for(int i=0;i<data.length;i+=3) {
			b=data[i];
			data[i]=data[i+2];
			data[i+2]=b;
		}
	}

	/**
	 * Applies the given masc to the given int array.
	 * 
	 * @param pixeldata
	 * @param masc
	 */
	public static void applyMascToPixeldata(final int[] pixeldata,final int masc) {
		for(int i=0;i<pixeldata.length;i++) {
			pixeldata[i]=pixeldata[i]&masc;
		}
	}

	/**
	 * Creates a 24-bit-sized int array containing the pixel count of the given pixel array in int RGB format.
	 * 
	 * @param pixeldata An int array in RGB format.
	 * @return An array containing the pixel count of the given pixel array, the index is the 24-bit colour value.
	 */
	public static int[] createPixelCountArray(final int[] pixeldata) {
		final int[] pixelcount=new int[0x1000000];
		for(int i=0;i<pixeldata.length;i++) {
			pixelcount[pixeldata[i]]++;
		}
		return pixelcount;
	}

	/**
	 * Creates a 24-bit-sized int array containing the pixel count of the given pixel array in int (x)RGB format, applying 
	 * the given masc before counting.
	 * 
	 * @param pixeldata
	 * @param masc
	 * @return
	 */
	public static int[] createPixelCountArray(final int[] pixeldata,final int masc) {
		final int[] pixelcount=new int[0x1000000];
		for(int i=0;i<pixeldata.length;i++) {
			pixelcount[pixeldata[i]&masc]++;
		}
		return pixelcount;
	}

}
