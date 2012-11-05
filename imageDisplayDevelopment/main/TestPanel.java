package main;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

public class TestPanel {

	
	//FIXME requires an absolute path instead of a relative path
	public static final String IMAGE_PATH = "U:\\Workspace\\Raffle2\\imageDisplayDevelopment\\trollface.png"; //$NON-NLS-1$

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		JPanel p = new JPanel();
		frame.add(p);
		frame.setVisible(true);
		Graphics g = p.getGraphics();
		g.drawImage(ImageIO.read(new File(TestPanel.IMAGE_PATH)), 0, 0, p);
		
		p.setLayout(new OverlayLayout(p));
		JPanel panel2 = new JPanel();
		p.add(panel2);
		panel2.setLayout(new OverlayLayout(panel2));
		JLabel l = new JLabel();
		l.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(l);
		l.setText("This is a test"); //$NON-NLS-1$
		l.repaint();
		g.drawImage(ImageIO.read(new File(TestPanel.IMAGE_PATH)), 0, 0, p);
		l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		l.paintImmediately(l.getBounds());
	}
	
}
