package main;

import interfaces.Picker;

import java.awt.AWTError;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.Timer;

public class ThreePanelPicker implements Picker, ActionListener, Serializable {

	// TODO determine if this class needs to run on its own thread

	/**
	 * 
	 */
	private static final long serialVersionUID = 5269471983577430158L;
	private JLabel out1;
	private JLabel out2;
	private JLabel out3;

	private String[] holders = {
			Messages.getString("ThreePanelPicker.0"), Messages.getString("ThreePanelPicker.1") }; //$NON-NLS-1$ //$NON-NLS-2$

	private NumberGenerator2 nc;

	/** The amount by which the multiplier increases each round. */
	public final double ROUND_MULTAPLIER_INCREASE = 0.1;

	public final int MAX_NUMBERS = 100;

	private Timer timer;

	private final int TIMER_DELAY = 300;

	private boolean isGold = true;

	private final int NUMBERS = 100;

	private Curves c;

	private BorderManager[] managers;

	/**
	 * public ThreePanelPicker ({@link JLabel} output1, {@link JLabel} output2,
	 * {@link JLabel} output3) throws {@link AWTException}
	 * <p>
	 * 
	 * Manages the output display components.
	 * 
	 * @param output1
	 *            the top output component.
	 * @param output2
	 *            the middle output component.
	 * @param output3
	 *            the bottom output component.
	 * @throws AWTException
	 *             thrown by the Curves object.
	 */
	public ThreePanelPicker(JLabel output1, JLabel output2, JLabel output3)
			throws AWTException {
		this.out1 = output1;
		this.out2 = output2;
		this.out3 = output3;
		this.nc = new NumberGenerator2();
		this.timer = new Timer(this.TIMER_DELAY, this);
		this.c = new Curves();
	}

	@Override
	public void setValues(int minimum, int maximum) {
		this.nc.setRange(minimum, maximum);
	}

	/**
	 * private void pushNextValue()
	 * <p>
	 * Pushes the next value into the system and prepares the number cycle
	 * object for the next one.
	 */
	private void pushNextValue() {
		pushValue(this.nc.getCurrent()
				+ Messages.getString("ThreePanelPicker.2")); //$NON-NLS-1$
		this.nc.generateValues();
	}

	/**
	 * private void pushValue ({@link String} value)
	 * <p>
	 * Pushes the text into the top panel, shifting the existing content down a
	 * level.
	 * 
	 * @param value
	 *            the text to push into the system.
	 * @throws AWTError
	 *             if a toolkit could not be found, or if one could not be
	 *             accessed or instantiated.
	 */
	private void pushValue(String value) {
		this.holders[1] = this.out2.getText();
		this.out2.setText(this.holders[0]);
		this.holders[0] = value;
		this.out1.paintImmediately(this.out1.getParent().getBounds());
		updateImagePanel(this.out2);
		this.out2.paintImmediately(this.out2.getParent().getBounds());
		this.out3.paintImmediately(this.out3.getParent().getBounds());
		updateText();
		try {
			Toolkit.getDefaultToolkit().sync();
		} catch (AWTError e) {
			Logger.log(e);
			throw e;
		}
	}

	private void updateText() {
		this.out1.paintImmediately(this.out1.getBounds());
		this.out2.paintImmediately(this.out2.getBounds());
		this.out3.paintImmediately(this.out3.getBounds());
	}

	@SuppressWarnings("static-method")
	private void updateImagePanel(JLabel label) {
		if (label instanceof ImagePanel) {
			((ImagePanel) label).update();
		}
	}

	/**
	 * public void setBorderManagers ({@link BorderManager}[] managers)
	 * <p>
	 * Specifies what BorderManagers to use for the output display.
	 * 
	 * @param managers
	 *            the border managers to use.
	 */
	public void setBorderManagers(BorderManager[] managers) {
		this.managers = managers;
	}

	@Override
	public void run() {
		reset();
		// setBorderVisibility(new int[] { 1 }, true);

		if (this.out2 instanceof ImagePanel) {
			((ImagePanel) this.out2).setImageDisplayed(true);
		}
		this.nc.generateFinalValue();
		boolean temp = true;
		this.c.getDelayMultapliers(this.NUMBERS);
		double delayMultaplier = 1.0;
		while (temp) {
			pushNextValue();
			temp = this.c.useNextDelay(delayMultaplier);
			if ((this.c.remainingDelays() - this.nc.getMaxLength()) < 0) {
				this.nc.lockNextValueDigit();
				delayMultaplier = 1.0;
				if (temp) {
					for (int i = 0; i < 10; i++) {
						pushNextValue();
						this.c.useDelay();
					}
				}
			}
		}
		pushValue(this.nc.getFinalValue());
		winning();
	}

	/**
	 * private void winning()
	 * <p>
	 * Starts the winning sequence.
	 */
	private void winning() {
		if (this.out2 instanceof ImagePanel) {
			((ImagePanel) this.out2).setImageDisplayed(false);
		}
		this.out1.setText(Messages.getString("ThreePanelPicker.3")); //$NON-NLS-1$
		this.out3.setText(Messages.getString("ThreePanelPicker.4")); //$NON-NLS-1$
		this.timer.start();
	}

	/**
	 * private void toggleColor()
	 * <p>
	 * Toggles the color of the top and bottom output JLabels.
	 */
	private void toggleColor() {
		Color temp;
		if (this.isGold) {
			temp = UserInterface.GREEN;
		} else {
			temp = UserInterface.GOLD;
		}
		this.out1.setBackground(temp);
		this.out3.setBackground(temp);
		this.out1.paintImmediately(this.out1.getBounds());
		this.out3.paintImmediately(this.out3.getBounds());
		this.isGold = !this.isGold;
	}

	/**
	 * public void reset()
	 * <p>
	 * Resets the system in preparation for the next usage.
	 * 
	 * @throws AWTError
	 *             if a toolkit could not be found, or if one could not be
	 *             accessed or instantiated.
	 */
	@Override
	public void reset() {
		stopFlashing();
		this.out1.setText(Messages.getString("ThreePanelPicker.5")); //$NON-NLS-1$
		this.out2.setText(Messages.getString("ThreePanelPicker.6")); //$NON-NLS-1$
		this.out3.setText(Messages.getString("ThreePanelPicker.7")); //$NON-NLS-1$
		this.out1.paintImmediately(this.out1.getBounds());
		this.out2.paintImmediately(this.out2.getBounds());
		this.out3.paintImmediately(this.out3.getBounds());
		try {
			Toolkit.getDefaultToolkit().sync();
		} catch (AWTError e) {
			Logger.log(e);
			throw e;
		}
	}

	/**
	 * private void setBorderVisibility(int[] indexes, boolean visible)
	 * <p>
	 * 
	 * Sets the visibility of the orders at indexes to visible.
	 * 
	 * @param indexes
	 *            the indexes of the border managers
	 * @param visible
	 *            the visibility status to apply
	 * 
	 * @throws AWTError
	 *             thrown by the updateBorder method in the BorderManager
	 */
	private void setBorderVisibility(int[] indexes, boolean visible) {
		for (int i : indexes) {
			this.managers[i].setBorderVivibility(visible);
			this.managers[i].updateBorder();
		}
	}

	/**
	 * public void stopFlashing()
	 * <p>
	 * Stops the flashing of the display.
	 */
	public void stopFlashing() {
		setBorderVisibility(new int[] { 0, 1, 2 }, false);
		this.timer.stop();
		if (!this.isGold) {
			toggleColor();
		}
	}

	@Override
	public void inputProblem() {
		reset();
		this.out1.setText(Messages.getString("ThreePanelPicker.8")); //$NON-NLS-1$
		this.out2.setText(Messages.getString("ThreePanelPicker.9")); //$NON-NLS-1$
		this.out3.setText(Messages.getString("ThreePanelPicker.10")); //$NON-NLS-1$
		this.out1.paintImmediately(this.out1.getBounds());
		this.out2.paintImmediately(this.out2.getBounds());
		this.out3.paintImmediately(this.out3.getBounds());
		Toolkit.getDefaultToolkit().beep();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		toggleColor();
	}
}
