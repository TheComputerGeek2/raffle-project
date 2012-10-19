package main;

import java.util.ArrayList;
import java.util.Random;

public class NumberCycle {

	private static ArrayList<Integer> numbers;
	private static int currentIndex;

	private Random rand;

	public NumberCycle() {
		rand = new Random();
		numbers = new ArrayList<Integer>();
		currentIndex = 0;
	}

	/**
	 * Sets the range to be used for the number set.
	 * 
	 * @param minimum
	 *            the minimum value.
	 * @param maximum
	 *            the maximum value.
	 */
	public void setRange(int minimum, int maximum) {
		numbers.clear();
		for (int i = minimum; i <= maximum; i++) {
			numbers.add(i);
		}
		currentIndex = 0;
		numbers.trimToSize();
	}

	/**
	 * Returns the current value.
	 * 
	 * @return the current value attached to the index.
	 */
	public int getCurrent() {
		return numbers.get(currentIndex);
	}

	/** Shifts the index by one. */
	public void shiftIndex() {
		currentIndex = rand.nextInt(numbers.size());
	}
}
