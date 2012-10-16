package main;

import interfaces.Picker;

import java.awt.AWTError;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class ThreePanelPicker implements Picker, ActionListener {
	
	private JLabel out1;
	private JLabel out2;
	private JLabel out3;
	
	private String[] holders = {Messages.getString("ThreePanelPicker.0"), Messages.getString("ThreePanelPicker.1")}; //$NON-NLS-1$ //$NON-NLS-2$
	
	private NumberCycle nc;
	
	/**The amount by which the multiplier increases each round.*/
	public final double ROUND_MULTAPLIER_INCREASE = 0.1;
	
	public final int MAX_NUMBERS = 100;
		
	private Timer timer;
	
	private final int TIMER_DELAY = 300;
		
	private boolean isGold = true;
		
	private final int NUMBERS = 100;
	
	private Curves c;

/**Manages the output display components.
 * @param output1 the top output component.
 * @param output2 the middle output component.
 * @param output3 the bottom output component.
 * @throws AWTException thrown by the Curves object.*/
public ThreePanelPicker(JLabel output1, JLabel output2,
		JLabel output3) throws AWTException {
	out1 = output1;
	out2 = output2;
	out3 = output3;
	nc = new NumberCycle();
	timer = new Timer(TIMER_DELAY, this);
	c = new Curves();
}

@Override
public void setValues(int minimum, int maximum) {
	nc.setRange(minimum, maximum);
}

/**Pushes the next value into the system and prepares the
 * number cycle object for the next one.*/
private void pushNextValue() {
	pushValue(nc.getCurrent() + Messages.getString("ThreePanelPicker.2")); //$NON-NLS-1$
	nc.shiftIndex();
}

/**Pushes the text into the top panel, shifting the
 * existing content down a level.
 * @param value the text to push into the system.
 * @throws AWTError if a toolkit could not be found, or if
 * one could not be accessed or instantiated.*/
private void pushValue(String value) {
	holders[1] = out2.getText();
	out2.setText(holders[0]);
	holders[0] = value;
	out1.paintImmediately(out1.getParent().getBounds());
	out2.paintImmediately(out2.getParent().getBounds());
	out3.paintImmediately(out3.getParent().getBounds());
	Toolkit.getDefaultToolkit().sync();
}

@Override
public void run() {
	reset();
	boolean temp = true;
	c.getDelayMultapliers(NUMBERS);
	while (temp) {
		pushNextValue();
		temp = c.useNextDelay();
	}
	winning();
}

/**Starts the winning sequence.*/
private void winning() {
	out1.setText(Messages.getString("ThreePanelPicker.3")); //$NON-NLS-1$
	out3.setText(Messages.getString("ThreePanelPicker.4")); //$NON-NLS-1$
	timer.start();
}

/**Toggles the color of the top and bottom output JLabels.*/
private void toggleColor() {
	Color temp;
	if (isGold) {
		temp = UserInterface.GREEN;
	} else {
		temp = UserInterface.GOLD;
	}
	out1.setBackground(temp);
	out3.setBackground(temp);
	out1.paintImmediately(out1.getBounds());
	out3.paintImmediately(out3.getBounds());
	isGold = !isGold;
}

/**Resets the system in preparation for the next usage.
 * @throws AWTError if a toolkit could not be found, or if
 * one could not be accessed or instantiated.*/
public void reset() {
	timer.stop();
	if (!isGold) {
		toggleColor();
	}
	out1.setText(Messages.getString("ThreePanelPicker.5")); //$NON-NLS-1$
	out2.setText(Messages.getString("ThreePanelPicker.6")); //$NON-NLS-1$
	out3.setText(Messages.getString("ThreePanelPicker.7")); //$NON-NLS-1$
	out1.paintImmediately(out1.getBounds());
	out2.paintImmediately(out2.getBounds());
	out3.paintImmediately(out3.getBounds());
	Toolkit.getDefaultToolkit().sync();
}

public void inputProblem() {
	reset();
	out1.setText(Messages.getString("ThreePanelPicker.8")); //$NON-NLS-1$
	out2.setText(Messages.getString("ThreePanelPicker.9")); //$NON-NLS-1$
	out3.setText(Messages.getString("ThreePanelPicker.10")); //$NON-NLS-1$
	out1.paintImmediately(out1.getBounds());
	out2.paintImmediately(out2.getBounds());
	out3.paintImmediately(out3.getBounds());
	Toolkit.getDefaultToolkit().beep();
}

@Override
public void actionPerformed(ActionEvent arg0) {
	toggleColor();
}
}
