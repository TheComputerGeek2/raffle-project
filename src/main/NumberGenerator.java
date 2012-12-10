package main;

import java.util.Random;

public class NumberGenerator {

	private Random rand;

	private int maximum;

	private int minimum;

	private String setValues = "";

	private int currentValue;

	private int maxLength;

	public NumberGenerator() {
		this.rand = new Random();
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	// TODO determine when to call this method
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
		this.maximum = (int) (this.maximum - (lastLocked * (Math.pow(10,
				unlockedValues - 1))));
	}

	public String getCurrent() {
		return this.setValues + getRemainingDigits();
	}

	private String getRemainingDigits() {
		String temp = formatValue().substring(this.setValues.length());
		return temp;
	//	return Integer.parseInt(temp);
	}

	private String formatValue() {
		String temp = "" + this.currentValue;
		while (temp.length() < this.maxLength) {
			temp = "0" + temp;
		}
		return temp;

	}

	// TODO implement the useage of this method after verifying the range
	// TODO check that this can generate ALL values in the range
	public void generateNewValue() {
		this.currentValue = this.rand.nextInt(this.maximum - this.minimum + 1)
				+ this.minimum;
	}

	public void setRange(int minimum, int maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.maxLength = (maximum + "").length();
		this.setValues = "";
		this.currentValue = 0;
	}
}
