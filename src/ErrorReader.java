import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class ErrorReader {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(new File("C:\\Users\\ROS_SJ~1\\AppData\\Local\\Temp\\VSD5A95.tmp\\install.log"));
		PrintStream out = new PrintStream(new File("src/error.txt"));
		while (reader.hasNextLine()) {
			String temp = reader.nextLine();
			System.out.println(temp);
			out.println(temp);
		}

	}

}
