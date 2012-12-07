package main;

import java.util.ArrayList;
import java.util.Random;

public class NumberCycle {

	private static ArrayList<Integer> numbers;
	private static int currentIndex;

	private Random rand;

	public NumberCycle() {
		rand = new Random();
		NumberCycle.numbers = new ArrayList<Integer>();
		NumberCycle.currentIndex = 0;
	}

	/**
	 * public void setRange (int minimum, int maximum)
	 * <p>
	 * 
	 * Sets the range to be used for the number set.
	 * 
	 * @param minimum
	 *            the minimum value.
	 * @param maximum
	 *            the maximum value.
	 */
	public void setRange(int minimum, int maximum) {
		NumberCycle.numbers.clear();
		for (int i = minimum; i <= maximum; i++) {
			NumberCycle.numbers.add(i);
		}
		NumberCycle.currentIndex = 0;
		NumberCycle.numbers.trimToSize();
	}

	/**
	 * public int getCurrent()
	 * <p>
	 * 
	 * Returns the current value.
	 * 
	 * @return the current value attached to the index.
	 */
	public int getCurrent() {
		return NumberCycle.numbers.get(NumberCycle.currentIndex);
	}

	/**
	 * public void shiftIndex()
	 * <p>
	 * Shifts the index by one.
	 */
	public void shiftIndex() {
		NumberCycle.currentIndex = this.rand
				.nextInt(NumberCycle.numbers.size());
	}
}
