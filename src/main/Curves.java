package main;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;

public class Curves {

	public static final double CUTOFF = 1.1;
	public static final double ACCELERATION_STARTING_POINT = 1.1667;
	public static final double SLOWDOWN_STARTING_POINT = 1.5;
	public static final double ENDING_POINT = 4.5;
	public static final double CEILING = 2.0;

	private ArrayList<Double> delays;
	private int index;

	private Robot robot;

	public Curves() throws AWTException {
		this.delays = new ArrayList<Double>();
		this.index = 0;
		this.robot = new Robot();
	}

	public static double getAcceleratingCurveValue(double value) {
		return Math.sin((3 * value) * Math.PI) + 1;
	}

	public static double getDeceleratingCurveValue(double value) {
		return Math.sin((value / 3) * Math.PI) + 1;
	}

	public static double getPointOnCombinedCurves(double value) {
		if (value < Curves.SLOWDOWN_STARTING_POINT) {
			return Curves.getAcceleratingCurveValue(value);
		}
		return Curves.getDeceleratingCurveValue(value);
	}

	public static double[] getPoints(double minimum, double maximum,
			int numPoints) {
		double range = maximum - minimum;
		double gap = range / (numPoints - 1);
		double[] points = new double[numPoints];
		for (int i = 0; i < numPoints; i++) {
			points[i] = gap * i + minimum;
		}
		return points;
	}

	public static double[] getFullPointSet(int acceleratingPoints,
			int deceleratingPoints) {
		double[] accelerating = Curves.getPoints(
				Curves.ACCELERATION_STARTING_POINT + Curves.CUTOFF,
				Curves.SLOWDOWN_STARTING_POINT, acceleratingPoints);
		double[] decelerating = Curves.getPoints(
				Curves.SLOWDOWN_STARTING_POINT, Curves.ENDING_POINT,
				deceleratingPoints);
		int numPoints = accelerating.length + decelerating.length - 1;
		double[] points = new double[numPoints];
		for (int i = 0; i < accelerating.length; i++) {
			points[i] = accelerating[i];
		}
		for (int i = 1; i < decelerating.length; i++) {
			points[accelerating.length - 1 + i] = decelerating[i];
		}
		return points;
	}

	public static double[] getFullPointSet(int numPoints) {
		double[] points = new double[numPoints];
		double gap = (Curves.ENDING_POINT - Curves.ACCELERATION_STARTING_POINT)
				/ (numPoints - 1);
		for (int i = 0; i < numPoints; i++) {
			points[i] = Curves.ACCELERATION_STARTING_POINT + gap * i;
		}
		return points;
	}

	public double[] deriveDelayMultapliers(double[] points) {
		double[] delays = new double[points.length];
		for (int i = 0; i < points.length; i++) {
			delays[i] = Curves.CEILING
					- Curves.getPointOnCombinedCurves(points[i]);
		}
		return delays;
	}

	public double[] getDelayMultapliers(int numPoints) {
		this.delays.clear();
		double[] temp = deriveDelayMultapliers(Curves
				.getFullPointSet(numPoints));
		for (int i = 0; i < temp.length; i++) {
			this.delays.add(temp[i]);
		}
		this.index = 0;
		return temp;
	}

	/**
	 * public boolean useNextDelay ()
	 * <p>
	 * 
	 * Uses the next delay on the line.
	 * 
	 * @return if there are more delays on the line.
	 */
	public boolean useNextDelay() {
		return useNextDelay(1.0);
	}

	public int remainingDelays() {
		return this.delays.size() - this.index;
	}

	public boolean useNextDelay(double multaplier) {
		if (this.index == this.delays.size()) {
			this.index = 0;
			return false;
		}
		this.robot.delay((int) ((UserInterface.BASE_DELAY * this.delays
				.get(this.index)) * multaplier));
		this.index++;
		return true;
	}

	public void useDelay() {
		if (this.index >= this.delays.size()) {
			return;
		}
		this.robot.delay((int) ((UserInterface.BASE_DELAY * this.delays
				.get(this.index))));
	}

}
