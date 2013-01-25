package main;

import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class HelpFrame {

	private static HelpFrame activeInstance;

	private JFrame frame;

	private JFrame mainFrame;

	private static final double PERCENT_OF_WIDTH = 50;

	private static final double PERCENT_OF_HEIGHT = 50;

	private static final int WIDTH = 300;
	private static final int HEIGHT = 200;

	private Properties info;

	private JTextArea area;

	public HelpFrame(JFrame parent) {
		if (HelpFrame.activeInstance != null) {
			HelpFrame.activeInstance.closeFrame();
		}
		HelpFrame.activeInstance = this;
		this.mainFrame = parent;
		makeFrame();
	}

	private void closeFrame() {
		this.frame.setVisible(false);
		this.frame.dispose();
	}

	private void centerOnFrame(Rectangle parent, int width, int height) {
		this.frame.setSize(width, height);
		centerOnFrame(parent);
	}

	private void centerOnFrame(Rectangle parent, double percentOfWidth,
			double percentOfHeight) {
		int width = (int) (parent.width * (percentOfWidth / 100));
		int height = (int) (parent.height * (percentOfHeight / 100));
		this.frame.setSize(width, height);
		centerOnFrame(this.mainFrame.getBounds());
	}

	private void centerOnFrame(Rectangle parent) {
		if (this.frame.getWidth() > parent.width) {
			Logger.log("The width of the help frame is greater than the width of the main frame.");
		}
		if (this.frame.getHeight() > parent.height) {
			Logger.log("The height of the help frame is greater than the height of the main frame.");
		}

		int xCoordinate = (parent.width / 2) - (this.frame.getWidth() / 2)
				+ parent.x;
		int yCoordinate = (parent.height / 2) - (this.frame.getHeight() / 2)
				+ parent.y;
		this.frame.setLocation(xCoordinate, yCoordinate);

	}

	private void makeFrame() {
		this.frame = new JFrame();
		centerOnFrame(this.mainFrame.getBounds(), HelpFrame.WIDTH,
				HelpFrame.HEIGHT);
		addContent();
		this.frame.setResizable(false);
		this.frame.setTitle("About");
		this.frame.setVisible(true);
	}

	private void addContent() {
		// loadProperties();
		this.area = new JTextArea();
		this.frame.add(area);
		this.area.setLineWrap(true);
		this.area.setWrapStyleWord(true);
		this.area.setText(getInfo());
		this.area.setEditable(false);
	}

	private String getInfo() {
		String temp = "";
		//temp += Info.name + "\n";
		temp += "Developed by " + Info.developer + "\n";
		temp += "Version " + Info.version + "\n";
		temp += "Minimum Java version: " + Info.minJavaVersion + "\n";
		temp += "Log directory: " + Info.logDirectory + "\n";
		temp += "To restore the default size: " + Info.restoreDefaultSize
				+ "\n";
		temp += Info.contact;
		return temp;

	}

	private void loadProperties() {
		this.info = new Properties();
		FileReader temp = null;
		try {
			temp = new FileReader("/raffle-project/src/main/info.properties");
		} catch (FileNotFoundException e1) {
			Logger.log(e1);
			e1.printStackTrace();
		}
		try {
			this.info.load(temp);
		} catch (IOException e) {
			Logger.log(e);
		}
		try {
			temp.close();
		} catch (IOException e) {
			Logger.log(e);
			e.printStackTrace();
		}
	}
}
