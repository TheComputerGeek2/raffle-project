package graphics;

import java.awt.Font;
import java.awt.Graphics;

public class TextResizer {
	
	private String text;
	
	private Font f;
	
	private int width;
	
	private Graphics g;

/**Instantiates a new TextResizer.
 * @param text the string to use for adjusting the font.
 * @param f the font to adjust.
 * @param width the target width.
 * @param g a graphics component to use for the font metrics.*/
public TextResizer(String text, Font f, int width, Graphics g) {
	setTargetWidth(width);
	setText(text);
	this.f = f;
	this.g = g;
}

/**Returns the current width of the string stored in the current font.
 * @return the width of the string.*/
public int getCurrentWidth() {
	return g.getFontMetrics(f).stringWidth(text);
}

/**Adjusts the size of the font to get as close as possible to the target width.*/
public void adjustSize() {
	int currentWidth = getCurrentWidth();
	if (currentWidth > width) {
		while (currentWidth > width) {
			f = f.deriveFont((float)(f.getSize()-1));
			currentWidth = getCurrentWidth();
		}
	} else if (currentWidth < width) {
		while (currentWidth < width) {
			f = f.deriveFont((float)(f.getSize()-1));
			currentWidth = getCurrentWidth();
		}
	}
	if (currentWidth > width) {
		f = f.deriveFont((float)(f.getSize() -1));
	}
}

/**Sets the text to use for the resizing process.
 * @param text the text to use.
 * @throws IllegalArgumentException if text is null or has a length of zero.*/
public void setText(String text) {
	if (text.equals(null) || text.equals("")) {
		throw new IllegalArgumentException();
	}
	this.text = text;
}

/**Sets the target width.
 * @param width the new target width.
 * @throws IllegalArgumentException if width is less than or equal to zero.*/
public void setTargetWidth(int width) {
	if (width <= 0) {
		throw new IllegalArgumentException("Target width <= 0: " + width);
	}
	this.width = width;
}

/**Sets the font to store.
 * @param f the font.*/
public void setFont(Font f) {
	this.f = f;
}

/**Returns the target width.
 * @return the target width.*/
public int getWidth() {
	return width;
}

/**Returns the stored text.
 * @return the text.*/
public String getText() {
	return text;
}

/**Returns the current font.
 * @return the font.*/
public Font getFont() {
	return f;
}
}