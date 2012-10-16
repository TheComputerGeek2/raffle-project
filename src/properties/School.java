package properties;

import java.awt.Color;
import java.io.Serializable;
import java.util.Scanner;

public class School implements Serializable {
	
	private static final long serialVersionUID = 5291934638095745657L;
	
	private String prefix;
	
	private Color color1;
	private Color color2;
	
/**Instantiates a new school object. The object contains the
 * following information: the school's prefix, the school's
 * primary color, and the school's secondary color.
 * @param prefix the school's three letter prefix.
 * @param red1 the red value of the school's primary color.
 * @param green1 the green value of the school's primary color.
 * @param blue1 the blue value of the school's primary color.
 * @param red2 the red value of the school's secondary color.
 * @param green2 the green value of the school's secondary color.
 * @param blue2 the blue value of the school's secondary color.*/
public School(String prefix, int red1, int green1, int blue1,
		int red2, int green2, int blue2) {
	this.prefix = prefix;
	color1 = new Color(red1, green1, blue1);
	color2 = new Color(red2, green2, blue2);
}

/**Instantiates a new school object. The object contains the
 * following information: the school's prefix, the school's
 * primary color, and the school's secondary color.
 * @param prefix the school's three letter prefix.
 * @param color1 the school's primary color.
 * @param color2 the school's secondary color.*/
public School(String prefix, Color color1, Color color2) {
	this(prefix, color1.getRed(), color1.getGreen(),
			color1.getBlue(), color2.getRed(), color2.getGreen(),
			color2.getBlue());
}

/**Instantiates a new school object from the data contained
 * in the provided string.
 * @param data the string that contains the school information.
 * @throws NoSuchElementException if there are not
 * enough tokens in the string.
 * @throws InputMismatchException if the needed inputs
 * are not provided in the correct order.*/
public School(String data) {
	Scanner input = new Scanner(data);
	prefix = input.next();
	int[] vals = new int[6];
	for (int i = 0; i < 6; i ++) {
		vals[i] = input.nextInt();
	}
	color1 = new Color(vals[0], vals[1], vals[2]);
	color2 = new Color(vals[3], vals[4], vals[5]);
}

/**Returns the school's prefix.
 * @return the school's prefix.*/
public String getPrefix() {
	return prefix;
}

/**Returns the school's primary color.
 * @return the primary color.*/
public Color getFirstColor() {
	return color1;
}

/**Returns the school's secondary color.
 * @return the secondary color.*/
public Color getSecondColor() {
	return color2;
}

/**Returns an array containing the school's colors.
 * The first value is the primary color.
 * The last value is the secondary color.
 * @return the school's colors.*/
public Color[] getColors() {
	return new Color[]{color1, color2}; 
}
}
