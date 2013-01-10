package main;

import java.awt.AWTError;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

public class SizeData {

	public boolean useDefault = true;

	public int savedWidth;

	public int savedHeight;

	public SizeData() {

	}

	public SizeData(int savedWidth, int savedHeight) {
		this.savedWidth = savedWidth;
		this.savedHeight = savedHeight;
		this.useDefault = shouldUseDefaultSize();
	}

	private boolean shouldUseDefaultSize() {
		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			if (screenSize.width < this.savedWidth
					|| screenSize.height < this.savedHeight) {
				return true;
			}
			return false;
		} catch (AWTError | HeadlessException e) {
			Logger.log(e);
			throw e;

		}

	}
}
