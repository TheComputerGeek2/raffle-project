package main;

import java.util.Random;

public class NumberGenerator {

	private Random rand;

	private int maximum;

	private int minimum;

	private String setValues = "";

	private int currentValue;

	private int maxLength;

	private int range;

	public NumberGenerator() {
		this.rand = new Random();
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	// TODO determine when to call this method
	/**
	 * public void extractDigit ()
	 * <p>
	 * Locks a digit from the current generated value.
	 */
	public void extractDigit() {
		System.out.println("Set Values = " + this.setValues);
		this.setValues += formatValue().charAt(this.setValues.length());
		System.out.println("Set values = " + this.setValues);
		updateMaxValue();
	}

	private void updateMaxValue() {
		int unlockedValues = this.maxLength - this.setValues.length() + 1;
		int lastLocked = Integer.parseInt(this.setValues.charAt(this.setValues
				.length() - 1) + "");
		this.range = (int) (this.range - (lastLocked * (Math.pow(10,
				unlockedValues - 1))));

	}

	/**
	 * public {@linkString} getCurrent ()
	 * <p>
	 * 
	 * Returns the current value to be displayed. This reflects the locked
	 * values and the remaining digits.
	 * 
	 * @return a String containing the locked values and the remaining digits.
	 */
	public String getCurrent() {
		return this.setValues + getRemainingDigits();
	}

	private String getRemainingDigits() {
		System.out.println("Current value is " + this.currentValue);
		System.out.println("Formatted value is " + formatValue());
		String temp = formatValue().substring(this.setValues.length());
		System.out.println("Formatted remaining digits = " + temp);
		return temp;
		// return Integer.parseInt(temp);
	}

	private String formatValue() {
		String temp = "" + this.currentValue;
		while (temp.length() < this.maxLength) {
			temp = "0" + temp;
		}
		return temp;

	}

	// FIXME fix the number generation THE SPECIFIED RANGE IS NOT HONORED

	// TODO implement the useage of this method after verifying the range
	// TODO check that this can generate ALL values in the range
	/**
	 * public void generateNewValue ()
	 * <p>
	 * Generates a new value to use in the system.
	 */
	public void generateNewValue() {
		System.out.println("Random's max = " + (this.range));
		System.out.println("Max value = " + this.maximum);
		System.out.println("Minimum value = " + this.minimum);
		int temp = this.rand.nextInt(this.range);
		System.out.println("Generated value = " + temp);
		this.currentValue = temp + this.minimum;
	}
	
	/**
	 * public void setRange (int minimum, int maximum)
	 * <p>
	 * 
	 * Tells the generator what the upper and lower extremes should be for the
	 * values generated.
	 * 
	 * @param minimum
	 *            the minimum value to generate.
	 * @param maximum
	 *            the maximum value to generate.
	 */
	public void setRange(int minimum, int maximum) {
		System.out.println("Range set to " + minimum + " to " + maximum);
		this.minimum = minimum;
		this.maximum = maximum;
		this.range = this.maximum - this.minimum + 1;
		this.maxLength = (maximum + "").length();
		this.setValues = "";
		this.currentValue = 0;
	}
}
