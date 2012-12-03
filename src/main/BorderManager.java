package main;

import java.awt.AWTError;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class BorderManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4036861167521151112L;

	private JComponent comp;

	private Border b;

	private Border empty;
	
	private boolean borderDisplayed = false;

	// TODO get the image directory on the client machine
	public static final String FILE_PATH = "H:\\Workspace\\raffle-project\\src\\main\\trollface.png";

	private ImageIcon ii;

	public BorderManager(JComponent comp) {
		this.comp = comp;
		loadBorder();
	}
	
	private void loadBorder() {
		this.ii = null;
		this.b = BorderFactory.createLineBorder(Color.RED, 1);
		this.empty = BorderFactory.createLineBorder(UserInterface.GOLD, 1);
	}

	/*private void loadBorder() {
		//TODO load the image and resize it according to the gui size
		
		this.ii = new ImageIcon(BorderManager.FILE_PATH);
		loadImage();
		this.b = BorderFactory.createMatteBorder(-1, -1, -1, -1, this.ii);
		// FIXME change the parameters to properly display the icon

	}*/

	// FIXME fix the image loading
	// TODO create a media tracker to track the image
	/* TODO consider removing this method it is potentially unneeded but useful
	* for error tracking */
	private void loadImage() {
		boolean loaded = false;
		while (!loaded) {
			switch (this.ii.getImageLoadStatus()) {
			case MediaTracker.ABORTED:
				System.out.println("Aborted");
				return;
			case MediaTracker.COMPLETE:
				System.out.println("Completed");
				loaded = true;
				break;
			case MediaTracker.ERRORED:
				System.out.println("Error");
				return;
			case MediaTracker.LOADING:
				System.out.println("loading");
				break;

			}
		}
	}

	// TODO get an image to use for the border

	public void setBorderVivibility(boolean visibility) {
		this.borderDisplayed = visibility;
	}

	public void toggleBorderVisibility() {
		this.borderDisplayed = !this.borderDisplayed;
	}

	/**
	 * Updates and repaints the border.
	 * 
	 * @throws AWTError
	 *             thrown by the Toolkit
	 */
	public void updateBorder() {
		if (this.borderDisplayed) {
			this.comp.setBorder(this.b);
		} else {
			//this.comp.setBorder(BorderFactory.createEmptyBorder());
			this.comp.setBorder(this.empty);
		}
		
		this.comp.paintImmediately(this.comp.getBounds());
		Toolkit.getDefaultToolkit().sync();
	}

}
