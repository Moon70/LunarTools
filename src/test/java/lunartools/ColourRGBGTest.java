package lunartools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ColourRGBGTest {

	@Test
	void colourRGBGObjectIsCreatedCorrectly() {
		ColourRGBG colourRGBG=new ColourRGBG(0x00010203);
		assertEquals(0x00010203,colourRGBG.getColour());
		assertEquals(0x01,colourRGBG.getRed());
		assertEquals(0x02,colourRGBG.getGreen());
		assertEquals(0x03,colourRGBG.getBlue());
		assertEquals(0x02,colourRGBG.getGrey());
		assertFalse(colourRGBG.isGrey());
	}

	@Test
	void greyColourIsRecognizedCorrectly() {
		ColourRGBG colourRGBG=new ColourRGBG(0x00020202);
		assertTrue(colourRGBG.isGrey());
	}
	
}
