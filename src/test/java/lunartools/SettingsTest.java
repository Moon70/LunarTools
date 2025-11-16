package lunartools;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SettingsTest {
	private AbstractSettings settings;
	private final String programName="UNIT_test_settings";
	private final String programVersion="0.0.1";

	@BeforeEach
	void beforeEach() {
		String javaproperty_programSettingsFolder=programName.toLowerCase()+".settings.folder";
		URL url=SettingsTest.class.getResource("/"+programName+".properties");
		File file=new File(url.getFile());
		System.setProperty(javaproperty_programSettingsFolder, file.getParent());
		settings=new AbstractSettings(programName,programVersion) {};
	}

	@Test
	void whenPropertyExistsThenTrueIsReturnesContainsKeyReturnsTrue() {
		assertTrue(settings.containsKey("string"));
	}

	@Test
	void whenPropertyDoesNotExistInPropertiesButInDefaultPropertiesThenContainsKeyReturnsFalse() {
		assertFalse(settings.containsKey("stringDefault"));
	}

	@Test
	void whenPropertyNeitherExistsInPropertiesNorDefaultPeopertiesThenContainsKeyReturnsFalse() {
		assertFalse(settings.containsKey("stringDoesNotExist"));
	}

	@Test
	void removingAPropertyWorksCorrectly() {
		final String propertyName="string";
		assertTrue(settings.containsKey(propertyName));
		settings.remove(propertyName);
		assertFalse(settings.containsKey(propertyName));
	}
	
	@Test
	void whenStringExistsInPropertiesThenStringIsReturned() {
		final String stringExpected="stringvalue";
		final String stringDefault="stringDefaultParameter";
		String string=settings.getString("string", stringDefault);
		assertEquals(stringExpected,string);
	}

	@Test
	void whenStringDoesNotExistInPropertiesButInDefaultPropertiesThenDefaultStringIsReturned() {
		final String stringExpected="stringValueFromDefaultProperties";
		final String stringDefault="stringDefaultParameter";
		String string=settings.getString("stringDefault",stringDefault);
		assertEquals(stringExpected,string);
	}

	@Test
	void whenStringNeitherExistsInPropertiesNorDefaultPropertiesThenNullIsReturned() {
		final String propertyName="stringDoesNotExist";
		assertNull(settings.getString(propertyName));
	}

	@Test
	void whenStringNeitherExistsInPropertiesNorDefaultPropertiesThenExceptionIsThrown() {
		final String propertyName="stringDoesNotExist";
		final String expectedMessage="Property not found: "+propertyName;
		Exception exception=assertThrows(RuntimeException.class, () -> settings.getStringNotNull(propertyName));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void whenStringNeitherExistsInPropertiesNorDefaultPropertiesThenGivenDefaultStringIsReturned() {
		final String stringExpected="stringDefaultParameter";
		final String stringDefault="stringDefaultParameter";
		String string=settings.getString("stringDoesNotExist", stringDefault);
		assertEquals(stringExpected,string);
	}

	@Test
	void stringIsSetCorrectly() {
		final String newStringKey="newString";
		final String newStringValue="newStringValue";
		final String stringDefault="stringDefault";
		final String stringExpected="newStringValue";
		assertFalse(settings.containsKey(newStringKey));
		settings.setString(newStringKey, newStringValue);

		String string=settings.getString(newStringKey,stringDefault);
		assertEquals(stringExpected,string);
	}

	@Test
	void whenIntExistsInPropertiesThenIntIsReturned() {
		final int intExpected=1234;
		final int intDefault=5678;
		int i=settings.getInt("int",intDefault);
		assertEquals(intExpected,i);
	}

	@Test
	void whenIntDoesNotExistInPropertiesButInDefaultPropertiesThenDefaultIntIsReturned() {
		final int intExpected=13579;
		final int intDefault=5678;
		int i=settings.getInt("intDefault",intDefault);
		assertEquals(intExpected,i);
	}

	@Test
	void whenIntNeitherExistsInPropertiesNorDefaultPropertiesThenExceptionIsThrown() {
		final String propertyName="intDoesNotExist";
		final String expectedMessage="Property not found: "+propertyName;
		Exception exception=assertThrows(RuntimeException.class, () -> settings.getInt(propertyName));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void whenIntNeitherExistsInPropertiesNorDefaultPropertiesThenGivenDefaultIntIsReturned() {
		final int intExpected=5678;
		final int intDefault=5678;
		int i=settings.getInt("intDoesNotExist", intDefault);
		assertEquals(intExpected,i);
	}

	@Test
	void intIsSetCorrectly() {
		final String newIntKey="newInt";
		final int newInt=2468;
		final int intExpected=2468;
		final int intDefault=5678;
		assertFalse(settings.containsKey(newIntKey));
		settings.setInt(newIntKey, newInt);

		int i=settings.getInt(newIntKey, intDefault);
		assertEquals(intExpected,i);
	}

	@Test
	void whenLongExistsInPropertiesThenLongIsReturned() {
		final long longExpected=12345678900l;
		final long longDefault=9876543210l;
		long x=settings.getLong("long", longDefault);
		assertEquals(longExpected,x);
	}

	@Test
	void whenLongDoesNotExistInPropertiesButInDefaultPropertiesThenDefaultLongIsReturned() {
		final long longExpected=133557790900l;
		final long longDefault=9876543210l;
		long x=settings.getLong("longDefault",longDefault);
		assertEquals(longExpected,x);
	}

	@Test
	void whenLongNeitherExistsInPropertiesNorDefaultPropertiesThenExceptionIsThrown() {
		final String propertyName="longDoesNotExist";
		final String expectedMessage="Property not found: "+propertyName;
		Exception exception=assertThrows(RuntimeException.class, () -> settings.getLong(propertyName));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void whenLongNeitherExistsInPropertiesNorDefaultPropertiesThenGivenDefaultLongIsReturned() {
		final long longExpected=9876543210l;
		final long longDefault=9876543210l;
		long x=settings.getLong("longDoesNotExist", longDefault);
		assertEquals(longExpected,x);
	}

	@Test
	void longIsSetCorrectly() {
		final String newLongKey="newLong";
		final long newLong=12345678900l;
		final long longExpected=12345678900l;
		final long longDefault=9876543210l;
		assertFalse(settings.containsKey(newLongKey));
		settings.setLong(newLongKey, newLong);

		long x=settings.getLong(newLongKey, longDefault);
		assertEquals(longExpected,x);
	}

	@Test
	void whenFloatExistsInPropertiesThenFloatIsReturned() {
		final float floatExpected=3.141592654f;
		final float floatDefault=1.23456789f;
		float x=settings.getFloat("float", floatDefault);
		assertEquals(floatExpected,x);
	}

	@Test
	void whenFloatDoesNotExistInPropertiesButInDefaultPropertiesThenDefaultFloatIsReturned() {
		final float floatExpected=1.234567890f;
		final float floatDefault=1.23456789f;
		float x=settings.getFloat("floatDefault",floatDefault);
		assertEquals(floatExpected,x);
	}

	@Test
	void whenFloatNeitherExistsInPropertiesNorDefaultPropertiesThenExceptionIsThrown() {
		final String propertyName="floatDoesNotExist";
		final String expectedMessage="Property not found: "+propertyName;
		Exception exception=assertThrows(RuntimeException.class, () -> settings.getFloat(propertyName));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void whenFloatNeitherExistsInPropertiesNorDefaultPropertiesThenGivenDefaultFloatIsReturned() {
		final float floatExpected=1.23456789f;
		final float floatDefault=1.23456789f;
		float x=settings.getFloat("floatDoesNotExist", floatDefault);
		assertEquals(floatExpected,x);
	}

	@Test
	void floatIsSetCorrectly() {
		final String newFloatKey="newFloat";
		final float newFloat=9.87654321f;
		final float floatExpected=9.87654321f;
		final float floatDefault=1.23456789f;
		assertFalse(settings.containsKey(newFloatKey));
		settings.setFloat(newFloatKey, newFloat);

		float x=settings.getFloat(newFloatKey, floatDefault);
		assertEquals(floatExpected,x);
	}

	@Test
	void whenDoubleExistsInPropertiesThenDoubleIsReturned() {
		final double doubleExpected=3.141592653589793d;
		final double doubleDefault=1.234567891234567d;
		double x=settings.getDouble("double", doubleDefault);
		assertEquals(doubleExpected,x);
	}

	@Test
	void whenDoubleDoesNotExistInPropertiesButInDefaultPropertiesThenDefaultDoubleIsReturned() {
		final double doubleExpected=1.987654321987654d;
		final double doubleDefault=1.234567891234567d;
		double x=settings.getDouble("doubleDefault",doubleDefault);
		assertEquals(doubleExpected,x);
	}

	@Test
	void whenDoubleNeitherExistsInPropertiesNorDefaultPropertiesThenExceptionIsThrown() {
		final String propertyName="doubleDoesNotExist";
		final String expectedMessage="Property not found: "+propertyName;
		Exception exception=assertThrows(RuntimeException.class, () -> settings.getDouble(propertyName));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void whenDoubleNeitherExistsInPropertiesNorDefaultPropertiesThenGivenDefaultDoubleIsReturned() {
		final double doubleExpected=1.234567891234567d;
		final double doubleDefault=1.234567891234567d;
		double x=settings.getDouble("doubleDoesNotExist", doubleDefault);
		assertEquals(doubleExpected,x);
	}

	@Test
	void doubleIsSetCorrectly() {
		final String newDoubleKey="newDouble";
		final double newDouble=3.141592653589793d;
		final double doubleExpected=3.141592653589793d;
		final double doubleDefault=1.234567891234567d;
		assertFalse(settings.containsKey(newDoubleKey));
		settings.setDouble(newDoubleKey, newDouble);

		double x=settings.getDouble(newDoubleKey, doubleDefault);
		assertEquals(doubleExpected,x);
	}

	@Test
	void whenRectangleExistsInPropertiesThenRectangleIsReturned() {
		final Rectangle rectangleExpected=new Rectangle(1,2,3,4);
		final Rectangle rectangleDefault=new Rectangle(4,3,2,1);
		Rectangle rectangle=settings.getRectangle("rectangle", rectangleDefault);
		assertEquals(rectangleExpected,rectangle);
	}

	@Test
	void whenRectangleDoesNotExistInPropertiesButInDefaultPropertiesThenDefaultRectangleIsReturned() {
		final Rectangle rectangleExpected=new Rectangle(4,3,2,1);
		final Rectangle rectangleDefault=new Rectangle(4,3,2,1);
		Rectangle rectangle=settings.getRectangle("rectangleDefault",rectangleDefault);
		assertEquals(rectangleExpected,rectangle);
	}

	@Test
	void whenRectangleNeitherExistsInPropertiesNorDefaultPropertiesThenExceptionIsThrown() {
		final String propertyName="rectangleDoesNotExist";
		final String expectedMessage="Property not found: "+propertyName;
		Exception exception=assertThrows(RuntimeException.class, () -> settings.getRectangle(propertyName));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void whenRectangleNeitherExistsInPropertiesNorDefaultPropertiesThenGivenDefaultRectangleIsReturned() {
		final Rectangle rectangleExpected=new Rectangle(4,3,2,1);
		final Rectangle rectangleDefault=new Rectangle(4,3,2,1);
		Rectangle rectangle=settings.getRectangle("rectangleDoesNotExist", rectangleDefault);
		assertEquals(rectangleExpected,rectangle);
	}

	@Test
	void whenReadingBadRectangleThenExceptionIsThrown() {
		final String propertyName="corruptedrectangle";
		final String expectedMessage="Not a Rectangle: "+propertyName;
		final Rectangle rectangleDefault=new Rectangle(4,3,2,1);
		Exception exception=assertThrows(IllegalArgumentException.class, () -> settings.getRectangle(propertyName, rectangleDefault));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void rectangleIsSetCorrectly() {
		final String newRectangleKey="newRectangle";
		final Rectangle newRectangle=new Rectangle(5,6,7,8);
		final Rectangle rectangleExpected=new Rectangle(5,6,7,8);
		final Rectangle rectangleDefault=new Rectangle(4,3,2,1);
		assertFalse(settings.containsKey(newRectangleKey));
		settings.setRectangle(newRectangleKey, newRectangle);

		Rectangle rectangle=settings.getRectangle(newRectangleKey,rectangleDefault);
		assertEquals(rectangleExpected,rectangle);
	}

	@Test
	void whenPointExistsInPropertiesThenPointIsReturned() {
		final Point pointExpected=new Point(1,2);
		final Point pointDefault=new Point(2,1);
		Point point=settings.getPoint("point", pointDefault);
		assertEquals(pointExpected,point);
	}

	@Test
	void whenPointDoesNotExistInPropertiesButInDefaultPropertiesThenDefaultRectangleIsReturned() {
		final Point pointExpected=new Point(3,4);
		final Point pointDefault=new Point(5,6);
		Point point=settings.getPoint("pointDefault", pointDefault);
		assertEquals(pointExpected,point);
	}

	@Test
	void whenPointNeitherExistsInPropertiesNorDefaultPropertiesThenExceptionIsThrown() {
		final String propertyName="pointDoesNotExist";
		final String expectedMessage="Property not found: "+propertyName;
		Exception exception=assertThrows(RuntimeException.class, () -> settings.getPoint(propertyName));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void whenPointNeitherExistsInPropertiesNorDefaultPropertiesThenGivenDefaultPointIsReturned() {
		final Point pointExpected=new Point(2,1);
		final Point pointDefault=new Point(2,1);
		Point point=settings.getPoint("pointDoesNotExist", pointDefault);
		assertEquals(pointExpected,point);
	}

	@Test
	void whenReadingBadPointThenExceptionIsThrown() {
		final String propertyName="corruptedpoint";
		final String expectedMessage="Not a Point: "+propertyName;
		final Point pointDefault=new Point(2,1);
		Exception exception=assertThrows(IllegalArgumentException.class, () -> settings.getPoint(propertyName, pointDefault));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void pointIsSetCorrectly() {
		final String newPointKey="newPoint";
		final Point newPoint=new Point(5,6);
		final Point pointExpected=new Point(5,6);
		final Point pointDefault=new Point(2,1);
		assertFalse(settings.containsKey(newPointKey));
		settings.setPoint(newPointKey, newPoint);

		Point point=settings.getPoint(newPointKey,pointDefault);
		assertEquals(pointExpected,point);
	}

	@Test
	void whenDimensionExistsInPropertiesThenDimensionIsReturned() {
		final Dimension dimensionExpected=new Dimension(800,600);
		final Dimension dimensionDefault=new Dimension(1024,768);
		Dimension dimension=settings.getDimension("dimension", dimensionDefault);
		assertEquals(dimensionExpected,dimension);
	}

	@Test
	void whenDimensionDoesNotExistInPropertiesButInDefaultPropertiesThenDefaultDimensionIsReturned() {
		final Dimension dimensionExpected=new Dimension(900,700);
		final Dimension dimensionDefault=new Dimension(1024,768);
		Dimension dimension=settings.getDimension("dimensionDefault", dimensionDefault);
		assertEquals(dimensionExpected,dimension);
	}

	@Test
	void whenDimensionNeitherExistsInPropertiesNorDefaultPropertiesThenExceptionIsThrown() {
		final String propertyName="dimensionDoesNotExist";
		final String expectedMessage="Property not found: "+propertyName;
		Exception exception=assertThrows(RuntimeException.class, () -> settings.getDimension(propertyName));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void whenDimensionNeitherExistsInPropertiesNorDefaultPropertiesThenGivenDefaultDimensionIsReturned() {
		final Dimension dimensionExpected=new Dimension(1024,768);
		final Dimension dimensionDefault=new Dimension(1024,768);
		Dimension point=settings.getDimension("dimensionDoesNotExist", dimensionDefault);
		assertEquals(dimensionExpected,point);
	}

	@Test
	void whenReadingBadDimensionThenExceptionIsThrown() {
		final String propertyName="corrupteddimension";
		final String expectedMessage="Not a Dimension: "+propertyName;
		final Dimension dimensionDefault=new Dimension(1024,768);
		Exception exception=assertThrows(IllegalArgumentException.class, () -> settings.getDimension(propertyName, dimensionDefault));
		assertEquals(expectedMessage,exception.getMessage());
	}

	@Test
	void dimensionIsSetCorrectly() {
		final String newDimensionKey="newDimension";
		final Dimension newDimension=new Dimension(100,200);
		final Dimension dimensionExpected=new Dimension(100,200);
		final Dimension dimensionDefault=new Dimension(1024,768);
		assertFalse(settings.containsKey(newDimensionKey));
		settings.setDimension(newDimensionKey, newDimension);

		Dimension dimension=settings.getDimension(newDimensionKey,dimensionDefault);
		assertEquals(dimensionExpected,dimension);
	}

	@Test
	void whenStringlistExistsInPropertiesThenStringlistIsReturned() {
		final ArrayList<String> stringlistExpected=new ArrayList<String>();
		stringlistExpected.add("aaa");
		stringlistExpected.add("bbb");
		stringlistExpected.add("ccc");

		final ArrayList<String> stringlistDefault=new ArrayList<String>();
		stringlistDefault.add("111");
		stringlistDefault.add("222");
		stringlistDefault.add("333");

		ArrayList<String> stringlist=settings.getStringlist("stringlist", stringlistDefault);
		assertEquals(stringlistExpected,stringlist);
	}

	@Test
	void whenStringlistDoesNotExistInPropertiesButInDefaultPropertiesThenDefaultStringlistIsReturned() {
		final ArrayList<String> stringlistExpected=new ArrayList<String>();
		stringlistExpected.add("ddd");
		stringlistExpected.add("eee");
		stringlistExpected.add("fff");

		final ArrayList<String> stringlistDefault=new ArrayList<String>();
		stringlistDefault.add("111");
		stringlistDefault.add("222");
		stringlistDefault.add("333");

		ArrayList<String> stringlist=settings.getStringlist("stringlistDefault", stringlistDefault);
		assertEquals(stringlistExpected,stringlist);
	}

	@Test
	void whenStringlistNeitherExistsInPropertiesNorDefaultPropertiesThenEmptyStringlistIsReturned() {
		final ArrayList<String> stringlistExpected=new ArrayList<String>();

		ArrayList<String> stringlist=settings.getStringlist("stringlistDoesNotExist");
		assertEquals(stringlistExpected,stringlist);
	}

	@Test
	void whenStringlistNeitherExistsInPropertiesNorDefaultPropertiesThenGivenDefaultStringlistIsReturned() {
		final ArrayList<String> stringlistExpected=new ArrayList<String>();
		stringlistExpected.add("111");
		stringlistExpected.add("222");
		stringlistExpected.add("333");

		final ArrayList<String> stringlistDefault=new ArrayList<String>();
		stringlistDefault.add("111");
		stringlistDefault.add("222");
		stringlistDefault.add("333");

		ArrayList<String> stringlist=settings.getStringlist("stringlistDoesNotExist", stringlistDefault);
		assertEquals(stringlistExpected,stringlist);
	}

	@Test
	void stringlistIsSetCorrectly() {
		final String newStringlistKey="newStringlist";
		final ArrayList<String> newStringlist=new ArrayList<String>();
		newStringlist.add("444");
		newStringlist.add("555");
		newStringlist.add("666");

		final ArrayList<String> stringlistExpected=new ArrayList<String>();
		stringlistExpected.add("444");
		stringlistExpected.add("555");
		stringlistExpected.add("666");

		final ArrayList<String> stringlistDefault=new ArrayList<String>();
		stringlistDefault.add("111");
		stringlistDefault.add("222");
		stringlistDefault.add("333");

		assertFalse(settings.containsKey(newStringlistKey));
		settings.setStringlist(newStringlistKey, newStringlist);

		ArrayList<String> stringlist=settings.getStringlist(newStringlistKey,stringlistDefault);
		assertEquals(stringlistExpected,stringlist);
	}

	@Test
	void stringlistOverwritesStringlistCorrectly() {
		final String newStringlistKey="newStringlist";
		final ArrayList<String> firstStringlist=new ArrayList<String>();
		firstStringlist.add("111");
		firstStringlist.add("222");
		firstStringlist.add("333");

		final ArrayList<String> secondStringlist=new ArrayList<String>();
		secondStringlist.add("444");

		final ArrayList<String> stringlistExpected=new ArrayList<String>();
		stringlistExpected.add("444");

		final ArrayList<String> stringlistDefault=new ArrayList<String>();
		stringlistDefault.add("111");
		stringlistDefault.add("222");
		stringlistDefault.add("333");

		settings.setStringlist(newStringlistKey, firstStringlist);
		assertEquals(3,settings.getStringlist(newStringlistKey).size());

		settings.setStringlist(newStringlistKey, secondStringlist);
		assertEquals(1,settings.getStringlist(newStringlistKey).size());
		ArrayList<String> stringlist=settings.getStringlist(newStringlistKey,stringlistDefault);
		assertEquals(stringlistExpected,stringlist);

	}

}
