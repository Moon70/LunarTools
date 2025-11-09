package lunartools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FileTools{

	/**
	 * Read file into a bytearay.
	 * 
	 * @param file The file to read
	 * @return Content of file as bytearray
	 * @throws IOException
	 * @Deprecated Renamed to <code>readFileToByteArray(File file)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static byte[] readFileAsByteArray(File file) throws IOException{
		long lenFile=file.length();
		byte[] bytes=new byte[(int)lenFile];
		try(InputStream inputStream=new FileInputStream(file)){
			inputStream.read(bytes);
		}
		return bytes;
	}

	/**
	 * Read file into a bytearay.
	 * 
	 * @param file The file to read
	 * @return Content of file as <code>byte[]</code>
	 * @throws IOException
	 */
	public static byte[] readFileToByteArray(File file) throws IOException{
		long lenFile=file.length();
		byte[] bytes=new byte[(int)lenFile];
		try(InputStream inputStream=new FileInputStream(file)){
			inputStream.read(bytes);
		}
		return bytes;
	}

	/**
	 * Read file into a stringbuffer.
	 * 
	 * @param file The file to read
	 * @param charset Name of the file´s charset
	 * @return Content of file as stringbuffer
	 * @Deprecated Renamed to <code>readFileToStringBuffer(File file,String charset)</code> to follow common Java naming conventions
	 * @throws IOException
	 */
	@Deprecated
	public static StringBuffer readFileAsStringBuffer(File file,String charset) throws IOException{
		try(InputStream inputStream=new FileInputStream(file)){
			return readInputStreamToStringBuffer(inputStream,charset);
		}
	}

	/**
	 * Read file into a StringBuffer.
	 * 
	 * @param file The file to read
	 * @param charset Name of the file´s charset
	 * @return Content of file as <code>StringBuffer</code>
	 * @throws IOException
	 */
	public static StringBuffer readFileToStringBuffer(File file,String charset) throws IOException{
		try(InputStream inputStream=new FileInputStream(file)){
			return readInputStreamToStringBuffer(inputStream,charset);
		}
	}

	/**
	 * Read file into a StringBuilder.
	 * 
	 * @param file The file to read
	 * @param charset Name of the file´s charset
	 * @return Content of file as <code>StringBuilder</code>
	 * @throws IOException
	 */
	public static StringBuilder readFileToStringBuilder(File file,String charset) throws IOException{
		try(InputStream inputStream=new FileInputStream(file)){
			return readInputStreamToStringBuilder(inputStream,charset);
		}
	}

	/**
	 * Reads the inputstream and puts the data into a stringbuffer.
	 * 
	 * @param inputStream The inputstream to read
	 * @param charset Name of the inputstream´s charset
	 * @return StringBuffer Data of inputstream as stringbuffer
	 * @throws IOException
	 * @Deprecated Renamed to <code>readInputStreamToStringBuffer(InputStream inputStream,String charset)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static StringBuffer getStringBufferFromInputStream(InputStream inputStream,String charset) throws IOException{
		final char[] buffer=new char[1024];
		StringBuffer stringBuffer=new StringBuffer();
		try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,charset))) {
			int len;
			while((len=bufferedReader.read(buffer))!=-1){
				stringBuffer.append(buffer,0,len);
			}
		}
		return stringBuffer;
	}

	/**
	 * Reads the inputstream and puts the data into a StringBuffer.
	 * 
	 * @param inputStream The inputstream to read
	 * @param charset Name of the inputstream´s charset
	 * @return StringBuffer Data of inputstream as <code>StringBuffer</code>
	 * @throws IOException
	 */
	public static StringBuffer readInputStreamToStringBuffer(InputStream inputStream,String charset) throws IOException{
		final char[] buffer=new char[1024];
		StringBuffer stringBuffer=new StringBuffer();
		try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,charset))) {
			int len;
			while((len=bufferedReader.read(buffer))!=-1){
				stringBuffer.append(buffer,0,len);
			}
		}
		return stringBuffer;
	}

	/**
	 * Reads the inputstream and puts the data into a StringBuilder.
	 * 
	 * @param inputStream The inputstream to read
	 * @param charset Name of the inputstream´s charset
	 * @return StringBuilder Data of inputstream as <code>StringBuilder</code>
	 * @throws IOException
	 */
	public static StringBuilder readInputStreamToStringBuilder(InputStream inputStream,String charset) throws IOException{
		final char[] buffer=new char[1024];
		StringBuilder stringBuilder=new StringBuilder();
		try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,charset))) {
			int len;
			while((len=bufferedReader.read(buffer))!=-1){
				stringBuilder.append(buffer,0,len);
			}
		}
		return stringBuilder;
	}

	/**
	 * Reads all data from an inputstream and returns it as bytearray.
	 * 
	 * @param inputStream The inputstream to read
	 * @return A bytearray containing all bytes from the inputstream
	 * @throws IOException
	 * @Deprecated Renamed to <code>readInputStreamToByteArray(InputStream inputStream)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static byte[] readBytearrayFromInputStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		final byte[] buffer=new byte[1024];
		int len=0;
		try {
			while((len=inputStream.read(buffer))>0) {
				baos.write(buffer, 0, len);
			}
		} finally {
			inputStream.close();
		}
		return baos.toByteArray();
	}

	/**
	 * Reads all data from an inputstream and returns it as bytearray.
	 * 
	 * @param inputStream The inputstream to read
	 * @return A bytearray containing all bytes from the <code>InputStream</code>
	 * @throws IOException
	 */
	public static byte[] readInputStreamToByteArray(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		final byte[] buffer=new byte[1024];
		int len=0;
		while((len=inputStream.read(buffer))>0) {
			baos.write(buffer, 0, len);
		}
		return baos.toByteArray();
	}

	/**
	 * Write (create/overwrite or append) bytearray to file.
	 * 
	 * @param file The file to create/overwrite or append to
	 * @param bytearray The bytearray to write
	 * @throws IOException
	 * @Deprecated Renamed to <code>writeByteArrayToFile(File file,byte[] bytearray)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static void writeFile(File file,byte[] bytearray) throws IOException{
		writeFile(file,bytearray,false);
	}

	/**
	 * Write (create/overwrite or append) bytearray to file.
	 * 
	 * @param file The file to create/overwrite or append to
	 * @param bytearray The <code>byte[]</code> to write
	 * @throws IOException
	 */
	public static void writeByteArrayToFile(File file,byte[] bytearray) throws IOException{
		writeByteArrayToFile(file,bytearray,false);
	}

	/**
	 * Write (create/overwrite or append) bytearray to file.
	 * 
	 * @param file The file to create/overwrite or append to
	 * @param bytearray The bytearray to write
	 * @param append <code>false</code> to create/overwrite a file, <true> to append to existing file
	 * @throws IOException
	 * @Deprecated Renamed to <code>writeByteArrayToFile(File file,byte[] bytearray,boolean append)</code> to follow common Java naming conventions
	 */
	@Deprecated
	public static void writeFile(File file,byte[] bytearray,boolean append) throws IOException{
		try(FileOutputStream fileOutputStream=new FileOutputStream(file.getAbsolutePath(),append)){
			fileOutputStream.write(bytearray);
			fileOutputStream.flush();
		}
	}

	/**
	 * Write (create/overwrite or append) bytearray to file.
	 * 
	 * @param file The file to create/overwrite or append to
	 * @param bytearray The <code>byte[]</code> to write
	 * @param append <code>false</code> to create/overwrite a file, <true> to append to existing file
	 * @throws IOException
	 */
	public static void writeByteArrayToFile(File file,byte[] bytearray,boolean append) throws IOException{
		try(FileOutputStream fileOutputStream=new FileOutputStream(file.getAbsolutePath(),append)){
			fileOutputStream.write(bytearray);
			fileOutputStream.flush();
		}
	}

	/**
	 * Write (create/overwrite or append) string to file.
	 * 
	 * @param file The file to create/overwrite or append to
	 * @param string The string to write
	 * @param append <code>false</code> to create/overwrite a file, <true> to append to existing file
	 * @param charset Name of the charset for creating the textfile
	 * @Deprecated writeStringToFile(File file,String string,boolean append,String charset)</code> to follow common Java naming conventions
	 * @throws IOException
	 */
	@Deprecated
	public static void writeFile(File file,String string,boolean append,String charset) throws IOException{
		byte[] bytes=string.getBytes(charset);
		writeFile(file,bytes,append);
	}

	/**
	 * Write (create/overwrite or append) string to file.
	 * 
	 * @param file The file to create/overwrite or append to
	 * @param string The string to write
	 * @param append <code>false</code> to create/overwrite a file, <true> to append to existing file
	 * @param charset Name of the charset for creating the textfile
	 * @throws IOException
	 */
	public static void writeStringToFile(File file,String string,boolean append,String charset) throws IOException{
		byte[] bytes=string.getBytes(charset);
		writeByteArrayToFile(file,bytes,append);
	}

	/**
	 * Write (create/overwrite or append) stringbuffer to file.
	 * 
	 * @param file The file to create/overwrite or append to
	 * @param stringBuffer The stringbuffer to write
	 * @param append <code>false</code> to create/overwrite a file, <true> to append to existing file
	 * @param charset Name of the charset for creating the textfile
	 * @Deprecated Renamed to <code>writeStringBufferToFile(File file,StringBuffer stringBuffer,boolean append,String charset)</code> to follow common Java naming conventions
	 * @throws IOException
	 */
	@Deprecated
	public static void writeFile(File file,StringBuffer stringBuffer,boolean append,String charset) throws IOException{
		byte[] bytes=stringBuffer.toString().getBytes(charset);
		writeFile(file,bytes,append);
	}

	/**
	 * Write (create/overwrite or append) StringBuffer to file.
	 * 
	 * @param file The <code>File</code> to create/overwrite or append to
	 * @param stringBuffer The <code>StringBuffer</code> to write
	 * @param append <code>false</code> to create/overwrite a file, <true> to append to existing file
	 * @param charset Name of the charset for creating the textfile
	 * @throws IOException
	 */
	public static void writeStringBufferToFile(File file,StringBuffer stringBuffer,boolean append,String charset) throws IOException{
		byte[] bytes=stringBuffer.toString().getBytes(charset);
		writeByteArrayToFile(file,bytes,append);
	}

	/**
	 * Write (create/overwrite or append) StringBuilder to file.
	 * 
	 * @param file The <code>File</code> to create/overwrite or append to
	 * @param stringBuilder The <code>StringBuilder</code> to write
	 * @param append <code>false</code> to create/overwrite a file, <true> to append to existing file
	 * @param charset Name of the charset for creating the textfile
	 * @throws IOException
	 */
	public static void writeStringBuilderToFile(File file,StringBuilder stringBuilder,boolean append,String charset) throws IOException{
		byte[] bytes=stringBuilder.toString().getBytes(charset);
		writeByteArrayToFile(file,bytes,append);
	}

	/**
	 * Same as <code>File.listFiles()</code> but sorted alphabetically.
	 * 
	 * @param folder The folder which content should be returned as sorted File array.
	 * @return The content of <code>folder</code> as sorted array.
	 */
	public static File[] listFilesSorted(File folder) {
		if (folder == null) {
			throw new IllegalArgumentException("The provided File object is null.");
		}
		if (!folder.isDirectory()) {
			throw new IllegalArgumentException("The provided File object is not a directory: "+folder);
		}
		File[] files=folder.listFiles();
		Arrays.sort(files, (file1, file2) -> file1.getName().compareToIgnoreCase(file2.getName()));
		return files;
	}

}
