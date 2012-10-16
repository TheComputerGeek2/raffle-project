package properties;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;

public class Reader {

	
	public static final String COLOR_FILE_PATH = "/Raffle2/bin/properties/SchoolColors.txt";
	
	public static final School ROOSEVELT = new School("ros",
			new Color(23, 172, 23), Color.ORANGE);
	
	private School currentSchool;
	
public Reader() {
	loadColors();
}

private void loadColors() {
	try {
		Scanner input = new Scanner(new File(COLOR_FILE_PATH));
		HashMap<String, School> temp = new HashMap<String, School>();
		while (input.hasNextLine()) {
			School s = new School(input.nextLine());
			temp.put(s.getPrefix(), s);
		}
		String currentSchoolName = getSchoolName(getHostName());
		if (temp.containsKey(currentSchoolName)) {
			currentSchool = temp.get(currentSchoolName);
		} else {
			currentSchool = ROOSEVELT;
		}
	} catch (FileNotFoundException e) {
		currentSchool = ROOSEVELT;
	} catch (UnknownHostException e) {
		currentSchool = ROOSEVELT;
	}
}

public School getCurrentSchool() {
	return currentSchool;
}

public static String getHostName() throws UnknownHostException {
	return InetAddress.getLocalHost().getHostName();
}

public static String getSchoolName(String hostName) {
	String temp = hostName.substring(1, 4);
	return temp;
}

public static int getRoomNumber(String hostName) {
	return Integer.parseInt(hostName.substring(6, 9));
}

}
