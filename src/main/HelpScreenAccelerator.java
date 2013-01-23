package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class HelpScreenAccelerator implements KeyListener {

	private JFrame frame;

	public HelpScreenAccelerator(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		processEvent(arg0);

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void processEvent(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_F1) {
			new HelpFrame(this.frame);
		}
	}
}
