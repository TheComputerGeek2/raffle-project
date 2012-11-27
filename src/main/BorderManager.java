package main;

import java.awt.AWTError;
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
		this.b = BorderFactory.createMatteBorder(0, 0, 0, 0, this.ii);
		// FIXME change the parameters to properly display the icon

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
