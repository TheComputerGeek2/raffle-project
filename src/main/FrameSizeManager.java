package main;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;

public class FrameSizeManager implements WindowListener {

	public static final String CONFIG_FILE_DIRECTORY = Logger.LOG_DIRECTORY
			+ "config/";

	private static PrintStream out;

	private JFrame frame;

	private static Dimension defaultSize;

	private static boolean useDefaultSize = true;

	private static boolean willRestoreDefault = false;

	public FrameSizeManager(JFrame frame) {
		this.frame = frame;
	}

	public static void shouldUseDefaultSize(boolean useDefault) {
		FrameSizeManager.willRestoreDefault = useDefault;
		FrameSizeManager.useDefaultSize = useDefault;
	}

	public void resizeFrame() {
		SizeData data = getSizeData();
		if (data.useDefault) {
			FrameSizeManager.useDefaultSize = true;
			return;
		}
		this.frame.setSize(data.savedWidth, data.savedHeight);
	}

	public static void resizeToDefault(JFrame frame) {
		frame.setSize(FrameSizeManager.defaultSize);
	}

	public void setDefaultSize(Dimension defaultSize) {
		FrameSizeManager.defaultSize = defaultSize;
	}

	private void saveData() {
		initializeConfigFolder();
		initializePrintStream();
		writeData();
	}

	private boolean isDefaultSize() {
		return frame.getWidth() == FrameSizeManager.defaultSize.width
				&& frame.getHeight() == FrameSizeManager.defaultSize.height;
	}

	private void writeData() {
		FrameSizeManager.useDefaultSize = /*
										 * FrameSizeManager.willRestoreDefault
										 * ||
										 */isDefaultSize();
		FrameSizeManager.out.println(FrameSizeManager.useDefaultSize);
		FrameSizeManager.out.println(this.frame.getWidth());
		FrameSizeManager.out.println(this.frame.getHeight());
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
			e.printStackTrace();
		}

	}

	public SizeData getSizeData() {
		Scanner reader = null;
		try {
			reader = new Scanner(new File(
					FrameSizeManager.CONFIG_FILE_DIRECTORY + "config.txt"));
			if (Boolean.parseBoolean(reader.nextLine())) {
				return new SizeData();
			}
			int width = Integer.parseInt(reader.nextLine());
			int height = Integer.parseInt(reader.nextLine());
			return new SizeData(width, height);
		} catch (FileNotFoundException e) {
			Logger.log(Logger.getDateStamp() + " Config file not found");
			return new SizeData();
		} catch (IllegalStateException | NumberFormatException e) {
			Logger.log(e);
			return new SizeData();
		} catch (NoSuchElementException e) {
			Logger.log("The config file appears to be malformed");
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
		this.saveData();

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
