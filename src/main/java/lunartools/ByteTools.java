package lunartools;

public class ByteTools {

	/**
	 * Converts a longword, given as int value, to a big-endian bytearray.
	 * 
	 * @param longword
	 * @return big endian bytearray
	 * @Deprecated Renamed to <code>longwordToByteArrayBigEndian(int longword)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static byte[] bLongwordToBytearray(int longword) {
		int byte0=(longword>>24)&0xff;
		int byte1=(longword>>16)&0xff;
		int byte2=(longword>>8)&0xff;
		int byte3=longword&0xff;
		return new byte[] {(byte)byte0,(byte)byte1,(byte)byte2,(byte)byte3};
	}

	/**
	 * Converts a longword, given as int value, to a big-endian bytearray.
	 * 
	 * @param longword
	 * @return big endian bytearray
	 */
	public static byte[] longwordToByteArrayBigEndian(int longword) {
		int byte0=(longword>>24)&0xff;
		int byte1=(longword>>16)&0xff;
		int byte2=(longword>>8)&0xff;
		int byte3=longword&0xff;
		return new byte[] {(byte)byte0,(byte)byte1,(byte)byte2,(byte)byte3};
	}

	/**
	 * Converts a longword, given as int value, to a little-endian bytearray.
	 * 
	 * @param longword
	 * @return little endian bytearray
	 * @Deprecated Renamed to <code>longwordToByteArrayLittleEndian(int longword)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static byte[] lLongwordToBytearray(int longword) {
		int byte0=(longword>>24)&0xff;
		int byte1=(longword>>16)&0xff;
		int byte2=(longword>>8)&0xff;
		int byte3=longword&0xff;
		return new byte[] {(byte)byte3,(byte)byte2,(byte)byte1,(byte)byte0};
	}

	/**
	 * Converts a longword, given as int value, to a little-endian bytearray.
	 * 
	 * @param longword
	 * @return little endian bytearray
	 */
	public static byte[] longwordToByteArrayLittleEndian(int longword) {
		int byte0=(longword>>24)&0xff;
		int byte1=(longword>>16)&0xff;
		int byte2=(longword>>8)&0xff;
		int byte3=longword&0xff;
		return new byte[] {(byte)byte3,(byte)byte2,(byte)byte1,(byte)byte0};
	}

	/**
	 * Converts a longword, given as long value, to a big-endian bytearray.
	 * 
	 * @param longword
	 * @return big endian bytearray
	 * @Deprecated Renamed to <code>longwordToByteArrayBigEndian(long longword)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static byte[] bLongwordToBytearray(long longword) {
		long byte0=(longword>>24)&0xff;
		long byte1=(longword>>16)&0xff;
		long byte2=(longword>>8)&0xff;
		long byte3=longword&0xff;
		return new byte[] {(byte)byte0,(byte)byte1,(byte)byte2,(byte)byte3};
	}

	/**
	 * Converts a longword, given as long value, to a big-endian bytearray.
	 * 
	 * @param longword
	 * @return big endian bytearray
	 */
	public static byte[] longwordToByteArrayBigEndian(long longword) {
		long byte0=(longword>>24)&0xff;
		long byte1=(longword>>16)&0xff;
		long byte2=(longword>>8)&0xff;
		long byte3=longword&0xff;
		return new byte[] {(byte)byte0,(byte)byte1,(byte)byte2,(byte)byte3};
	}

	/**
	 * Converts a longword, given as long value, to a little-endian bytearray.
	 * 
	 * @param longword
	 * @return little endian bytearray
	 * @Deprecated Renamed to <code>longwordToByteArrayLittleEndian(long longword)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static byte[] lLongwordToBytearray(long longword) {
		long byte0=(longword>>24)&0xff;
		long byte1=(longword>>16)&0xff;
		long byte2=(longword>>8)&0xff;
		long byte3=longword&0xff;
		return new byte[] {(byte)byte3,(byte)byte2,(byte)byte1,(byte)byte0};
	}

	/**
	 * Converts a longword, given as long value, to a little-endian bytearray.
	 * 
	 * @param longword
	 * @return little endian bytearray
	 */
	public static byte[] longwordToByteArrayLittleEndian(long longword) {
		long byte0=(longword>>24)&0xff;
		long byte1=(longword>>16)&0xff;
		long byte2=(longword>>8)&0xff;
		long byte3=longword&0xff;
		return new byte[] {(byte)byte3,(byte)byte2,(byte)byte1,(byte)byte0};
	}

	/**
	 * Converts a word, given as int value, to a big-endian bytearray.
	 * 
	 * @param word
	 * @return big endian bytearray
	 * @Deprecated Renamed to <code>wordToBytearrayBigEndian(int word)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static byte[] bWordToBytearray(int word) {
		int byte0=(word>>8)&0xff;
		int byte1=word&0xff;
		return new byte[] {(byte)byte0,(byte)byte1};
	}

	/**
	 * Converts a word, given as int value, to a big-endian bytearray.
	 * 
	 * @param word
	 * @return big endian bytearray
	 */
	public static byte[] wordToBytearrayBigEndian(int word) {
		int byte0=(word>>8)&0xff;
		int byte1=word&0xff;
		return new byte[] {(byte)byte0,(byte)byte1};
	}

	/**
	 * Converts a word, given as int value, to a little-endian bytearray.
	 * 
	 * @param word
	 * @return little endian bytearray
	 * @Deprecated Renamed to <code>wordToBytearrayLittleEndian(int word)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static byte[] lWordToBytearray(int word) {
		int byte0=(word>>8)&0xff;
		int byte1=word&0xff;
		return new byte[] {(byte)byte1,(byte)byte0};
	}

	/**
	 * Converts a word, given as int value, to a little-endian bytearray.
	 * 
	 * @param word
	 * @return little endian bytearray
	 */
	public static byte[] wordToBytearrayLittleEndian(int word) {
		int byte0=(word>>8)&0xff;
		int byte1=word&0xff;
		return new byte[] {(byte)byte1,(byte)byte0};
	}

	/**
	 * Converts 4 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a longword.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytearray The bytearray containing a longword
	 * @param offset The offset to the to the longword
	 * @return The longword created from a bytearry in big endian byteorder
	 * @Deprecated Renamed to <code>bigEndianBytesToLongWord(byte[] bytearray, int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static long bBytearrayToLongword(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int byte2=bytearray[offset+2]&0xff;
		int byte3=bytearray[offset+3]&0xff;
		return ((long)byte0<<24)|(byte1<<16)|(byte2<<8)|byte3;
	}

	/**
	 * Converts 4 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a longword.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytearray The bytearray containing a longword
	 * @param offset The offset to the to the longword
	 * @return The longword created from a bytearry in big endian byteorder
	 */
	public static long bigEndianBytesToLongWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int byte2=bytearray[offset+2]&0xff;
		int byte3=bytearray[offset+3]&0xff;
		return ((long)byte0<<24)|(byte1<<16)|(byte2<<8)|byte3;
	}

	/**
	 * Converts 4 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a longword.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytearray The bytearray containing a longword
	 * @param offset The offset to the to the longword
	 * @return The longword created from a bytearry in little endian byteorder
	 * @Deprecated Renamed to <code>littleEndianBytesToLongWord(byte[] bytearray, int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static long lBytearrayToLongword(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int byte2=bytearray[offset+2]&0xff;
		int byte3=bytearray[offset+3]&0xff;
		return ((long)byte3<<24)|(byte2<<16)|(byte1<<8)|byte0;
	}

	/**
	 * Converts 4 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a longword.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytearray The bytearray containing a longword
	 * @param offset The offset to the to the longword
	 * @return The longword created from a bytearry in little endian byteorder
	 */
	public static long littleEndianBytesToLongWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int byte2=bytearray[offset+2]&0xff;
		int byte3=bytearray[offset+3]&0xff;
		return ((long)byte3<<24)|(byte2<<16)|(byte1<<8)|byte0;
	}

	/**
	 * Converts 4 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a signed longword.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytearray The bytearray containing a longword
	 * @param offset The offset to the to the longword
	 * @return The signed longword created from a bytearry in big endian byteorder
	 * @Deprecated Renamed to <code>bigEndianBytesToSignedLongWord(byte[] bytearray, int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static long bBytearrayToSignedLongword(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int byte2=bytearray[offset+2]&0xff;
		int byte3=bytearray[offset+3]&0xff;
		long result=((long)byte0<<24)|(byte1<<16)|(byte2<<8)|byte3;
		return (result & 0b10000000000000000000000000000000)==0?result:result-0x100000000l;
	}

	/**
	 * Converts 4 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a signed longword.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytearray The bytearray containing a longword
	 * @param offset The offset to the to the longword
	 * @return The signed longword created from a bytearry in big endian byteorder
	 */
	public static long bigEndianBytesToSignedLongWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int byte2=bytearray[offset+2]&0xff;
		int byte3=bytearray[offset+3]&0xff;
		long result=((long)byte0<<24)|(byte1<<16)|(byte2<<8)|byte3;
		return (result & 0b10000000000000000000000000000000)==0?result:result-0x100000000l;
	}

	/**
	 * Converts 4 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a signed longword.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytearray The bytearray containing a longword
	 * @param offset The offset to the to the longword
	 * @return The signed longword created from a bytearry in little endian byteorder
	 * @Deprecated Renamed to <code>littleEndianBytesToSignedLongWord(byte[] bytearray, int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static long lBytearrayToSignedLongword(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int byte2=bytearray[offset+2]&0xff;
		int byte3=bytearray[offset+3]&0xff;
		long result=((long)byte3<<24)|(byte2<<16)|(byte1<<8)|byte0;
		return (result & 0b10000000000000000000000000000000)==0?result:result-0x100000000l;
	}

	/**
	 * Converts 4 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a signed longword.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytearray The bytearray containing a longword
	 * @param offset The offset to the to the longword
	 * @return The signed longword created from a bytearry in little endian byteorder
	 */
	public static long littleEndianBytesToSignedLongWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int byte2=bytearray[offset+2]&0xff;
		int byte3=bytearray[offset+3]&0xff;
		long result=((long)byte3<<24)|(byte2<<16)|(byte1<<8)|byte0;
		return (result & 0b10000000000000000000000000000000)==0?result:result-0x100000000l;
	}

	/**
	 * Converts 2 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a word.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytearray The bytearray containing a word
	 * @param offset The offset to the to the word
	 * @return The word created from a bytearry in big endian byteorder
	 * @Deprecated Renamed to <code>bigEndianBytesToWord(byte[] bytearray, int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static int bBytearrayToWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		return (byte0<<8)|byte1;
	}

	/**
	 * Converts 2 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a word.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytearray The bytearray containing a word
	 * @param offset The offset to the to the word
	 * @return The word created from a bytearry in big endian byteorder
	 */
	public static int bigEndianBytesToWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		return (byte0<<8)|byte1;
	}

	/**
	 * Converts 2 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a word.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytearray The bytearray containing a word
	 * @param offset The offset to the to the word
	 * @return The word created from a bytearry in little endian byteorder
	 * @Deprecated Renamed to <code>littleEndianBytesToWord(byte[] bytearray, int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static int lBytearrayToWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		return (byte1<<8)|byte0;
	}

	/**
	 * Converts 2 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a word.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytearray The bytearray containing a word
	 * @param offset The offset to the to the word
	 * @return The word created from a bytearry in little endian byteorder
	 */
	public static int littleEndianBytesToWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		return (byte1<<8)|byte0;
	}

	/**
	 * Converts 2 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a signed word.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytearray The bytearray containing a word
	 * @param offset The offset to the to the word
	 * @return The signed word created from a bytearry in big endian byteorder
	 * @Deprecated Renamed to <code>bigEndianBytesToSignedWord(byte[] bytearray, int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static int bBytearrayToSignedWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int result=(byte0<<8)|byte1;
		return (result & 0b1000000000000000)==0?result:result-0x10000;
	}

	/**
	 * Converts 2 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a signed word.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytearray The bytearray containing a word
	 * @param offset The offset to the to the word
	 * @return The signed word created from a bytearry in big endian byteorder
	 */
	public static int bigEndianBytesToSignedWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int result=(byte0<<8)|byte1;
		return (result & 0b1000000000000000)==0?result:result-0x10000;
	}

	/**
	 * Converts 2 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a signed word.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytearray The bytearray containing a word
	 * @param offset The offset to the to the word
	 * @return The signed word created from a bytearry in little endian byteorder
	 * @Deprecated Renamed to <code>littleEndianBytesToSignedWord(byte[] bytearray, int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static int lBytearrayToSignedWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int result=(byte1<<8)|byte0;
		return (result & 0b1000000000000000)==0?result:result-0x10000;
	}

	/**
	 * Converts 2 bytes, beginning from <code>offset</code> of the given bytearray to
	 * a signed word.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytearray The bytearray containing a word
	 * @param offset The offset to the to the word
	 * @return The signed word created from a bytearry in little endian byteorder
	 */
	public static int littleEndianBytesToSignedWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int result=(byte1<<8)|byte0;
		return (result & 0b1000000000000000)==0?result:result-0x10000;
	}

	/**
	 * Writes a longword to a bytearray at the given offset, overwriting the bytes.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytes The bytearray to write the longword
	 * @param offset offset to the bytearray position to write the longword
	 * @param longword The longword
	 * @Deprecated Renamed to <code>putLongWordAtPositionBigEndian(long longword,byte[] bytes,int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static void bWriteLongwordToBytearray(byte[] bytes,int offset,long longword) {
		byte[] ba=bLongwordToBytearray(longword);
		for(int i=0;i<4;i++) {
			bytes[offset+i]=ba[i];
		}
	}

	/**
	 * Writes a longword to a bytearray at the given offset, overwriting the bytes.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytes The bytearray to write the longword
	 * @param offset offset to the bytearray position to write the longword
	 * @param longword The longword
	 */
	public static void putLongWordAtPositionBigEndian(long longword,byte[] bytes,int offset) {
		byte[] ba=longwordToByteArrayBigEndian(longword);
		for(int i=0;i<4;i++) {
			bytes[offset+i]=ba[i];
		}
	}

	/**
	 * Writes a longword to a bytearray at the given offset, overwriting the bytes.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytes The bytearray to write the longword
	 * @param offset offset to the bytearray position to write the longword
	 * @param longword The longword
	 * @Deprecated Renamed to <code>putLongWordAtPositionLittleEndian(long longword,byte[] bytes,int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static void lWriteLongwordToBytearray(byte[] bytes,int offset,long longword) {
		byte[] ba=lLongwordToBytearray(longword);
		for(int i=0;i<4;i++) {
			bytes[offset+i]=ba[i];
		}
	}

	/**
	 * Writes a longword to a bytearray at the given offset, overwriting the bytes.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytes The bytearray to write the longword
	 * @param offset offset to the bytearray position to write the longword
	 * @param longword The longword
	 */
	public static void putLongWordAtPositionLittleEndian(long longword,byte[] bytes,int offset) {
		byte[] ba=longwordToByteArrayLittleEndian(longword);
		for(int i=0;i<4;i++) {
			bytes[offset+i]=ba[i];
		}
	}

	/**
	 * Writes a word to a bytearray at the given offset, overwriting the bytes.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytes The bytearray to write the word
	 * @param offset offset to the bytearray position to write the word
	 * @param word The word
	 * @Deprecated Renamed to <code>putWordAtPositionBigEndian(int word,byte[] bytes,int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static void bWriteWordToBytearray(byte[] bytes,int offset,int word) {
		byte[] ba=bWordToBytearray(word);
		bytes[offset]=ba[0];
		bytes[offset+1]=ba[1];
	}

	/**
	 * Writes a word to a bytearray at the given offset, overwriting the bytes.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytes The bytearray to write the word
	 * @param offset offset to the bytearray position to write the word
	 * @param word The word
	 */
	public static void putWordAtPositionBigEndian(int word,byte[] bytes,int offset) {
		byte[] ba=wordToBytearrayBigEndian(word);
		bytes[offset]=ba[0];
		bytes[offset+1]=ba[1];
	}

	/**
	 * Writes a word to a bytearray at the given offset, overwriting the bytes.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytes The bytearray to write the word
	 * @param offset offset to the bytearray position to write the word
	 * @param word The word
	 * @Deprecated Renamed to <code>putWordAtPositionLittleEndian(int word,byte[] bytes,int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static void lWriteWordToBytearray(byte[] bytes,int offset,int word) {
		byte[] ba=lWordToBytearray(word);
		bytes[offset]=ba[0];
		bytes[offset+1]=ba[1];
	}

	/**
	 * Writes a word to a bytearray at the given offset, overwriting the bytes.
	 * <br>The byte order is little endian.
	 * 
	 * @param bytes The bytearray to write the word
	 * @param offset offset to the bytearray position to write the word
	 * @param word The word
	 */
	public static void putWordAtPositionLittleEndian(int word,byte[] bytes,int offset) {
		byte[] ba=wordToBytearrayLittleEndian(word);
		bytes[offset]=ba[0];
		bytes[offset+1]=ba[1];
	}

	/**
	 * Reads a byte from a bytearray and returns it as int.
	 * 
	 * @param bytearray The bytearray
	 * @param offset The offset to the byte
	 * @return The byte as int
	 * @Deprecated Renamed to <code>bytesToByte(byte[] bytearray, int offset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static int bytearrayToByte(byte[] bytearray, int offset) {
		return bytearray[offset]&0xff;
	}

	/**
	 * Reads a byte from a bytearray and returns it as int.
	 * 
	 * @param bytearray The bytearray
	 * @param offset The offset to the byte
	 * @return The byte as int
	 */
	public static int bytesToByte(byte[] bytearray, int offset) {
		return bytearray[offset]&0xff;
	}

	/**
	 * Converts a hex string to a bytearray.
	 * <br>The byte order is big endian.
	 * 
	 * @param hexstring
	 * @return
	 * @Deprecated Renamed to <code>hexStringToByteArrayBigEndian(String hexString)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static byte[] bHexStringToByteArray(String hexstring) {
		byte[] ba=new byte[hexstring.length()>>1];
		for(int i=0;i<hexstring.length();i+=2) {
			int bytevalue=Integer.parseInt(hexstring.substring(i, i+2), 16);
			ba[i>>1]=(byte)bytevalue;
		}
		return ba;
	}

	/**
	 * Converts a hex string to a bytearray.
	 * <br>The byte order is big endian.
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStringToByteArrayBigEndian(String hexString) {
		byte[] ba=new byte[hexString.length()>>1];
		for(int i=0;i<hexString.length();i+=2) {
			int bytevalue=Integer.parseInt(hexString.substring(i, i+2), 16);
			ba[i>>1]=(byte)bytevalue;
		}
		return ba;
	}

	/**
	 * Checks if one bytearray is equal another bytearray, or part of another bytearray
	 * at a given offset.
	 * 
	 * @param byteArray1 The first bytearray, size is smaller or same than the other bytearray
	 * @param byteArray2 Ther second bytearray, size is bigger or same than the other bytearray
	 * @param offsetByteArray2 Offset to the second bytearray 
	 * @return <code>true</code< if the bytes of bytearray1 and the byte of bytearray2 at the given offet are equal 
	 * @Deprecated Renamed to <code>equalsAtPosition(byte[] subArray,byte[] mainArray ,int positionInMainArray)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static boolean compareBytes(byte[] byteArray1,byte[] byteArray2,int offsetByteArray2) {
		for(int i=0;i<byteArray1.length;i++) {
			if(byteArray2[offsetByteArray2+i]!=byteArray1[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if one bytearray is equal another bytearray, or part of another bytearray
	 * at a given offset.
	 * 
	 * @param subArray The first bytearray, size is smaller or same than the other bytearray
	 * @param mainArray Ther second bytearray, size is bigger or same than the other bytearray
	 * @param positionInMainArray Offset to the second bytearray 
	 * @return <code>true</code< if the bytes of bytearray1 and the byte of bytearray2 at the given offet are equal 
	 */
	public static boolean equalsAtPosition(byte[] subArray,byte[] mainArray ,int positionInMainArray) {
		if(positionInMainArray < 0) {
			throw new IllegalArgumentException("offset must not be negative");
		}
		if(positionInMainArray + subArray.length > mainArray.length) {
			return false;
		}
		for(int i=0;i<subArray.length;i++) {
			if(mainArray[positionInMainArray+i]!=subArray[i]) {
				return false;
			}
		}
		return true;
	}

}
