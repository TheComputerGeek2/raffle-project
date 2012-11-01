package main;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestPanel {
	
	public static final String IMAGE_PATH = "U:\\Workspace\\Raffle2\\imageDisplayDevelopment\\trollface.png";
	
public static void main(String[] args) throws IOException {
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(500,500);
	JPanel p = new JPanel();
	frame.add(p);
	frame.setVisible(true);
	Graphics g = p.getGraphics();
	g.drawImage(ImageIO.read(new File(TestPanel.IMAGE_PATH)), 0,0, p);
}

}
