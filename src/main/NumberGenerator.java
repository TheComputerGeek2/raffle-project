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
	rand = new Random();
}

private void extractDigit() {
	setValues += formatValue().charAt(setValues.length());
	//TODO subtract the value locked from the max
	//TODO update the maximum
}

public String getCurrent() {
	return this.setValues + getRemainingDigits();
}

private int getRemainingDigits() {
	//TODO determine what digits remain valid
	return 0;
}

private String formatValue() {
	String temp = "" + currentValue;
	while (temp.length() < this.maxLength) {
		temp = "0" + temp;
	}
	return temp;
	
}

public void reset() {
	this.setValues = "";
	this.maxLength = ("" + maximum).length();
}

//TODO check that this can generate ALL values in the range
private void generateNewValue() {
	this.currentValue = rand.nextInt(this.maximum-this.minimum+1) + this.minimum;
}

private void setMaximum(int maximum) {
	this.maximum = maximum;
}

public void setRange(int minimum, int maximum) {
	this.minimum = minimum;
	this.maximum = maximum;
}
}
