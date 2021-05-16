package lunartools;

public class ByteTools {

	/**
	 * Converts a longword, given as int value, to a big-endian bytearray.
	 * 
	 * @param longword
	 * @return big endian bytearray
	 */
	public static byte[] bLongwordToBytearray(int longword) {
		int byte0=(longword>>24)&0xff;
		int byte1=(longword>>16)&0xff;
		int byte2=(longword>>8)&0xff;
		int byte3=longword&0xff;
		return new byte[] {(byte)byte0,(byte)byte1,(byte)byte2,(byte)byte3};
	}
	
	/**
	 * Converts a longword, given as long value, to a big-endian bytearray.
	 * 
	 * @param longword
	 * @return big endian bytearray
	 */
	public static byte[] bLongwordToBytearray(long longword) {
		long byte0=(longword>>24)&0xff;
		long byte1=(longword>>16)&0xff;
		long byte2=(longword>>8)&0xff;
		long byte3=longword&0xff;
		return new byte[] {(byte)byte0,(byte)byte1,(byte)byte2,(byte)byte3};
	}
	
	/**
	 * Converts a word, given as int value, to a big-endian bytearray.
	 * 
	 * @param word
	 * @return big endian bytearray
	 */
	public static byte[] bWordToBytearray(int word) {
		int byte0=(word>>8)&0xff;
		int byte1=word&0xff;
		return new byte[] {(byte)byte0,(byte)byte1};
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
	public static long bBytearrayToLongword(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		int byte2=bytearray[offset+2]&0xff;
		int byte3=bytearray[offset+3]&0xff;
		return ((long)byte0<<24)|(byte1<<16)|(byte2<<8)|byte3;
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
	public static int bBytearrayToWord(byte[] bytearray, int offset) {
		int byte0=bytearray[offset]&0xff;
		int byte1=bytearray[offset+1]&0xff;
		return (byte0<<8)|byte1;
	}

	/**
	 * Writes a longword to a bytearray at the given offset, overwriting the bytes.
	 * <br>The byte order is big endian.
	 * 
	 * @param bytes The bytearray to write the longword
	 * @param offset offset to the bytearray position to write the longword
	 * @param longword The longword
	 */
	public static void bWriteLongwordToBytearray(byte[] bytes,int offset,long longword) {
		byte[] ba=bLongwordToBytearray(longword);
		for(int i=0;i<4;i++) {
			bytes[offset+i]=ba[i];
		}
	}

	/**
	 * Reads a byte from a bytearray and returns it as int.
	 * 
	 * @param bytearray The bytearray
	 * @param offset The offset to the byte
	 * @return The byte as int
	 */
	public static int bytearrayToByte(byte[] bytearray, int offset) {
		return bytearray[offset]&0xff;
	}

	/**
	 * Converts a hex string to a bytearray.
	 * <br>The byte order is big endian.
	 * 
	 * @param hexstring
	 * @return
	 */
	public static byte[] bHexStringToByteArray(String hexstring) {
		byte[] ba=new byte[hexstring.length()>>1];
		for(int i=0;i<hexstring.length();i+=2) {
			int bytevalue=Integer.parseInt(hexstring.substring(i, i+2), 16);
			ba[i>>1]=(byte)bytevalue;
		}
		return ba;
	}
	
	/**
	 * Checks if one bytearray is equal another bytearray, or part of another bytearray
	 * at a given offset.
	 * 
	 * @param bytearray1 The first bytearray, size is smaller or same than the other bytearray
	 * @param bytearray2 Ther second bytearray, size is bigger or same than the other bytearray
	 * @param offsetBytearray2 Offset to the second bytearray 
	 * @return <code>true</code< if the bytes of bytearray1 and the byte of bytearray2 at the given offet are equal 
	 */
	public static boolean compareBytes(byte[] bytearray1,byte[] bytearray2,int offsetBytearray2) {
		for(int i=0;i<bytearray1.length;i++) {
			if(bytearray2[offsetBytearray2+i]!=bytearray1[i]) {
				return false;
			}
		}
		return true;
	}

}
