package lunartools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

public class FileTools{

	/**
	 * Read file into a bytearay.
	 * 
	 * @param file The file to read
	 * @return Content of file as bytearray
	 * @throws IOException
	 */
	public static byte[] readFileAsByteArray(File file) throws IOException{
		long lenFile=file.length();
		byte[] bytes=new byte[(int)lenFile];
		try(InputStream inputStream=new FileInputStream(file)){
			inputStream.read(bytes);
		}
		return bytes;
	}

	/**
	 * Write (create/overwrite or append) bytearray to file.
	 * 
	 * @param file The file to create/overwrite or append to
	 * @param bytearray The bytearray to write
	 * @throws IOException
	 */
	public static void writeFile(File file,byte[] bytearray) throws IOException{
		writeFile(file,bytearray,false);
	}

	/**
	 * Write (create/overwrite or append) bytearray to file.
	 * 
	 * @param file The file to create/overwrite or append to
	 * @param bytearray The bytearray to write
	 * @param append <code>false</code> to create/overwrite a file, <true> to append to existing file
	 * @throws IOException
	 */
	public static void writeFile(File file,byte[] bytearray,boolean append) throws IOException{
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
	 * @throws IOException
	 */
	public static void writeFile(File file,String string,boolean append,String charset) throws IOException{
		byte[] bytes=string.getBytes(charset);
		writeFile(file,bytes,append);
	}

	/**
	 * Write (create/overwrite or append) string to file.
	 * 
	 * @param file The file to create/overwrite or append to
	 * @param stringBuffer The stringbuffer to write
	 * @param append <code>false</code> to create/overwrite a file, <true> to append to existing file
	 * @param charset Name of the charset for creating the textfile
	 * @throws IOException
	 */
	public static void writeFile(File file,StringBuffer stringBuffer,boolean append,String charset) throws IOException{
		byte[] bytes=stringBuffer.toString().getBytes(charset);
		writeFile(file,bytes,append);
	}

	/**
	 * Reads the inputstream and puts the data into a stringbuffer.
	 * 
	 * @param inputStream The inputstream to read
	 * @param charset Name of the inputstream´s charset
	 * @return StringBuffer Data of inputstream as stringbuffer
	 * @throws IOException
	 */
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
	 * Read file into a stringbuffer.
	 * 
	 * @param file The file to read
	 * @param charset Name of the file´s charset
	 * @return Content of file as stringbuffer
	 * @throws IOException
	 */
	public static StringBuffer readFileAsStringBuffer(File file,String charset) throws IOException{
		try(InputStream inputStream=new FileInputStream(file)){
			return getStringBufferFromInputStream(inputStream,charset);
		}
	}

	/**
	 * Reads all data from an inputstream and returns it as bytearray.
	 * 
	 * @param inputStream The inputstream to read
	 * @return A bytearray containing all bytes from the inputstream
	 * @throws IOException
	 */
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
	 * Same as <code>File.listFiles()</code> but sorted alphabetically.
	 * 
	 * @param folder The folder which content should be returned as sorted File array.
	 * @return The content of <code>folder</code> as sorted array.
	 */
	public static File[] listFilesSorted(File folder) {
		File[] files=folder.listFiles();
		TreeSet<File> treeSet=new TreeSet<File>(new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for(File file:files) {
			treeSet.add(file);
		}
		return treeSet.toArray(files);
	}
	
}
