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
	delays = new ArrayList<Double>();
	index = 0;
	robot = new Robot();
}

public static double getAcceleratingCurveValue(double value) {
	return Math.sin((3*value)*Math.PI) +1;
}

public static double getDeceleratingCurveValue(double value) {
	return Math.sin((value/3)*Math.PI)+1;
}

public static double getPointOnCombinedCurves(double value) {
	if (value < SLOWDOWN_STARTING_POINT) {
		return getAcceleratingCurveValue(value);
	}
	return getDeceleratingCurveValue(value);
}

public static double[] getPoints(double minimum, double maximum, int numPoints) {
	double range = maximum-minimum;
	double gap = range/(numPoints-1);
	double[] points = new double[numPoints];
	for (int i = 0; i <numPoints; i++) {
		points[i] = gap*i + minimum;
	}
	return points;
}

public static double[] getFullPointSet(int acceleratingPoints, int deceleratingPoints) {
	double[] accelerating = getPoints(ACCELERATION_STARTING_POINT + CUTOFF, SLOWDOWN_STARTING_POINT, acceleratingPoints);
	double[] decelerating = getPoints(SLOWDOWN_STARTING_POINT, ENDING_POINT, deceleratingPoints);
	int numPoints = accelerating.length + decelerating.length-1;
	double[] points = new double[numPoints];
	for (int i = 0; i < accelerating.length; i++) {
		points[i] = accelerating[i];
	}
	for (int i = 1; i < decelerating.length; i++) {
		points[accelerating.length-1+i] = decelerating[i];
	}
	return points;
}

public static double[] getFullPointSet(int numPoints) {
	double[] points = new double[numPoints];
	double gap = (ENDING_POINT-ACCELERATION_STARTING_POINT)/(numPoints-1);
	for (int i = 0; i < numPoints; i++) {
		points[i] = ACCELERATION_STARTING_POINT + gap*i;
	}
	return points;
}

public double[] deriveDelayMultapliers(double[] points) {
	double[] delays = new double[points.length];
	for (int i = 0; i < points.length; i++) {
		delays[i] = CEILING - getPointOnCombinedCurves(points[i]);
	}
	return delays;
}

public double[] getDelayMultapliers(int numPoints) {
	delays.clear();
	double[] temp = deriveDelayMultapliers(getFullPointSet(numPoints));
	for (int i = 0; i <temp.length; i++) {
		delays.add(temp[i]);
	}
	index = 0;
	return temp;
}

/**Uses the next delay on the line.
 * @return if there are more delays on the line.*/
public boolean useNextDelay() {
	if (index == delays.size()) {
		index = 0;
		return false;
	}
	robot.delay((int)(UserInterface.BASE_DELAY*delays.get(index)));
	index++;
	return true;
}
}
