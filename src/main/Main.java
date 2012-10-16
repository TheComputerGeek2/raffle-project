package main;

import java.awt.AWTException;

/**A number generator for use as a raffle picker.
 * @author samdixon
 * @version 2.0*/
public class Main {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			new UserInterface();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
}
