package graphics;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageDisplayPanel extends JPanel implements ComponentListener {
	
	private static final long serialVersionUID = -7977010858049061342L;

	private String imagePath;
	
	private BufferedImage img;
	
	
/**Instantiates a new ImageDisplayPanel.*/
public ImageDisplayPanel() {
	super();
}

/**Instantiates a new ImageDisplayPanel and loads the
 * image from the relative path specified.
 * @param imagePath the relative path of the image
 * (not including the project name or the first slash).*/
public ImageDisplayPanel(String imagePath) {
	super();
	prepImage(imagePath);
}

/**Stores the absolute image path and the buffered image
 * based upon the relative file path specified.
 * @param imagePath the file path of the image.*/
private void prepImage(String imagePath) {
	this.imagePath = getAbsolutePath(imagePath);
	this.img = loadImage(this.imagePath);
}

/**Reads an image from a file for which the location was passed
 * in and returns the buffered image. 
 * @param path the absolute path of the file.
 * @return the buffered image.*/
private BufferedImage loadImage(String path) {
	BufferedImage temp = null;
	try {
		temp = ImageIO.read(new File(path));
	} catch (IOException ioe) {
		ioe.printStackTrace();
		//FIXME fix the error handling
	}
	return temp;
}

/**Returns the absolute path representing the relative path provided.
 * The relative path should not include the project level,
 * but should start just below it and does not need to include the first slash.
 * @param relativePath the relative path.
 * @return the absolute path.*/
public static String getAbsolutePath(String relativePath) {
	return System.getProperty("user.dir") + "/" + relativePath;
}

/**Returns the buffered image loaded from the file.
 * @return the image.*/
public BufferedImage getImage() {
	return img;
}

public String toString() {
	return "ImageDisplayPanel[" + getX() + ", " + getY() + 
	", " + getWidth() + ", " + getHeight() + ", " + imagePath + "]";
}

private void reloadImage() {
	//TODO create the method body
}

@Override
public void componentHidden(ComponentEvent e) {}

@Override
public void componentMoved(ComponentEvent e) {}

@Override
public void componentResized(ComponentEvent e) {
	reloadImage();
}

@Override
public void componentShown(ComponentEvent e) {}

}
