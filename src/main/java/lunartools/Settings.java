
package lunartools;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
 * <br>Read/write those types from property file:
 * <li>String
 * <li>int
 * <li>long
 * <li>float
 * <li>double
 * <li>Rectangle
 * <li>Point
 * <li>Dimension
 * @deprecated Subclass AbstractSettings
 */
@Deprecated
public class Settings {
	private static Logger logger = LoggerFactory.getLogger(Settings.class);
	private static final String DEFAULTPROPERTIES_FILENAME="DefaultSettings.properties";
	private String programName;
	private String version;
	private String javaproperty_programSettingsFolder;
	private Properties properties;
	private Properties propertiesDefault;
	private File fileProperties;

	public Settings(String programName,String version){
		this.programName=programName;
		this.version=version;
		this.javaproperty_programSettingsFolder=programName.toLowerCase().replaceAll(" ","_")+".settings.folder";
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
		propertiesDefault=new Properties();
		try(InputStream inputStream = this.getClass().getResourceAsStream("/"+DEFAULTPROPERTIES_FILENAME)){
			if(inputStream==null) {
				logger.debug("Default properties could not be loaded: "+DEFAULTPROPERTIES_FILENAME);
			}else {
				propertiesDefault.load(inputStream);
			}
		} catch (IOException e) {
			logger.debug("Default properties could not be loaded: "+DEFAULTPROPERTIES_FILENAME,e);
		}
		return propertiesDefault;
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

	/**
	 * Returns <code>true</code> if the given name is a property key of the property file.
	 * <br>It returns <code>false</code> if this key does not exist in the property file, <u>even if it does exist in the
	 * default property file.</u>
	 * 
	 * @param name Property name
	 * @return <code>true</code> only if <code>name</code> is a key of the property file.
	 */
	public boolean containsKey(String name) {
		return properties.containsKey(name);
	}

	/**
	 * Removes a property.
	 * 
	 * @param key The key of the property to be removed
	 */
	public void remove(String key) {
		properties.remove(key);
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its String value.
	 * <br>If no property is found, <code>null</code> is returned.
	 * 
	 * @param name Property name
	 * @return property value
	 */
	public String getString(String name) {
		return properties.getProperty(name);
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its String value.
	 * <br>If no property is found, a RuntimeException is thrown.
	 * 
	 * @param name Property name
	 * @throws RuntimeException Property not found
	 * @return property value
	 */
	public String getStringNotNull(String name) {
		String s=properties.getProperty(name);
		if(s==null) {
			throw new RuntimeException("Property not found: "+name);
		}
		return s;
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its String value.
	 * <br>If no property is found, the given default String is returned.
	 * 
	 * @param name Property name
	 * @param defaultString Default property value
	 * @return property value
	 */
	public String getString(String name, String defaultString) {
		return properties.getProperty(name,defaultString);
	}

	/**
	 * Stores the given property.
	 * 
	 * @param name Property name
	 * @param string property value
	 */
	public void setString(String name, String string) {
		properties.setProperty(name,string);
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its
	 * <code>int</code> value by calling <code>Integer.parseInt</code>.
	 * <br>If no property is found, a RuntimeException is thrown.
	 * 
	 * @param name Property name
	 * @throws RuntimeException Property not found
	 * @return property value as <code>int</code>
	 */
	public int getInt(String name) {
		String s=properties.getProperty(name);
		if(s==null) {
			throw new RuntimeException("Property not found: "+name);
		}
		return Integer.parseInt(s);
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its
	 * <code>int</code> value by calling <code>Integer.parseInt</code>.
	 * <br>If no property is found, the given default value is returned.
	 * 
	 * @param name Property name
	 * @param defaultInt Default property value
	 * @return property value
	 */
	public int getInt(String name, int defaultInt) {
		String s=properties.getProperty(name);
		if(s!=null) {
			return Integer.parseInt(s);
		}
		return defaultInt;
	}

	/**
	 * Stores the given property.
	 * 
	 * @param name Property name
	 * @param string property value
	 */
	public void setInt(String name, int value) {
		properties.setProperty(name,""+value);
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its
	 * <code>long</code> value by calling <code>Long.parseInt</code>.
	 * <br>If no property is found, a RuntimeException is thrown.
	 * 
	 * @param name Property name
	 * @throws RuntimeException Property not found
	 * @return property value as <code>int</code>
	 */
	public long getLong(String name) {
		String s=properties.getProperty(name);
		if(s==null) {
			throw new RuntimeException("Property not found: "+name);
		}
		return Long.parseLong(s);
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its
	 * <code>long</code> value by calling <code>Long.parseLong</code>.
	 * <br>If no property is found, the given default value is returned.
	 * 
	 * @param name Property name
	 * @param defaultLong Default property value
	 * @return property value
	 */
	public long getLong(String name, long defaultLong) {
		String s=properties.getProperty(name);
		if(s!=null) {
			return Long.parseLong(s);
		}
		return defaultLong;
	}

	/**
	 * Stores the given property.
	 * 
	 * @param name Property name
	 * @param string property value
	 */
	public void setLong(String name, long value) {
		properties.setProperty(name,""+value);
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its
	 * <code>float</code> value by calling <code>Float.parseFloat</code>.
	 * <br>If no property is found, a RuntimeException is thrown.
	 * 
	 * @param name Property name
	 * @throws RuntimeException Property not found
	 * @return property value as <code>int</code>
	 */
	public float getFloat(String name) {
		String s=properties.getProperty(name);
		if(s==null) {
			throw new RuntimeException("Property not found: "+name);
		}
		return Float.parseFloat(s);
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its
	 * <code>float</code> value by calling <code>Float.parseFloat</code>.
	 * <br>If no property is found, the given default value is returned.
	 * 
	 * @param name Property name
	 * @param defaultFloat Default property value
	 * @return property value
	 */
	public float getFloat(String name, float defaultFloat) {
		String s=properties.getProperty(name);
		if(s!=null) {
			return Float.parseFloat(s);
		}
		return defaultFloat;
	}

	/**
	 * Stores the given property.
	 * 
	 * @param name Property name
	 * @param string property value
	 */
	public void setFloat(String name, float value) {
		properties.setProperty(name,""+value);
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its
	 * <code>double</code> value by calling <code>Double.parseDouble</code>.
	 * <br>If no property is found, a RuntimeException is thrown.
	 * 
	 * @param name Property name
	 * @throws RuntimeException Property not found
	 * @return property value as <code>int</code>
	 */
	public double getDouble(String name) {
		String s=properties.getProperty(name);
		if(s==null) {
			throw new RuntimeException("Property not found: "+name);
		}
		return Double.parseDouble(s);
	}

	/**
	 * This method searches both the property file and default property file for the given key and returns its
	 * <code>double</code> value by calling <code>Double.parseDouble</code>.
	 * <br>If no property is found, the given default value is returned.
	 * 
	 * @param name Property name
	 * @param defaultDouble Default property value
	 * @return property value
	 */
	public double getDouble(String name, double defaultDouble) {
		String s=properties.getProperty(name);
		if(s!=null) {
			return Double.parseDouble(s);
		}
		return defaultDouble;
	}

	/**
	 * Stores the given property.
	 * 
	 * @param name Property name
	 * @param string property value
	 */
	public void setDouble(String name, double value) {
		properties.setProperty(name,""+value);
	}

	/**
	 * Stores the given Rectangle object by creating a comma separated property containing the rectangles x,y,width and height value.
	 * 
	 * @param name Property name
	 * @param string property value
	 */
	public void setRectangle(String name, Rectangle rectangle) {
		properties.setProperty(name,""+rectangle.x+","+rectangle.y+","+rectangle.width+","+rectangle.height);
	}

	/**
	 * This method searches both the property file and default property file for the given key and uses its value
	 * to create a <code>Rectangle</code> object.
	 * <br>If no property is found, a RuntimeException is thrown.
	 * <br>If the property value does not contain 4 values to create a Rectangle object, an IllegalArgumentException is thrown.
	 * 
	 * @param name Property name
	 * @throws RuntimeException Property not found
	 * @throws IllegalArgumentException Not a Rectangle
	 * @return property value as <code>int</code>
	 */
	public Rectangle getRectangle(String name) {
		String s=properties.getProperty(name);
		if(s==null) {
			throw new RuntimeException("Property not found: "+name);
		}
		return createRectangle(name,s);
	}

	/**
	 * This method searches both the property file and default property file for the given key and uses its value
	 * to create a <code>Rectangle</code> object.
	 * <br>If no property is found, the given default value is returned.
	 * <br>If the property value does not contain 4 values to create a Rectangle object, an IllegalArgumentException is thrown.
	 * 
	 * @param name Property name
	 * @param defaultRectangle Default property value
	 * @throws IllegalArgumentException Not a Rectangle
	 * @return property value as <code>int</code>
	 */
	public Rectangle getRectangle(String name, Rectangle defaultRectangle) {
		String s=properties.getProperty(name);
		if(s!=null) {
			return createRectangle(name,s);
		}
		return defaultRectangle;
	}

	private Rectangle createRectangle(String propertyName, String rectangleAsCsv) {
		String[] sa=rectangleAsCsv.split(",");
		if(sa.length==4) {
			return new Rectangle(Integer.parseInt(sa[0]),Integer.parseInt(sa[1]),Integer.parseInt(sa[2]),Integer.parseInt(sa[3]));
		}else {
			throw new IllegalArgumentException("Not a Rectangle: "+propertyName);
		}
	}

	/**
	 * Stores the given Point object by creating a comma separated property containing the points x and y value.
	 * 
	 * @param name Property name
	 * @param string property value
	 */
	public void setPoint(String name, Point point) {
		properties.setProperty(name,""+point.x+","+point.y);
	}

	/**
	 * This method searches both the property file and default property file for the given key and uses its value
	 * to create a <code>Point</code> object.
	 * <br>If no property is found, a RuntimeException is thrown.
	 * <br>If the property value does not contain 2 values to create a Point object, an IllegalArgumentException is thrown.
	 * 
	 * @param name Property name
	 * @throws RuntimeException Property not found
	 * @throws IllegalArgumentException Not a Point
	 * @return property value as <code>Rectangle</code>
	 */
	public Point getPoint(String name) {
		String s=properties.getProperty(name);
		if(s==null) {
			throw new RuntimeException("Property not found: "+name);
		}
		return createPoint(name,s);
	}

	/**
	 * This method searches both the property file and default property file for the given key and uses its value
	 * to create a <code>Point</code> object.
	 * <br>If no property is found, the given default value is returned.
	 * <br>If the property value does not contain 2 values to create a Point object, an IllegalArgumentException is thrown.
	 * 
	 * @param name Property name
	 * @param defaultPoint Default property value
	 * @throws IllegalArgumentException Not a Point
	 * @return property value as <code>Point</code>
	 */
	public Point getPoint(String name, Point defaultPoint) {
		String s=properties.getProperty(name);
		if(s!=null) {
			return createPoint(name,s);
		}
		return defaultPoint;
	}

	private Point createPoint(String propertyName, String pointAsCsv) {
		String[] sa=pointAsCsv.split(",");
		if(sa.length==2) {
			return new Point(Integer.parseInt(sa[0]),Integer.parseInt(sa[1]));
		}else {
			throw new IllegalArgumentException("Not a Point: "+propertyName);
		}
	}

	/**
	 * Stores the given Dimension object by creating a comma separated property containing the points width and height value.
	 * 
	 * @param name Property name
	 * @param string property value
	 */
	public void setDimension(String name, Dimension dimension) {
		properties.setProperty(name,""+dimension.width+","+dimension.height);
	}

	/**
	 * This method searches both the property file and default property file for the given key and uses its value
	 * to create a <code>Dimension</code> object.
	 * <br>If no property is found, a RuntimeException is thrown.
	 * <br>If the property value does not contain 2 values to create a Dimension object, an IllegalArgumentException is thrown.
	 * 
	 * @param name Property name
	 * @throws RuntimeException Property not found
	 * @throws IllegalArgumentException Not a Dimension
	 * @return property value as <code>Dimension</code>
	 */
	public Dimension getDimension(String name) {
		String s=properties.getProperty(name);
		if(s==null) {
			throw new RuntimeException("Property not found: "+name);
		}
		return createDimension(name,s);
	}

	/**
	 * This method searches both the property file and default property file for the given key and uses its value
	 * to create a <code>Dimension</code> object.
	 * <br>If no property is found, the given default value is returned.
	 * <br>If the property value does not contain 2 values to create a Dimension object, an IllegalArgumentException is thrown.
	 * 
	 * @param name Property name
	 * @param defaultDimension Default property value
	 * @throws IllegalArgumentException Not a Dimension
	 * @return property value as <code>Dimension</code>
	 */
	public Dimension getDimension(String name, Dimension defaultDimension) {
		String s=properties.getProperty(name);
		if(s!=null) {
			return createDimension(name,s);
		}
		return defaultDimension;
	}

	private Dimension createDimension(String propertyName, String dimensionAsCsv) {
		String[] sa=dimensionAsCsv.split(",");
		if(sa.length==2) {
			return new Dimension(Integer.parseInt(sa[0]),Integer.parseInt(sa[1]));
		}else {
			throw new IllegalArgumentException("Not a Dimension: "+propertyName);
		}
	}

	/**
	 * This method searches both the property file and default property file for a string list.
	 * <br>Example:
	 * <br>The property file contains those values:
	 * <code>
	 * <br>foo1=a
	 * <br>foo2=b
	 * <br>foo3=c
	 * </code>
	 * <br>Calling <code>getStringlist("foo")</code> returns an ArrayList containing the values <code>[a,b,c]</code>
	 * <br>If no property is found, an empty ArrayList is returned.
	 * 
	 * @param name Property name
	 * @param stringlistDefault Default ArrayList
	 * @return property value(s) as <code>ArrayList<String></code>
	 */
	public ArrayList<String> getStringlist(String name) {
		return getStringlist(name,new ArrayList<String>());
	}

	/**
	 * This method searches both the property file and default property file for a string list.
	 * <br>Example:
	 * <br>The property file contains those values:
	 * <code>
	 * <br>foo1=a
	 * <br>foo2=b
	 * <br>foo3=c
	 * </code>
	 * <br>Calling <code>getStringlist("foo")</code> returns an ArrayList containing the values <code>[a,b,c]</code>
	 * <br>If no property is found, the given default ArrayList is returned.
	 * 
	 * @param name Property name
	 * @param stringlistDefault Default ArrayList
	 * @return property value(s) as <code>ArrayList<String></code>
	 */
	public ArrayList<String> getStringlist(String name, ArrayList<String> stringlistDefault) {
		ArrayList<String> stringlist=new ArrayList<String>();
		int index=1;
		Properties properties;
		if(this.properties.containsKey(name+index)) {
			properties=this.properties;
		}else if(this.propertiesDefault.containsKey(name+index)) {
			properties=this.propertiesDefault;
		}else {
			return stringlistDefault;
		}

		while(properties.containsKey(name+index)) {
			stringlist.add(properties.getProperty(name+index++));
		}
		return stringlist;
	}

	/**
	 * Stores the given ArrayList.
	 * <br>Example:
	 * <br>An ArrayList containing the String values [a,b,c] is stored as property <code>foo</code> by creating
	 * those property file entries:
	 * <code>
	 * <br>foo1=a
	 * <br>foo2=b
	 * <br>foo3=c
	 * </code>
	 * 
	 * @param name Property name
	 * @param stringlist an ArrayList<String>
	 * 
	 */
	public void setStringlist(String name, ArrayList<String> stringlist) {
		int index=0;
		while(properties.containsKey(name+(++index))) {
			properties.remove(name+index);
		}
		for(int i=0;i<stringlist.size();i++) {
			properties.setProperty(name+(i+1), stringlist.get(i));
		}
	}

	/**
	 * Returns the property file´s folder.<br>
	 * 
	 * @return The folder containing the application properties
	 */
	public File getPropertiesFolder() {
		if(fileProperties==null) {
			return null;
		}
		return fileProperties.getParentFile();
	}
	
}
