package graphics;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ticket {
	
	private BufferedImage baseImage = null;
	
	private String currentText;
	
	private Dimension currentSize;
	
public Ticket(String filePath) throws IOException {
	this(new File(getDirectory(filePath)));
}

private static String getDirectory(String relativePath) {
	return System.getProperty("user.dir") + "/" + relativePath;
}

public Ticket(File source) throws IOException {
	baseImage = ImageIO.read(source);
}

public Ticket(BufferedImage baseImage) {
	if (baseImage.equals(null)) {
		throw new NullPointerException();
	}
	this.baseImage = baseImage;
}

public void setText(String text) {
	this.currentText = text;
}

public String getText() {
	return currentText;
}

public void setSize(Dimension newSize) {
	if (newSize.width <= 0 || newSize.height<= 0) {
		throw new IllegalArgumentException();
	}
	this.currentSize = newSize;
}
}
