package lunartools;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class to load and save program properties.
 * <br>For a program called <code>foo</code> which is launched from <code>foo.jar</code>, this class creates a <code>foo.properties</code> beside the jar file.
 * <br>If the program is not launched from a jar file, i.e. it is launched from an IDE, the system variables <code>temp</code> and <code>tmp</code> are searched to find a temp folder.
 * <br>The Java property <code>foo.settings.folder</code> can be used to specify a folder (all lowercase).
 * <br>The program should/could contain a <code>DefaultSettings.properties</code< file.
 * <br>
 * <br>Read/write those standard object from the property file:
 * <li>Rectangle
 * <li>Point
 * 
 * @author Thomas Mattel
 */
public class Settings {
	private static Logger logger = LoggerFactory.getLogger(Settings.class);
	private static final String DEFAULTPROPERTIES_FILENAME="DefaultSettings.properties";
	private String programName;
	private String version;
	private String javaproperty_programSettingsFolder;
	private Properties properties;
	private File fileProperties;
	
	public Settings(String programName,String version){
		this.programName=programName;
		this.version=version;
		this.javaproperty_programSettingsFolder=programName.toLowerCase()+".settings.folder";
		logger.debug("javaproperty settings folder: "+this.javaproperty_programSettingsFolder);
		File folder=determinePropertiesFolder();
		if(folder!=null) {
			this.fileProperties=new File(folder,programName+".properties");
			logger.debug("program settings file: "+this.fileProperties);
		}
		loadSettings();
	}
	
	private File determinePropertiesFolder() {
		File file=null;
		String path=System.getProperty(javaproperty_programSettingsFolder);
		if(path!=null && path.length()>0) {
			file=new File(path);
			if(file.exists()) {
				return file;
			}
		}

		path = Settings.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		try {
			if(path.toLowerCase().endsWith(".jar")) {
				file=new File(URLDecoder.decode(path, "UTF-8")).getParentFile();
				return file;
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("error decoding codesource path: "+path,e);
		}

		String temp=System.getenv("temp");
		if(temp!=null) {
			file=new File(temp);
			return file;
		}

		String tmp=System.getenv("tmp");
		if(tmp!=null) {
			file=new File(tmp);
			return file;
		}
		return null;
	}

	private void loadSettings(){
		properties=new Properties(loadDefaultProperties());
		File file=fileProperties;
		if(file.exists()) {
			try(FileReader fileReader=new FileReader(file)) {
				properties.load(fileReader);
			} catch (IOException e) {
				throw new RuntimeException("error loading settings",e);
			}
		}
	}
	
	private Properties loadDefaultProperties() {
		Properties properties=new Properties();
		try(InputStream inputStream = this.getClass().getResourceAsStream("/"+DEFAULTPROPERTIES_FILENAME)){
			if(inputStream==null) {
				logger.debug("Default properties could not be loaded: "+DEFAULTPROPERTIES_FILENAME);
			}else {
				properties.load(inputStream);
			}
		} catch (IOException e) {
			logger.debug("Default properties could not be loaded: "+DEFAULTPROPERTIES_FILENAME,e);
		}
		return properties;
	}
	
	public void saveSettings() throws IOException {
		if(fileProperties==null) {
			logger.debug("Could not save program settings, file is null");
			return;
		}
		try(FileWriter fileWriter=new FileWriter(fileProperties)) {
			String comments="";
			if(programName!=null) {
				comments=programName+(version==null?"":(" "+version));
			}
			properties.store(fileWriter,comments);
		}
	}
	
	public void setRectangle(String name, Rectangle rectangle) {
		properties.setProperty(name,""+rectangle.x+","+rectangle.y+","+rectangle.width+","+rectangle.height);
	}
	
	public Rectangle getRectangle(String name, Rectangle defaultRectangle) {
		String s=properties.getProperty(name);
		if(s!=null) {
			String[] sa=s.split(",");
			if(sa.length==4) {
				return new Rectangle(Integer.parseInt(sa[0]),Integer.parseInt(sa[1]),Integer.parseInt(sa[2]),Integer.parseInt(sa[3]));
			}
		}
		setRectangle(name,defaultRectangle);
		return defaultRectangle;
	}
	
	public void setPoint(String name, Point point) {
		properties.setProperty(name,""+point.x+","+point.y);
	}
	
	public Point getPoint(String name, Point defaultPoint) {
		String s=properties.getProperty(name);
		if(s!=null) {
			String[] sa=s.split(",");
			if(sa.length==2) {
				return new Point(Integer.parseInt(sa[0]),Integer.parseInt(sa[1]));
			}
		}
		setPoint(name,defaultPoint);
		return defaultPoint;
	}
	
	public void set(String name, String string) {
		properties.setProperty(name,string);
	}
	
	public String getString(String name) {
		return properties.getProperty(name);
	}

	
}
