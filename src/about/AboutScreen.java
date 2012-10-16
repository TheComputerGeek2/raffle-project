package about;

import javax.swing.JFrame;

public class AboutScreen {
	
	private JFrame frame;
	private final int WIDTH = 150;
	private final int HEIGHT = 200;
	
	
public AboutScreen() {
	makeFrame();
}

private void makeFrame() {
	frame = new JFrame();
	frame.setSize(WIDTH, HEIGHT);
	
}
}
