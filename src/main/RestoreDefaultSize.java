package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RestoreDefaultSize implements KeyListener {
	public RestoreDefaultSize() {

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (KeyEvent.VK_F2 == arg0.getKeyCode()) {
			FrameSizeManager.shouldUseDefaultSize(true);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (KeyEvent.VK_F2 == arg0.getKeyCode()) {
			FrameSizeManager.shouldUseDefaultSize(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
