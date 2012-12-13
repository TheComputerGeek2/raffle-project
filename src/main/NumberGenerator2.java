package main;

import java.util.Random;

public class NumberGenerator2 {

	private Random rand;

	private int minimum;

	private int maximum;

	private int finalValue;

	private int currentValue;

	public static final int INITIAL_VALUES = 50;

	public static final int STEP_VALUES = 20;

	private String lockedValues = "";

	private int maxLength;

	private int valuesGenerated;

	public NumberGenerator2() {
		this.rand = new Random();
		this.valuesGenerated = 0;
	}

	public void setRange(int minimum, int maximum) {
		this.lockedValues = "";
		this.minimum = minimum;
		this.maximum = maximum;
		this.maxLength = String.valueOf(this.maximum).length();
		this.valuesGenerated = 0;
	}

	public void generateFinalValue() {
		this.finalValue = this.rand.nextInt(this.maximum - this.minimum + 1)
				+ this.minimum;
	}

	/*
	 * Generates the random values for each digit and returns them as a string
	 * only for the numbers that will not be locked this time
	 */
	private String generateValues() {
		String temp = "";
		for (int i = 1; i <= this.maxLength - this.lockedValues.length(); i++) {
			temp += this.rand.nextInt(10);
		}
		this.valuesGenerated++;
		return temp;
	}

}
