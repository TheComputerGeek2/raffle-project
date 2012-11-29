package main;

import java.awt.AWTError;
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

	private boolean borderDisplayed = false;

	public static final String FILE_PATH = "/raffle-project/src/main/trollface.png";

	private ImageIcon ii;

	public BorderManager(JComponent comp) {
		this.comp = comp;
		loadBorder();
	}

	private void loadBorder() {
		this.ii = new ImageIcon(BorderManager.FILE_PATH);
		loadImage();
		this.b = BorderFactory.createMatteBorder(-1, -1, -1, -1, this.ii);
		// FIXME change the parameters to properly display the icon

	}
	
	//FIXME fix the image loading
	//TODO create a media tracker to track the image
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
			this.comp.setBorder(BorderFactory.createEmptyBorder());
		}
		this.comp.repaint();
		Toolkit.getDefaultToolkit().sync();
	}

}
