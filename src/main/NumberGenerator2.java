package main;

import java.util.Random;

public class NumberGenerator2 {

	private Random rand;

	private int minimum;

	private int maximum;

	private int finalValue;

	private String currentValue;

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
		System.out.println("Final value selected: " + this.finalValue);
	}

	/*
	 * Generates the random values for each digit and returns them as a string
	 * only for the numbers that will not be locked this time
	 */
	public String generateValues() {
		String temp = "";
		for (int i = 1; i <= this.maxLength - this.lockedValues.length(); i++) {
			temp += this.rand.nextInt(10);
		}
		this.currentValue = temp;
		this.valuesGenerated++;
		System.out.println("Values generated: " + temp);
		return temp;
	}

	public String getCurrent() {
		return this.lockedValues + this.currentValue;
	}

	public int getNumValueGenerated() {
		return this.valuesGenerated;
	}

	public void lockNextValueDigit() {
		String temp = String.valueOf(this.finalValue);
		this.lockedValues += temp.charAt(this.lockedValues.length());
		generateValues();
	}

	public int getMaxLength() {
		return this.maxLength;
	}
	
	public String getFinalValue() {
		return String.valueOf(this.finalValue);
	}
}
