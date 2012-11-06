package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
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
		JLabel l = new JLabel();
		l.setHorizontalAlignment(SwingConstants.CENTER);
		p.add(l);
		//l.setLocation(getCenter(p, l));
		l.setText("This is a test"); //$NON-NLS-1$
		l.setAlignmentX((float) 0.5);
		l.repaint();
		g.drawImage(ImageIO.read(new File(TestPanel.IMAGE_PATH)), 0, 0, p);
		l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		l.paintImmediately(l.getBounds());
	}
	
	public static Point getCenter(JPanel panel, JLabel child) {
		Dimension size = panel.getSize();
		int x = size.width/2 - child.getWidth()/2;
		int y = size.height/2 - child.getHeight()/2;
		return new Point(x,y);
	}
	
}
