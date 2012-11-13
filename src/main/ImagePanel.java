package main;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class ImagePanel extends JLabel {

	
	//TODO find the path on the client machine or use a relative path
	public static final String IMAGE_PATH = "U:\\Workspace\\Raffle2\\src\\main\\trollface.png"; //$NON-NLS-1$

	private boolean ticketDisplayed = false;

	private BufferedImage ticketImg;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2611948174530037025L;

	private JPanel p;

	private Graphics g;

	public ImagePanel() {
		super();
		loadImage();
		this.p = new JPanel();
		updateGraphics();
		this.p.setLayout(new OverlayLayout(this.p));
		this.p.add(this);
		this.setAlignmentX((float) 0.5);
	}

	public void updateGraphics() {
		this.g = this.p.getGraphics();
	}

	private void loadImage() {
		try {
			this.ticketImg = javax.imageio.ImageIO.read(new File(ImagePanel.IMAGE_PATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void resizeImage() {
		this.ticketImg = (BufferedImage) this.ticketImg.getScaledInstance(
				this.p.getWidth(), this.p.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		// TODO resize the image to the panel and call the method where needed
	}

	public void updateImage() {
		if (this.ticketDisplayed) {
			this.g.drawImage(this.ticketImg, 0, 0, this.p);
		}
	}

	public void setImageDisplayed(boolean isDisplayed) {
		this.ticketDisplayed = isDisplayed;
	}

	protected void loadListeners() {
		JFrame host = getHost();
		host.addWindowListener(new WindowListener() {

			private void update() {
				updateGraphics();
				updateImage();
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				update();

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
				update();
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				update();
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				update();
			}

		});

	}

	private JFrame getHost() {
		Component host = this.getParent();
		while (!(host instanceof JFrame)) {
			host = host.getParent();
		}
		return (JFrame) host;

	}

}
