package lunartools;

/**
 * A simple class to store a RGB pixel.
 * 
 * @author Thomas Mattel
 */
public class ColourRGBG{
	private int colour;
	private int red;
	private int green;
	private int blue;
	private int grey;

	public ColourRGBG(int colour) {
		colour=this.colour=colour&0xffffff;
		this.red=(colour>>16);
		this.green=(colour>>8) & 0xff;
		this.blue=colour & 0xff;
		this.grey=(this.red+this.green+this.blue)/3;
	}

	public int getColour() {
		return colour;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public int getGrey() {
		return grey;
	}

	boolean isGrey() {
		return red==green && red==blue;
	}

	public String toString() {
		return Integer.toHexString(colour);
	}

}
