package main;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

/**
 * A number generator for use as a raffle picker.
 * 
 * @author samdixon
 * @version 2.0
 */
public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws HeadlessException, IOException {
		try {
			new UserInterface();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
