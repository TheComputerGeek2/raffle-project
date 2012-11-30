package main;

import java.awt.AWTError;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.JTextField;

public class InputFilter implements KeyListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -893362519469923868L;

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
		this.currentSystem = NumberSystem.DECIMAL;
	}

	public String getValidInputs() {
		return InputFilter.VALID_DECIMAL_INPUTS;
	}

	public int getMaxInputLength() {
		return InputFilter.MAX_INPUT_LENGTH;
	}

	/**
	 * private void response (@link KeyEvent} arg0)
	 * <p>
	 * 
	 * The response for key events.
	 * 
	 * @param arg0
	 *            the key event to respond to.
	 */
	private void response(KeyEvent arg0) {
		enforceValidInputCharacters(this.source.getText(),
				InputFilter.VALID_DECIMAL_INPUTS);
		enforceLength(this.source.getText(), InputFilter.MAX_INPUT_LENGTH);
	}

	/**
	 * public void enforceValidInputCharacters ({@link String} input,
	 * {@link String} validInputs)
	 * <p>
	 * 
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
				this.source.setText(input.substring(0, i));
				this.source.paintImmediately(this.source.getBounds());
				InputFilter.beep();
				return;
			}
		}
	}

	/**
	 * private void enforceLength ({@link String} input, int maxLength)
	 * <p>
	 * 
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
			this.source.setText(input.substring(0, maxLength));
			this.source.paintImmediately(this.source.getBounds());
			InputFilter.beep();
		}
	}

	/**
	 * private static void beep()
	 * <p>
	 * 
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
	 * private static boolean checkCharacters ({@link String} text,
	 * {@link String} validInputs)
	 * <p>
	 * 
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
	public void keyTyped(KeyEvent arg0) { /* Required by the KeyListener interface */
	}
}
