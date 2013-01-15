package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class RestoreDefaultSize implements KeyListener {

	private JFrame frame;

	public RestoreDefaultSize(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (KeyEvent.VK_F2 == arg0.getKeyCode()) {
			FrameSizeManager.shouldUseDefaultSize(true);
			FrameSizeManager.resizeToDefault(this.frame);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (KeyEvent.VK_F2 == arg0.getKeyCode()) {
			FrameSizeManager.shouldUseDefaultSize(true);
			FrameSizeManager.resizeToDefault(this.frame);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
