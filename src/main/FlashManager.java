package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FlashManager implements MouseListener {

	
	private static boolean active;
	
	private static ThreePanelPicker picker;
	
	public FlashManager() {
		
	}

	public static void setActive(boolean isActive) {
		FlashManager.active = isActive;
	}
	
	public static void setDisplayManager(ThreePanelPicker picker) {
		FlashManager.picker = picker;
	}
	
	private static void respond() {
		if (active) {
			picker.stopFlashing();
			active= !active;
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