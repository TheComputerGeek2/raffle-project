package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
public static void main(String[] args) {
	JFrame frame = new JFrame();
	frame.setSize(500,500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel temp = new JPanel();
	frame.add(temp);
	ImagePanel ip = new ImagePanel(temp);
	frame.setVisible(true);
	ip.displayImage();
	frame.repaint();
}
}
