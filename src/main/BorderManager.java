package main;

import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class BorderManager {

	private JComponent comp;

	private Border b;

	private boolean borderDisplayed = false;

	public BorderManager(JComponent comp) {
		this.comp = comp;
		loadBorder();
	}

	private void loadBorder() {
		// TODO create border from image

	}

	public void setBorderVivibility(boolean visibility) {
		this.borderDisplayed = visibility;
	}

	/**
	 * Updates and repaints the border.
	 * 
	 * @throws AWTError
	 *             thrown by the {@link Toolkit}
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
