package main;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DimensionsProcessor {

	private double scale;

	/**
	 * Instantiates a new DimensionsProcessor for the specified width and height
	 * of a component.
	 * 
	 * @param width
	 *            the base width of the component.
	 * @param height
	 *            the base height of the component.
	 * @throws AWTError
	 *             if a toolkit could not be found, or if one could not be
	 *             accessed or instantiated.
	 * @throws HeadlessException
	 *             if GraphicsEnvironment.isHeadless() returns true.
	 */
	public DimensionsProcessor(int width, int height) {
		double scale = determineScale(width, height);
		this.scale = scale;
	}

	/**
	 * Returns the value by which to scale the font sizes and dimensions.
	 * 
	 * @param width
	 *            the base width of the frame.
	 * @param height
	 *            the base height of the frame.
	 * @return the scale.
	 * @throws AWTError
	 *             if a toolkit could not be found, or if one could not be
	 *             accessed or instantiated.
	 * @throws HeadlessException
	 *             if GraphicsEnvironment.isHeadless() returns true.
	 */
	public final static double determineScale(int width, int height) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double widthScale = (double) screenSize.width / width;
		double heightScale = (double) screenSize.height / height;
		return Math.min(widthScale, heightScale);
	}

	/**
	 * Scales the specified value by the scale calculated from the width and
	 * height of the frame.
	 * 
	 * @param value
	 *            the value to be scaled.
	 * @return the resulting value.
	 */
	public final int scaleValue(int value) {
		int temp = (int) (scale * value);
		return temp;
	}
}
