package numbers;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class AsynchronousSlowdown {
	
	@SuppressWarnings("unused")
	private int minimumValue;
	@SuppressWarnings("unused")
	private int maximumValue;
	
	@SuppressWarnings("unused")
	private int remainingMaxValue;
	@SuppressWarnings("unused")
	private int numbersFrozen;
	
	@SuppressWarnings("unused")
	private String frozenDigits;
	private int maxLength;
	
	@SuppressWarnings("unused")
	private Random rand;
	
	private JLabel output;
	
	private ArrayList<Integer> nums;
	
	private Toolkit tk;

public AsynchronousSlowdown(JLabel output) {
	this.output = output;
	nums = new ArrayList<Integer>();
	tk = Toolkit.getDefaultToolkit();
}

/**
 * Sets the maximum and minimum values that the generator will finish on.
 * @param minimum the minimum value to display.
 * @param maximum the maximum value to display.*/
public void setRange(int minimum, int maximum) {
	minimumValue = minimum;
	maximumValue = maximum;
	maxLength = ("" + maximum).length();
	for (int i = 0; i < maxLength; i++) {
		nums.add(0);
	}
}

/**Creates a string containing the values stored in the array list of integers provided.
 * @param strings the arraylist of integers.
 * @return the resulting String.*/
public String arrayListToString(ArrayList<Integer> strings) {
	synchronized (this) {
		String temp = "";
		for (int i = 0; i < strings.size(); i++) {
			temp += strings.get(i) + ""; //FIXME fix the to string system
		}
		return temp;
	}
}

protected void pushValue(SingleNumberSlowdown sns) {
	synchronized (this) {
		pushIntoList(sns);
		pushValue(arrayListToString(nums));
	}
}

protected void pushIntoList(SingleNumberSlowdown sns) {
	synchronized (this) {
		//TODO get the position and push the value into that position
	}
}

/**Pushes the provided String onto the output display and updates the display.
 * @param value the content to push onto the field.*/
public void pushValue(String value) {
	output.setText(value);
	output.paintImmediately(output.getParent().getBounds());
	tk.sync();
}

/**A method to be used by the single number slowdown object to determine their new upper limits.
 * @param position the position number of the single number slowdown.
 * @return the maximum value for that position allowed at the current time.*/
protected int determineMaxForPosition(int position) {
	//TODO determine the max possible value for the position specified
	return 0;
}
}
