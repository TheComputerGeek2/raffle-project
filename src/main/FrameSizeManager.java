package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FrameSizeManager implements WindowListener {

	public static final String CONFIG_FILE_DIRECTORY = Logger.LOG_DIRECTORY
			+ "config/";

	private static PrintStream out;

	public FrameSizeManager() {

	}

	private void initializeConfigFolder() {
		new File(FrameSizeManager.CONFIG_FILE_DIRECTORY).mkdirs();
	}

	private void initializePrintStream() {
		try {
			FrameSizeManager.out = new PrintStream(new File(
					FrameSizeManager.CONFIG_FILE_DIRECTORY + "config.txt"));
		} catch (FileNotFoundException | SecurityException e) {
			Logger.log(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public SizeData getSizeData() {
		Scanner reader = null;
		try {
			reader = new Scanner(new File(
					FrameSizeManager.CONFIG_FILE_DIRECTORY + "config.txt"));
			int width = Integer.parseInt(reader.nextLine());
			int height = Integer.parseInt(reader.nextLine());
			return new SizeData(width, height);
		} catch (FileNotFoundException e) {
			Logger.log(Logger.getDateStamp() + " Config file not found");
			return new SizeData();
		} catch (IllegalStateException | NoSuchElementException
				| NumberFormatException e) {
			Logger.log(e);
			return new SizeData();
		} finally {
			reader.close();
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}
