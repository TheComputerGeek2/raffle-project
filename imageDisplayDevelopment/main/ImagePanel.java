package main;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel {

	private JPanel colorPanel;

	private JPanel imagePanel;

	private Graphics g;

	private JLabel textPanel;

	private JFrame frame;

	private BufferedImage ticketImage;
	
	public static final String IMAGE_PATH = "U:\\Workspace\\Raffle2\\imageDisplayDevelopment\\trollface.png";
	
	public ImagePanel(JPanel host) {
		findFrame(host);
		loadImage();
		//addListenerToHost();
		colorPanel = new JPanel();
		colorPanel.setOpaque(true);
		imagePanel = new JPanel();
		imagePanel.setOpaque(true);
		textPanel = new JLabel();
		textPanel.setOpaque(false);
		stackPanels(host);
	}

	private void loadImage() {
		try {
			this.ticketImage = ImageIO.read(new File(ImagePanel.IMAGE_PATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void stackPanels(JPanel host) {
		host.add(imagePanel);
		//colorPanel.add(imagePanel);
	//	imagePanel.add(textPanel);
	}

	private void addListenerToHost() {
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				loadGraphics();
			}

		});
	}

	protected void loadGraphics() {
		this.g = this.imagePanel.getGraphics();
		if (g.equals(null)) {
			System.out.println("Graphics are null");
		}
	}

	private void findFrame(JPanel host) {
		Component parent = host.getParent();
		while (!(parent instanceof JFrame)) {
			parent = parent.getParent();
		}
		this.frame = (JFrame) parent;
	}

	public JPanel getColorPanel() {
		return this.colorPanel;
	}

	public JLabel getTextPanel() {
		return textPanel;
	}

	public JPanel getImagePanel() {
		return this.imagePanel;
	}

	public boolean displayImage() {
		return g.drawImage(this.ticketImage, 0, 0, this.imagePanel);

	}

	public boolean hasGraphicsReady() {
		return !g.equals(null);
	}
	
}
