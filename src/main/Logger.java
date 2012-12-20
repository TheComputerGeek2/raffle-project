package main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	public Logger() {
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
	}

	public static String getDateStamp() {
		Date temp = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
		String dateString = sdf.format(temp);
		return dateString;
	}

	public String formatInformation(long seed, int minimum, int maximum) {
		return Logger.getDateStamp() + " Seed (" + String.valueOf(seed)
				+ ") Minimum: (" + minimum + ") Maximum: (" + maximum + ")";
	}
}
