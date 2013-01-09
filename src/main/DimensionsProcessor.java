package main;

import java.awt.AWTError;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

public class DimensionsProcessor {

	private double scale;

	/**
	 * public DimensionsProcessor (int width, int height)
	 * <p>
	 * 
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
	 * public static final double determineScale (int width, int height)
	 * <p>
	 * 
	 * 
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
		Dimension screenSize;
		try {
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		} catch (AWTError | HeadlessException e) {
			Logger.log(e);
			throw e;
		}
		double widthScale = (double) screenSize.width / width;
		double heightScale = (double) screenSize.height / height;
		return Math.min(widthScale, heightScale);
	}

	/**
	 * public final int scaleValue (int value)
	 * <p>
	 * 
	 * Scales the specified value by the scale calculated from the width and
	 * height of the frame.
	 * 
	 * @param value
	 *            the value to be scaled.
	 * @return the resulting value.
	 */
	public final int scaleValue(int value) {
		int temp = (int) (this.scale * value);
		return temp;
	}
}
