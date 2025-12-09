package lunartools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

class ImageToolsTest {

	@Test
	void imageIconIsCreatedFromResourceCorrectly() {
		ImageIcon imageIcon=ImageTools.createImageIcon("/RGB.png");
		assertEquals(1,imageIcon.getIconHeight());
		assertEquals(8,imageIcon.getIconWidth());
	}

	@Test
	void creatingImageIconFromNotExistingResourceReturnsNull() {
		ImageIcon imageIcon=ImageTools.createImageIcon("/FileDoesNotExist.png");
		assertNull(imageIcon);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void imageIsCreatedFromResourceCorrectly() throws Exception {
		Image image=ImageTools.createImageFromResource("/RGB.png");
		Frame frame=new Frame();
		MediaTracker mediaTracker=new MediaTracker(frame);
		mediaTracker.addImage(image, 0);
		try {
			mediaTracker.waitForAll();
		}finally {
			frame.dispose();
		}
		assertEquals(1,image.getHeight(null));
		assertEquals(8,image.getWidth(null));
	}

	@SuppressWarnings("deprecation")
	@Test
	void creatingImageFromNotExistingResourceReturnsNull() throws IOException {
		Image image=ImageTools.createImageFromResource("/FileDoesNotExist.png");
		assertNull(image);
	}

	@Test
	void intRgbBufferedImageIsCreatedCorrectlyFromResourceFile() throws Exception{
		URL url=ImageToolsTest.class.getResource("/RGB.png");
		File file=new File(url.getFile());
		BufferedImage bufferedImage=ImageTools.createBufferedImage_intRGB(file);
		DataBuffer dataBuffer=bufferedImage.getRaster().getDataBuffer();
		assertTrue(dataBuffer instanceof DataBufferInt);
		int[] imagedata=((DataBufferInt)dataBuffer).getData();
		assertEquals(0xff0000,imagedata[0]);
		assertEquals(0x00ff00,imagedata[1]);
		assertEquals(0x0000ff,imagedata[2]);
	}

	@Test
	void intRgbArrayIsDerivedCorrectlyFromResourceFile() throws Exception{
		URL url=ImageToolsTest.class.getResource("/RGB.png");
		File file=new File(url.getFile());
		BufferedImage bufferedImage=ImageTools.createBufferedImage_intRGB(file);
		int[] imagedata=ImageTools.getRgbIntsFromBufferedImage(bufferedImage);
		assertEquals(0xff0000,imagedata[0]);
		assertEquals(0x00ff00,imagedata[1]);
		assertEquals(0x0000ff,imagedata[2]);
	}

	@Test
	void intRgbArrayIsDerivedCorrectlyFromImageReadByImageIO() throws Exception{
		URL url=ImageToolsTest.class.getResource("/RGB.png");
		File file=new File(url.getFile());
		BufferedImage bufferedImage=ImageIO.read(file);
		int[] imagedata=ImageTools.getRgbIntsFromBufferedImage(bufferedImage);
		assertEquals(0xff0000,imagedata[0]);
		assertEquals(0x00ff00,imagedata[1]);
		assertEquals(0x0000ff,imagedata[2]);
	}

	@Test
	void intRgbArrayIsDerivedCorrectlyFromBufferedImageTypeIntArgb() throws Exception{
		BufferedImage bufferedImage=new BufferedImage(3,1,BufferedImage.TYPE_INT_ARGB);
		bufferedImage.setRGB(0, 0, 0x80ff0000);
		bufferedImage.setRGB(1, 0, 0x8000ff00);
		bufferedImage.setRGB(2, 0, 0x800000ff);
		int[] imagedata=ImageTools.getRgbIntsFromBufferedImage(bufferedImage);
		assertEquals(0xff0000,imagedata[0]);
		assertEquals(0x00ff00,imagedata[1]);
		assertEquals(0x0000ff,imagedata[2]);
	}

	@Test
	void intRgbArrayIsDerivedCorrectlyFromBufferedImageTypeIntRgb() throws Exception{
		BufferedImage bufferedImage=new BufferedImage(3,1,BufferedImage.TYPE_INT_RGB);
		bufferedImage.setRGB(0, 0, 0x80ff0000);
		bufferedImage.setRGB(1, 0, 0x8000ff00);
		bufferedImage.setRGB(2, 0, 0x800000ff);
		int[] imagedata=ImageTools.getRgbIntsFromBufferedImage(bufferedImage);
		assertEquals(0xff0000,imagedata[0]);
		assertEquals(0x00ff00,imagedata[1]);
		assertEquals(0x0000ff,imagedata[2]);
	}

	@Test
	void intRgbArrayIsDerivedCorrectlyFromBufferedImageType3ByteBgr() throws Exception{
		BufferedImage bufferedImage=new BufferedImage(3,1,BufferedImage.TYPE_3BYTE_BGR);
		bufferedImage.setRGB(0, 0, 0x80ff0000);
		bufferedImage.setRGB(1, 0, 0x8000ff00);
		bufferedImage.setRGB(2, 0, 0x800000ff);
		int[] imagedata=ImageTools.getRgbIntsFromBufferedImage(bufferedImage);
		assertEquals(0xff0000,imagedata[0]);
		assertEquals(0x00ff00,imagedata[1]);
		assertEquals(0x0000ff,imagedata[2]);
	}

	@Test
	void byteRgbArrayIsDerivedCorrectlyFromBufferedImageTypeIntRgb() throws Exception{
		BufferedImage bufferedImage=new BufferedImage(3,1,BufferedImage.TYPE_INT_RGB);
		bufferedImage.setRGB(0, 0, 0x80ff0000);
		bufferedImage.setRGB(1, 0, 0x8000ff00);
		bufferedImage.setRGB(2, 0, 0x800000ff);
		byte[] bytes=ImageTools.getRgbBytesFromBufferedImage(bufferedImage);
		//R
		assertEquals((byte)255,	bytes[0]);
		assertEquals(0,			bytes[1]);
		assertEquals(0,			bytes[2]);
		//G
		assertEquals(0,			bytes[3]);
		assertEquals((byte)255,	bytes[4]);
		assertEquals(0,			bytes[5]);
		//B
		assertEquals(0,			bytes[6]);
		assertEquals(0,			bytes[7]);
		assertEquals((byte)255,	bytes[8]);
	}
	
	@Test
	void byteRgbArrayIsDerivedCorrectlyFromBufferedImageTypeIntArgb() throws Exception{
		BufferedImage bufferedImage=new BufferedImage(3,1,BufferedImage.TYPE_INT_ARGB);
		bufferedImage.setRGB(0, 0, 0x80ff0000);
		bufferedImage.setRGB(1, 0, 0x8000ff00);
		bufferedImage.setRGB(2, 0, 0x800000ff);
		byte[] bytes=ImageTools.getRgbBytesFromBufferedImage(bufferedImage);
		//R
		assertEquals((byte)255,	bytes[0]);
		assertEquals(0,			bytes[1]);
		assertEquals(0,			bytes[2]);
		//G
		assertEquals(0,			bytes[3]);
		assertEquals((byte)255,	bytes[4]);
		assertEquals(0,			bytes[5]);
		//B
		assertEquals(0,			bytes[6]);
		assertEquals(0,			bytes[7]);
		assertEquals((byte)255,	bytes[8]);
	}
	
	@Test
	void byteRgbArrayIsDerivedCorrectlyFromImageType3ByteBgr() throws Exception{
		BufferedImage bufferedImage=new BufferedImage(3,1,BufferedImage.TYPE_3BYTE_BGR);
		bufferedImage.setRGB(0, 0, 0x80ff0000);
		bufferedImage.setRGB(1, 0, 0x8000ff00);
		bufferedImage.setRGB(2, 0, 0x800000ff);
		byte[] bytes=ImageTools.getRgbBytesFromBufferedImage(bufferedImage);
		//R
		assertEquals((byte)255,	bytes[0]);
		assertEquals(0,			bytes[1]);
		assertEquals(0,			bytes[2]);
		//G
		assertEquals(0,			bytes[3]);
		assertEquals((byte)255,	bytes[4]);
		assertEquals(0,			bytes[5]);
		//B
		assertEquals(0,			bytes[6]);
		assertEquals(0,			bytes[7]);
		assertEquals((byte)255,	bytes[8]);
	}
	
	@Test
	void byteRgbArrayIsDerivedCorrectlyFromImageReadByImageIO() throws Exception{
		URL url=ImageToolsTest.class.getResource("/RGB.png");
		File file=new File(url.getFile());
		BufferedImage bufferedImage=ImageIO.read(file);
		byte[] bytes=ImageTools.getRgbBytesFromBufferedImage(bufferedImage);
		//R
		assertEquals((byte)255,	bytes[0]);
		assertEquals(0,			bytes[1]);
		assertEquals(0,			bytes[2]);
		//G
		assertEquals(0,			bytes[3]);
		assertEquals((byte)255,	bytes[4]);
		assertEquals(0,			bytes[5]);
		//B
		assertEquals(0,			bytes[6]);
		assertEquals(0,			bytes[7]);
		assertEquals((byte)255,	bytes[8]);
	}
	
	@Test
	void rgbIntArrayIsIsWrittenBackToBufferedImageTypeIntRgbCorrectly() throws Exception{
		BufferedImage bufferedImage=new BufferedImage(3,1,BufferedImage.TYPE_INT_ARGB);
		bufferedImage.setRGB(0, 0, 0x80ff0000);
		bufferedImage.setRGB(1, 0, 0x8000ff00);
		bufferedImage.setRGB(2, 0, 0x800000ff);
		int[] rgbInts=((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
		
		int[] rgbIntsCopy=rgbInts.clone();
		rgbIntsCopy[0]=0x900000aa;
		rgbIntsCopy[1]=0x9000aa00;
		rgbIntsCopy[2]=0x90aa0000;
		
		ImageTools.writeRgbIntsToBufferedImage(rgbIntsCopy, bufferedImage);
		
		assertEquals(0x900000aa,rgbInts[0]);
		assertEquals(0x9000aa00,rgbInts[1]);
		assertEquals(0x90aa0000,rgbInts[2]);
	}
	
	@Test
	void rgbIntArrayIsIsWrittenBackToBufferedImageType3ByteBgrCorrectly() throws Exception{
		BufferedImage bufferedImage=new BufferedImage(3,1,BufferedImage.TYPE_3BYTE_BGR);
		bufferedImage.setRGB(0, 0, 0x80ff0000);
		bufferedImage.setRGB(1, 0, 0x8000ff00);
		bufferedImage.setRGB(2, 0, 0x800000ff);
		byte[] rgbBytes=((DataBufferByte)bufferedImage.getRaster().getDataBuffer()).getData();
		
		int[] rgbInts=new int[] {0x900000aa,0x9000aa00,0x90aa0000};
		
		ImageTools.writeRgbIntsToBufferedImage(rgbInts, bufferedImage);
		
		assertEquals((byte)0xaa,rgbBytes[0]);
		assertEquals((byte)0x00,rgbBytes[1]);
		assertEquals((byte)0x00,rgbBytes[2]);
		
		assertEquals((byte)0x00,rgbBytes[3]);
		assertEquals((byte)0xaa,rgbBytes[4]);
		assertEquals((byte)0x00,rgbBytes[5]);
		
		assertEquals((byte)0x00,rgbBytes[6]);
		assertEquals((byte)0x00,rgbBytes[7]);
		assertEquals((byte)0xaa,rgbBytes[8]);
	}
	
	@Test
	void intRgbIsCreatedFromByteBgrCorrectly(){
		byte[] bytes=new byte[] {1,2,3,4,5,6};
		int[] intRgb=ImageTools.createIntRGBfromByteBGR(bytes);

		assertEquals(2,intRgb.length);
		assertEquals(0x00030201,intRgb[0]);
		assertEquals(0x00060504,intRgb[1]);
	}
	
	@Test
	void byteRgbIsCreatedFromIntRgbCorrectly(){
		int[] intRgb=new int[1];
		intRgb[0]=0x80112233;
		byte[] bytes=ImageTools.createByteRGBfromIntRGB(intRgb);
		assertEquals(3,bytes.length);
		assertEquals(0x11,bytes[0]);
		assertEquals(0x22,bytes[1]);
		assertEquals(0x33,bytes[2]);
	}
	
	@Test
	void byteGreyscaleIsCreatedFromIntGreyscaleCorrectly(){
		int[] intRgb=new int[1];
		intRgb[0]=0x80111111;
		byte[] bytes=ImageTools.createByteGreyscaleFromIntGreyscale(intRgb);
		assertEquals(1,bytes.length);
		assertEquals(0x11,bytes[0]);
	}
	
	@Test
	void mascIsAppliedToPixeldataCorrectly() {
		int[] intRgb=new int[1];
		intRgb[0]=0x80112233;

		ImageTools.applyMascToPixeldata(intRgb,0xff00);
		
		assertEquals(0x00002200,intRgb[0]);
	}
	
	@Test
	void pixelCountArrayFromIntRgbIsCreatedCorrectly() {
		int[] intRgb=new int[3];
		intRgb[0]=0x112233;
		intRgb[1]=0x332211;
		intRgb[2]=0x112233;
	
		int[] pixelCount=ImageTools.createPixelCountArray(intRgb);
		
		assertEquals(2,pixelCount[0x112233]);
		assertEquals(1,pixelCount[0x332211]);
	}
	
	@Test
	void pixelCountArrayFromMascedIntRgbIsCreatedCorrectly() {
		int[] intRgb=new int[3];
		intRgb[0]=0x80112233;
		intRgb[1]=0x80332211;
		intRgb[2]=0x80112233;
	
		int[] pixelCount=ImageTools.createPixelCountArray(intRgb,0xffff00);
		
		assertEquals(2,pixelCount[0x112200]);
		assertEquals(1,pixelCount[0x332200]);
	}
	
	@Test
	void byteRGBswapRBWorksCorrectly() {
		byte[] bytes=new byte[] {1,2,3,4,5,6};
		
		ImageTools.byteRGBswapRB(bytes);
		
		byte[] bytesExpected=new byte[] {3,2,1,6,5,4};
		assertTrue(Arrays.equals(bytesExpected, bytes));
	}
}
