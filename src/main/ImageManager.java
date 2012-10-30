package main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageManager {

	private UserInterface ui;

	private JPanel[] panels;

	public ImageManager(UserInterface ui) {
		this.ui = ui;
		this.panels = ui.getImgPanels();
	}

	public void displayCenterImage() throws IOException {
		
		Graphics g = panels[1].getGraphics();
		if (g.equals(null)) {
			System.out.println("Graphics = null");
		}
		g.drawImage(loadImage(), 0, 0, panels[1]);
	}

	private Image loadImage() throws IOException {
		return ImageIO.read(new File(
				"U:\\Workspace\\Raffle2\\src\\main\\trollface.png")); //$NON-NLS-1$

	}

}
