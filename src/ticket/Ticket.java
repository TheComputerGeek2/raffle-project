package ticket;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ticket {
	
private int x;

private int y;

private int xShift;
private int yShift;

private int value;
	
private BufferedImage ticketImg;

private VolatileImage vImg; //TODO try to create the image so that it can be released more efficiently

private ImageObserver observer = null;

private Graphics2D g2;

/**The Font to use for the value display.*/
private Font f;

/**The color to use for the value display.*/
private Color c;

private boolean isFinished = false;

/**Creates a new ticket to display values on the panel.
 * @param x the starting x coordinate of the top left corner of the image on the panel.
 * @param y the starting y coordinate of the top right corner of the image on the panel.
 * @param g2 the graphics object to use to draw the images.
 * @param observer the ImageObserver for the image.*/
public Ticket(int x, int y, Graphics2D g2, ImageObserver observer) {
	this.x = x;
	this.y = y;
	this.g2 = g2;
	this.observer = observer;
}

/**Sets the graphics object to use for painting the image onto the panel to g2.
 * @param g2 the new Graphics object to use.*/
public void setGraphics(Graphics2D g2) {
	this.g2 = g2;
}

public void draw() {
	//TODO draw on the specified panel using the x and y values as coordinates and displaying the ticket value
	g2.drawImage(ticketImg, this.x, this.y, observer);
	if (this.x + ticketImg.getWidth() < 0 || this.y + ticketImg.getHeight() < 0) {
		this.isFinished = true;
	}
}

/**Loads an image of the ticket into memory.
 * @param path the filepath of the ticket image.*/
public void loadImage(String path) {
	try {
		this.ticketImg = ImageIO.read(new File(path));
	} catch (IOException ioe) {
		ioe.printStackTrace();
	}
}

public void shiftDisplay() {
	this.x += this.xShift;
	this.y += this.yShift;
}

public void setShift(int x, int y) {
	this.xShift = x;
	this.yShift = y;
}

public void setValue(int value) {
	this.value = value;
	addValue();
}

private void addValue() {
	Graphics2D temp = ticketImg.createGraphics();
	temp.setFont(this.f);
	temp.setColor(this.c);
	//TODO determine where to paint the value based upon the size of the value
	//TODO paint the value onto the image
}



public boolean isFinished() {
	return this.isFinished;
}

public void destroy() {
	ticketImg.flush();
	vImg.flush();
	//TODO clear the other resources
}
}
