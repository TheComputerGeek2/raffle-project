package graphics;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageEditor {
	
	/**The absolute path of the image.*/
	private String imagePath;
	
	private BufferedImage baseImage;
	
	private BufferedImage scaledImage;
	
	private TextResizer tr;
	
public ImageEditor(String imagePath) {
	this.imagePath = imagePath;
	try {
		baseImage = ImageIO.read(new File(imagePath));
	} catch (IOException ioe) {
		ioe.printStackTrace();
	}
}

/**Returns the image's file path.
 * @return the file path.*/
public String getImagePath() {
	return imagePath;
}

public BufferedImage getBaseImage() {
	return baseImage.getSubimage(1, 1, baseImage.getWidth(), baseImage.getHeight());
}

public void reloadImage(int width, int height) {
	this.scaledImage = (BufferedImage)baseImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
}

public BufferedImage getEditedImage(int width, int height,
		String text, Font f, double percentageFromTop,
		double percentageFromLeft, double percentageFromRight) {
	BufferedImage temp = null;
	//TODO create the method body
	return temp;
}

private BufferedImage addText(String text, Font f, double percentageFromTop,
		double percentageFromLeft, double percentageFromRight) {
	BufferedImage temp = this.scaledImage.getSubimage(1, 1, scaledImage.getWidth(), scaledImage.getHeight());
	Graphics2D g2 = temp.createGraphics();
	int leftSide = (int)(temp.getWidth()*(percentageFromLeft/100));
	int rightSide = (int)(temp.getWidth()*((100-percentageFromRight)/100));
	int vert = (int)(temp.getHeight()*(percentageFromTop/100));
	int width = rightSide-leftSide;
	//TODO resize the text
	//TODO add the text
	//TODO create the method body
	return temp;
}

private BufferedImage scaleImage(BufferedImage base, int width, int height) {
	return (BufferedImage)base.getScaledInstance(width, height, Image.SCALE_SMOOTH);
}
}
