package main;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class InputFilter implements KeyListener {

	public static final String VALID_DECIMAL_INPUTS = Messages
			.getString("InputFilter.0"); //$NON-NLS-1$

	public static final String VALID_HEXIDECIMAL_INPUTS = Messages
			.getString("InputFilter.1"); //$NON-NLS-1$

	public static final int MAX_INPUT_LENGTH = 10;

	private JTextField source;

	public static enum NumberSystem {
		DECIMAL, HEX
	}

	@SuppressWarnings("unused")
	private NumberSystem currentSystem;

	public InputFilter(JTextField source) {
		this.source = source;
		currentSystem = NumberSystem.DECIMAL;
	}

	public String getValidInputs() {
		return VALID_DECIMAL_INPUTS;
	}

	public int getMaxInputLength() {
		return MAX_INPUT_LENGTH;
	}

	/**
	 * The response for key events.
	 * 
	 * @param arg0
	 *            the key event to respond to.
	 */
	private void response(KeyEvent arg0) {
		enforceValidInputCharacters(source.getText(), VALID_DECIMAL_INPUTS);
		enforceLength(source.getText(), MAX_INPUT_LENGTH);
	}

	/**
	 * Enforces the use of valid input characters.
	 * 
	 * @param input
	 *            the input to check.
	 * @param validInputs
	 *            the characters to allow.
	 * @throws AWTError
	 *             if a toolkit could not be found, or if one could not be
	 *             accessed or instantiated.
	 */
	private void enforceValidInputCharacters(String input, String validInputs) {
		for (int i = 0; i < input.length(); i++) {
			if (!checkCharacter(
					input.charAt(i) + Messages.getString("InputFilter.2"), validInputs)) { //$NON-NLS-1$
				source.setText(input.substring(0, i));
				source.paintImmediately(source.getBounds());
				InputFilter.beep();
				return;
			}
		}
	}

	/**
	 * Enforces a maximum input length.
	 * 
	 * @param input
	 *            the input text.
	 * @param maxLength
	 *            the maximum length to allow.
	 * @throws AWTError
	 *             if a toolkit could not be found, or if one could not be
	 *             accessed or instantiated.
	 */
	private void enforceLength(String input, int maxLength) {
		if (input.length() > maxLength) {
			source.setText(input.substring(0, maxLength));
			source.paintImmediately(source.getBounds());
			InputFilter.beep();
		}
	}

	/**
	 * Calls the sync and beep methods from the default toolkit.
	 * 
	 * @throws AWTError
	 *             if a toolkit could not be found, or if one could not be
	 *             accessed or instantiated.
	 */
	private static void beep() {
		Toolkit.getDefaultToolkit().sync();
		Toolkit.getDefaultToolkit().beep();
	}

	/**
	 * Checks to see if the specified character is valid for input.
	 * 
	 * @param text
	 *            the character to check in the form of a string.
	 * @param validInputs
	 *            a string containing all of the valid inputs.
	 * @return if the input is valid.
	 */
	private static boolean checkCharacter(String text, String validInputs) {
		for (int i = 0; i < validInputs.length(); i++) {
			if (text.contains(validInputs.charAt(i)
					+ Messages.getString("InputFilter.3"))) { //$NON-NLS-1$
				return true;
			}
		}
		return false;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		response(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		response(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
