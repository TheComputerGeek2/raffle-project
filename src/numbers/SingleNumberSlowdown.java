package numbers;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JLabel;

import main.NumberCycle;

public class SingleNumberSlowdown {
	
	@SuppressWarnings("unused")
	private int max;
	@SuppressWarnings("unused")
	private int min;
	@SuppressWarnings("unused")
	private int position;
	
	@SuppressWarnings("unused")
	private JLabel output;
	
	@SuppressWarnings("unused")
	private int maxAvailable;
	
	@SuppressWarnings("unused")
	private Random rand;
	
	@SuppressWarnings("unused")
	private Toolkit tk;
	
	@SuppressWarnings("unused")
	private AsynchronousSlowdown as;
	
	@SuppressWarnings("unused")
	private int currentValue;
	
	private NumberCycle nc;
	
	public SingleNumberSlowdown(int max, int min, int position,
			JLabel output, AsynchronousSlowdown as) {
		this.output = output;
		this.max = max;
		this.min = min;
		this.position = position;
		this.tk = Toolkit.getDefaultToolkit();
		this.as = as;
		nc = new NumberCycle();
		nc.setRange(0, 9);
	}
	
	public void setMaxAvailable(int maxAvailable) {
		this.maxAvailable = maxAvailable;
	}
	
	public void slowDown() {
		
	}
	
	public int getValue() {
		return nc.getCurrent();
	}
	
	public void pushValue() {
		
	}
	
	
}