package lunartools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ByteToolsTest {

	@Test
	void positiveIntLongwordIsConvertedToBigEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {0x01,0x02,(byte)0x80,(byte)0x90};
		int longword=0x01028090;
		assertArrayEquals(expected,ByteTools.bLongwordToBytearray(longword));
	}

	@Test
	void positiveIntLongwordIsConvertedToLittleEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {(byte)0x90,(byte)0x80,0x02,0x01};
		int longword=0x01028090;
		assertArrayEquals(expected,ByteTools.lLongwordToBytearray(longword));
	}

	@Test
	void negativeIntLongwordIsConvertedToBigEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {(byte)0xff,(byte)0xfc,(byte)0xfb,(byte)0xfa};
		int longword=0xfffcfbfa;
		assertArrayEquals(expected,ByteTools.bLongwordToBytearray(longword));
	}

	@Test
	void negativeIntLongwordIsConvertedToLittleEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {(byte)0xfa,(byte)0xfb,(byte)0xfc,(byte)0xff};
		int longword=0xfffcfbfa;
		assertArrayEquals(expected,ByteTools.lLongwordToBytearray(longword));
	}

	@Test
	void positiveLongLongwordIsConvertedToBigEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {0x01,0x02,(byte)0x80,(byte)0x90};
		long longword=0x01028090;
		assertArrayEquals(expected,ByteTools.bLongwordToBytearray(longword));
	}

	@Test
	void positiveLongLongwordIsConvertedToLittleEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {(byte)0x90,(byte)0x80,0x02,0x01};
		long longword=0x01028090;
		assertArrayEquals(expected,ByteTools.lLongwordToBytearray(longword));
	}

	@Test
	void negativeLongLongwordIsConvertedToBigEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff};
		long longword=0xffffffff;
		assertArrayEquals(expected,ByteTools.bLongwordToBytearray(longword));
	}

	@Test
	void negativeLongLongwordIsConvertedToLittleEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {(byte)0xfa,(byte)0xfb,(byte)0xfc,(byte)0xff};
		int longword=0xfffcfbfa;
		assertArrayEquals(expected,ByteTools.lLongwordToBytearray(longword));
	}

	@Test
	void positiveIntWordIsConvertedToBigEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {0x02,(byte)0x80};
		int word=0x0280;
		assertArrayEquals(expected,ByteTools.bWordToBytearray(word));
	}

	@Test
	void positiveIntWordIsConvertedToLittleEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {(byte)0x80,0x02};
		int word=0x0280;
		assertArrayEquals(expected,ByteTools.lWordToBytearray(word));
	}

	@Test
	void negativeIntWordIsConvertedToBigEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {(byte)0xff,(byte)0xff};
		int word=0xffff;
		assertArrayEquals(expected,ByteTools.bWordToBytearray(word));
	}

	@Test
	void negativeIntWordIsConvertedToLittleEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {(byte)0xfa,(byte)0xff};
		int word=0xfffa;
		assertArrayEquals(expected,ByteTools.lWordToBytearray(word));
	}

	@Test
	void positiveLongValueIsExtractedFromBigEndianBytearrayCorrectly() {
		long expected=0x01028090;
		byte[] ba=new byte[] {0x11,0x11,0x01,0x02,(byte)0x80,(byte)0x90,0x11,0x11};
		int offsetToWantedBytearray=2;
		assertEquals(expected,ByteTools.bBytearrayToLongword(ba,offsetToWantedBytearray));
	}

	@Test
	void positiveSignedLongValueIsExtractedFromBigEndianBytearrayCorrectly() {
		long expected=0x01028090;
		byte[] ba=new byte[] {0x11,0x11,0x01,0x02,(byte)0x80,(byte)0x90,0x11,0x11};
		int offsetToWantedBytearray=2;
		assertEquals(expected,ByteTools.bBytearrayToSignedLongword(ba,offsetToWantedBytearray));
	}

	@Test
	void positiveLongValueIsExtractedFromLittleEndianBytearrayCorrectly() {
		long expected=0x01028090;
		byte[] ba=new byte[] {0x11,0x11,(byte)0x90,(byte)0x80,0x02,0x01,0x11,0x11};
		int offsetToWantedBytearray=2;
		assertEquals(expected,ByteTools.lBytearrayToLongword(ba,offsetToWantedBytearray));
	}

	@Test
	void positiveSignedLongValueIsExtractedFromLittleEndianBytearrayCorrectly() {
		long expected=0x01028090;
		byte[] ba=new byte[] {0x11,0x11,(byte)0x90,(byte)0x80,0x02,0x01,0x11,0x11};
		int offsetToWantedBytearray=2;
		assertEquals(expected,ByteTools.lBytearrayToSignedLongword(ba,offsetToWantedBytearray));
	}

	@Test
	void negativeLongValueIsExtractedFromBigEndianBytearrayCorrectly() {
		long expected=0xff0280ffl;
		byte[] ba=new byte[] {0x11,(byte)0xff,0x02,(byte)0x80,(byte)0xff,0x11,0x11};
		int offsetToWantedBytearray=1;
		assertEquals(expected,ByteTools.bBytearrayToLongword(ba,offsetToWantedBytearray));
	}

	@Test
	void negativeSignedLongValueIsExtractedFromBigEndianBytearrayCorrectly() {
		long expected=-16909061l;
		byte[] ba=new byte[] {0x11,(byte)0xfe,(byte)0xfd,(byte)0xfc,(byte)0xfb,0x11,0x11};
		int offsetToWantedBytearray=1;
		assertEquals(expected,ByteTools.bBytearrayToSignedLongword(ba,offsetToWantedBytearray));
	}

	@Test
	void negativeLongValueIsExtractedFromLittleEndianBytearrayCorrectly() {
		long expected=0xff0280ffl;
		byte[] ba=new byte[] {0x11,(byte)0xff,(byte)0x80,0x02,(byte)0xff,0x11,0x11};
		int offsetToWantedBytearray=1;
		assertEquals(expected,ByteTools.lBytearrayToLongword(ba,offsetToWantedBytearray));
	}

	@Test
	void negativeSignedLongValueIsExtractedFromLittleEndianBytearrayCorrectly() {
		long expected=-16909061l;
		byte[] ba=new byte[] {0x11,(byte)0xfb,(byte)0xfc,(byte)0xfd,(byte)0xfe,0x11,0x11};
		int offsetToWantedBytearray=1;
		assertEquals(expected,ByteTools.lBytearrayToSignedLongword(ba,offsetToWantedBytearray));
	}


	@Test
	void positiveWordValueIsExtractedFromBigEndianBytearrayCorrectly() {
		int expected=0x0180;
		byte[] ba=new byte[] {0x11,0x11,0x01,(byte)0x80,0x11,0x11};
		int offsetToWantedBytearray=2;
		assertEquals(expected,ByteTools.bBytearrayToWord(ba,offsetToWantedBytearray));
	}

	@Test
	void positiveSignedWordValueIsExtractedFromBigEndianBytearrayCorrectly() {
		int expected=0x0180;
		byte[] ba=new byte[] {0x11,0x11,0x01,(byte)0x80,0x11,0x11};
		int offsetToWantedBytearray=2;
		assertEquals(expected,ByteTools.bBytearrayToSignedWord(ba,offsetToWantedBytearray));
	}

	@Test
	void positiveWordValueIsExtractedFromLittleEndianBytearrayCorrectly() {
		int expected=0x0180;
		byte[] ba=new byte[] {0x11,0x11,(byte)0x80,0x01,0x11,0x11};
		int offsetToWantedBytearray=2;
		assertEquals(expected,ByteTools.lBytearrayToWord(ba,offsetToWantedBytearray));
	}

	@Test
	void positiveSignedWordValueIsExtractedFromLittleEndianBytearrayCorrectly() {
		int expected=0x0180;
		byte[] ba=new byte[] {0x11,0x11,(byte)0x80,0x01,0x11,0x11};
		int offsetToWantedBytearray=2;
		assertEquals(expected,ByteTools.lBytearrayToSignedWord(ba,offsetToWantedBytearray));
	}

	@Test
	void negativeWordValueIsExtractedFromBigEndianBytearrayCorrectly() {
		int expected=0xff02;
		byte[] ba=new byte[] {0x11,(byte)0xff,0x02,0x11,0x11};
		int offsetToWantedBytearray=1;
		assertEquals(expected,ByteTools.bBytearrayToWord(ba,offsetToWantedBytearray));
	}

	@Test
	void negativeSignedWordValueIsExtractedFromBigEndianBytearrayCorrectly() {
		int expected=-2;
		byte[] ba=new byte[] {0x11,(byte)0xff,(byte)0xfe,0x11,0x11};
		int offsetToWantedBytearray=1;
		assertEquals(expected,ByteTools.bBytearrayToSignedWord(ba,offsetToWantedBytearray));
	}

	@Test
	void negativeWordValueIsExtractedFromLittleEndianBytearrayCorrectly() {
		int expected=0xff02;
		byte[] ba=new byte[] {0x11,0x02,(byte)0xff,0x11,0x11};
		int offsetToWantedBytearray=1;
		assertEquals(expected,ByteTools.lBytearrayToWord(ba,offsetToWantedBytearray));
	}

	@Test
	void negativeSignedWordValueIsExtractedFromLittleEndianBytearrayCorrectly() {
		int expected=-2;
		byte[] ba=new byte[] {0x11,(byte)0xfe,(byte)0xff,0x11,0x11};
		int offsetToWantedBytearray=1;
		assertEquals(expected,ByteTools.lBytearrayToSignedWord(ba,offsetToWantedBytearray));
	}


	@Test
	void positiveByteIsReadFromBytearrayCorrectly() {
		int expected=0x14;
		byte[] bytearray=new byte[]	{0x01,0x02,0x14,0x04,0x05};
		int offsetToByte=2;
		assertEquals(expected,ByteTools.bytearrayToByte(bytearray, offsetToByte));
	}

	@Test
	void negativeByteIsReadFromBytearrayCorrectly() {
		int expected=0xff;
		byte[] bytearray=new byte[] {0x01,0x02,(byte)0xff,0x04,0x05};
		int offsetToByte=2;
		assertEquals(expected,ByteTools.bytearrayToByte(bytearray, offsetToByte));
	}

	@Test
	void positiveWordIsWrittenToBigEndianBytearrayCorrectly() {
		byte[] bytearray=new byte[]	{0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08};
		byte[] expected=new byte[]	{0x01,0x02,0x11,0x12,0x05,0x06,0x07,0x08};
		int word=0x1112;
		int offsetToWriteWord=2;
		ByteTools.bWriteWordToBytearray(bytearray,offsetToWriteWord,word);
		assertArrayEquals(expected,bytearray);
	}

	@Test
	void positiveWordIsWrittenToLittleEndianBytearrayCorrectly() {
		byte[] bytearray=new byte[]	{0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08};
		byte[] expected=new byte[]	{0x01,0x02,0x12,0x11,0x05,0x06,0x07,0x08};
		int word=0x1112;
		int offsetToWriteWord=2;
		ByteTools.lWriteWordToBytearray(bytearray,offsetToWriteWord,word);
		assertArrayEquals(expected,bytearray);
	}

	@Test
	void negativeWordIsWrittenToBigEndianBytearrayCorrectly() {
		byte[] bytearray=new byte[]	{0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08};
		byte[] expected=new byte[]	{0x01,0x02,(byte)0xff,(byte)0xfe,0x05,0x06,0x07,0x08};
		int word=0xfffe;
		int offsetToWriteWord=2;
		ByteTools.bWriteWordToBytearray(bytearray,offsetToWriteWord,word);
		assertArrayEquals(expected,bytearray);
	}

	@Test
	void negativeWordIsWrittenToLittleEndianBytearrayCorrectly() {
		byte[] bytearray=new byte[]	{0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08};
		byte[] expected=new byte[]	{0x01,0x02,(byte)0xfe,(byte)0xff,0x05,0x06,0x07,0x08};
		int word=0xfffe;
		int offsetToWriteWord=2;
		ByteTools.lWriteWordToBytearray(bytearray,offsetToWriteWord,word);
		assertArrayEquals(expected,bytearray);
	}

	@Test
	void positiveLongwordIsWrittenToBigEndianBytearrayCorrectly() {
		byte[] bytearray=new byte[]	{0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08};
		byte[] expected=new byte[]	{0x01,0x02,0x11,0x12,0x13,0x14,0x07,0x08};
		int longword=0x11121314;
		int offsetToWriteLongword=2;
		ByteTools.bWriteLongwordToBytearray(bytearray,offsetToWriteLongword,longword);
		assertArrayEquals(expected,bytearray);
	}

	@Test
	void positiveLongwordIsWrittenToLittleEndianBytearrayCorrectly() {
		byte[] bytearray=new byte[]	{0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08};
		byte[] expected=new byte[]	{0x01,0x02,0x14,0x13,0x12,0x11,0x07,0x08};
		int longword=0x11121314;
		int offsetToWriteLongword=2;
		ByteTools.lWriteLongwordToBytearray(bytearray,offsetToWriteLongword,longword);
		assertArrayEquals(expected,bytearray);
	}

	@Test
	void negativeLongwordIsWrittenToBigEndianBytearrayCorrectly() {
		byte[] bytearray=new byte[]	{0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08};
		byte[] expected=new byte[]	{0x01,0x02,(byte)0xff,(byte)0xfe,(byte)0xfd,(byte)0xfc,0x07,0x08};
		int longword=0xfffefdfc;
		int offsetToWriteLongword=2;
		ByteTools.bWriteLongwordToBytearray(bytearray,offsetToWriteLongword,longword);
		assertArrayEquals(expected,bytearray);
	}

	@Test
	void negativeLongwordIsWrittenToLittleEndianBytearrayCorrectly() {
		byte[] bytearray=new byte[]	{0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08};
		byte[] expected=new byte[]	{0x01,0x02,(byte)0xfc,(byte)0xfd,(byte)0xfe,(byte)0xff,0x07,0x08};
		int longword=0xfffefdfc;
		int offsetToWriteLongword=2;
		ByteTools.lWriteLongwordToBytearray(bytearray,offsetToWriteLongword,longword);
		assertArrayEquals(expected,bytearray);
	}

	@Test
	void positiveHexStringIsConvertedToBigEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {0x01,0x02,0x03,0x04};
		assertArrayEquals(expected,ByteTools.bHexStringToByteArray("01020304"));
	}

	@Test
	void negativeHexStringIsConvertedToBigEndianBytearrayCorrectly() {
		byte[] expected=new byte[] {(byte)0xff,(byte)0xfe,(byte)0xfd,(byte)0xfc};
		assertArrayEquals(expected,ByteTools.bHexStringToByteArray("fffefdfc"));
	}

	@Test
	void compareBytesReturnsTrueCorrectly() {
		byte[] bytearray1=new byte[] {0x01,0x02,0x03,0x04};
		byte[] bytearray2=new byte[] {0x00,0x00,0x01,0x02,0x03,0x04,0x00,0x00};
		int offset=2;
		assertTrue(ByteTools.compareBytes(bytearray1, bytearray2, offset));
	}

	@Test
	void compareBytesReturnsFalseCorrectly() {
		byte[] bytearray1=new byte[] {0x01,0x02,0x03,0x04};
		byte[] bytearray2=new byte[] {0x00,0x00,0x01,0x00,0x03,0x04,0x00,0x00};
		int offset=2;
		assertFalse(ByteTools.compareBytes(bytearray1, bytearray2, offset));
	}

}
