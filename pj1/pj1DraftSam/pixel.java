//Pixel Class
public class pixel {
	//Fields
	public short red;
	public short green;
	public short blue;

	//Constructors
	public pixel() {
		red = 0;
		green = 0;
		blue = 0;
	}
	public pixel(short r, short g, short b) {
		if (r<0||r>255||g<0||g>255||b<0||b>255) {
			System.out.println("Invalid Pixel Constructed");
			System.exit(0);
		}
		red = r;
		green = g;
		blue = b;
	}
}
