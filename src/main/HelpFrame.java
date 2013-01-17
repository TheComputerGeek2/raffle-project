package main;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class HelpFrame {

	private JFrame frame;

	private JFrame mainFrame;

	public HelpFrame(JFrame parent) {
		this.mainFrame = parent;

	}

	private void centerOnFrame(Rectangle parent, double percentOfWidth,
			double percentOfHeight) {
		// TODO resize the help frame to the percentage of the width and height
		// of the main frame specified
		int width = (int) (parent.width * (percentOfWidth / 100));
		int height = (int) (parent.height * (percentOfHeight / 100));
		this.frame.setSize(width, height);
		centerOnFrame(this.mainFrame.getBounds());

		// TODO position the help frame to the center of the main frame
	}

	private void centerOnFrame(Rectangle parent) {
		// TODO center the frame on the parent while keeping the existing
		// dimensions
	}

	// TODO prevent more than one help window from being instantiated at once
}
