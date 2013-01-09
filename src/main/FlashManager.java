package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

public class FlashManager implements MouseListener {

	private static boolean active;

	private static ThreePanelPicker picker;

	private static Timer timer = new Timer();

	public static final long DELAY = 5000;

	public FlashManager() {

	}

	private static void setTimer() {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				FlashManager.respond();
			}
		};
		try {
			FlashManager.timer.schedule(task, FlashManager.DELAY);
		} catch (IllegalArgumentException | IllegalStateException
				| NullPointerException e) {
			Logger.log(e);
			throw e;
		}
	}

	public static void setActive(boolean isActive) {
		FlashManager.active = isActive;
		if (isActive) {
			setTimer();
		}
	}

	public static void setDisplayManager(ThreePanelPicker picker) {
		FlashManager.picker = picker;
	}

	static void respond() {
		if (FlashManager.active) {
			FlashManager.picker.stopFlashing();
			FlashManager.active = !FlashManager.active;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		respond();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}