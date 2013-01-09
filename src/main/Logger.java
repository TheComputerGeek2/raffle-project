package main;

import java.awt.AWTError;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	public static final boolean LOGGER_ACTIVE = true;

	public static final String LOG_DIRECTORY = "H://RaffleLogs/";

	private static String secondaryLogDirectory;

	public static final String BASE_FILENAME = "RaffleLog.txt";

	private static PrintStream out;

	// TODO create a log in the H drive
	// TODO create a config file and a means to enable or disable the
	// logging
	/*
	 * Information to log: Minimum value, maximum value, ending value, seed,
	 * date
	 */

	/*
	 * Logging options: where to keep the log if a log should be kept
	 */

	public static String getDateStamp() {
		Date temp = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
		String dateString = sdf.format(temp);
		return dateString;
	}

	public static String formatInformation(int minimum, int maximum,
			int valueGenerated) {
		return Logger.getDateStamp() + " Minimum: (" + minimum + ") Maximum: ("
				+ maximum + ") Value Generated: (" + valueGenerated + ")";
	}

	/**
	 * public static void log ({@link String} information)
	 * <p>
	 * Logs the information provided to the file.
	 * 
	 * @param e
	 *            the formatted information to save.
	 */
	public static void log(String e) {
		if (!Logger.LOGGER_ACTIVE) {
			return;
		}
		Logger.initialize();
		Logger.out.println(e);

	}

	public static void log(Throwable t) {
		if (!Logger.LOGGER_ACTIVE) {
			return;
		}
		Logger.initialize();
		t.printStackTrace(Logger.out);
	}

	private static void makeLogFolder() {
		Logger.secondaryLogDirectory = System.currentTimeMillis() + "";
		new File("H://RaffleLogs/" + Logger.secondaryLogDirectory).mkdirs();
	}

	private static void initialize() {

		if (Logger.out == (null)) {
			Logger.makeLogFolder();
			try {
				Logger.out = new PrintStream(Logger.LOG_DIRECTORY
						+ Logger.secondaryLogDirectory + "/" + "Raffle.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
