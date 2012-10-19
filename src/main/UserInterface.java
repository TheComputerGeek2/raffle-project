package main;

import interfaces.Picker;

import java.awt.AWTError;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UserInterface implements KeyListener, MouseListener {

	/** The frame of the object. */
	public Container frame;

	public static final String TITLE = Messages.getString("UserInterface.0"); //$NON-NLS-1$

	/**
	 * The maximum number of extra rounds beyond the base rounds.
	 */
	public static final int MAX_ROUNDS = 10;

	/** The number of base rounds. */
	public static final int BASE_ROUNDS = 20;

	/** The base delay in between numbers. */
	public static final int DELAY = 30;

	/** The width of the frame. */
	public static final int BASE_WIDTH = 300;

	/** The height of the frame. */
	public static final int BASE_HEIGHT = 400;

	/** The gold color used for the graphics. */
	public static final Color GOLD = Color.ORANGE;// new Color(255, 204, 102);

	/** The green color used for the graphics. */
	public static final Color GREEN = new Color(23, 172, 23); // original was 13
																// 172 43

	/**
	 * The DimensionsProcessor used to determine what size to make the frame and
	 * fonts.
	 */
	public static final DimensionsProcessor dp = new DimensionsProcessor(
			BASE_WIDTH, BASE_HEIGHT);

	/** The JTextField that accepts the minimum output value. */
	private JTextField minimumValueInput;

	/** The JTextField that accepts the maximum output value. */
	private JTextField maximumValueInput;

	/** The minimum output value. */
	private int minimumValue;

	/** The maximum output value. */
	private int maximumValue;

	/** The output component. */
	private JLabel[] outputs = new JLabel[3];

	/** The button that starts the number picking sequence. */
	private JButton pickNumber;

	public static final int BASE_DELAY = 50;

	/** The font for the input components. */
	protected static final Font INPUT_FONT = new Font(Font.SANS_SERIF,
			Font.BOLD, dp.scaleValue(24));

	/** The font used for the output label. */
	protected static final Font OUTPUT_FONT = new Font(Font.SANS_SERIF,
			Font.BOLD, dp.scaleValue(50));

	/** The object that picks the numbers and displays them. */
	private Picker np;

	/**
	 * Instantiates a new user interface of the raffle program.
	 * 
	 * @throws AWTException
	 *             an exception thrown by the ThreePanelPicker. Or if a toolkit
	 *             could not be found, or if one could not be accessed or
	 *             instantiated.
	 */
	public UserInterface() throws AWTException {
		this(new JFrame());
	}

	/**
	 * Instantiates a new user interface of the raffle program.
	 * 
	 * @param frame
	 *            the JFrame to use.
	 * @throws AWTException
	 *             an exception thrown by the ThreePanelPicker. Or if a toolkit
	 *             could not be found, or if one could not be accessed or
	 *             instantiated.
	 */
	public UserInterface(JFrame frame) throws AWTException {
		makeFrame(frame);
		init();
	}

	public UserInterface(JApplet applet) throws AWTException {
		makeFrame(applet);
		init();
	}

	private void init() throws AWTException {
		addComponents();
		centerFrame(frame);
		frame.setBackground(GREEN);
		np = new ThreePanelPicker(outputs[0], outputs[1], outputs[2]);
		frame.setVisible(true);
	}

	/**
	 * Centers the frame on the screen.
	 * 
	 * @param frame
	 *            the frame to be centered.
	 * @return the point of the top left corner of the frame.
	 * @throws AWTError
	 *             if a toolkit could not be found, or if one could not be
	 *             accessed or instantiated.
	 */
	private static Point centerFrame(Container frame) {
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = ScreenSize.width / 2 - frame.getWidth() / 2;
		int newY = ScreenSize.height / 2 - frame.getHeight() / 2;
		frame.setLocation(newX, newY);
		return new Point(newX, newY);
	}

	/**
	 * Makes the frame of the object.
	 * 
	 * @return the frame of the object.
	 */
	private Container makeFrame(Container frame) {
		frame.setSize(new Dimension(dp.scaleValue(BASE_WIDTH), dp
				.scaleValue(BASE_HEIGHT)));
		if (frame instanceof JFrame) {
			((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		frame.setLayout(new GridLayout(5, 1));
		if (frame instanceof JFrame) {
			((JFrame) frame).setTitle(TITLE);
		} else if (frame instanceof JApplet) {
			((JApplet) frame).setName(TITLE);
		}
		this.frame = frame;
		return frame;
	}

	/** Adds the components to the frame. */
	private void addComponents() {
		addInputs();
		addButton();
		addOutputs();
	}

	/** Adds the input components to the frame. */
	private void addInputs() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 2));
		JLabel label1 = new JLabel();
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(INPUT_FONT);
		label1.setText(Messages.getString("UserInterface.1")); //$NON-NLS-1$
		label1.setOpaque(true);
		label1.setBackground(GREEN);
		panel1.add(label1);
		minimumValueInput = new JTextField();
		minimumValueInput.setHorizontalAlignment(SwingConstants.CENTER);
		minimumValueInput.setFont(INPUT_FONT);
		minimumValueInput.addKeyListener(this);
		KeyListener minVerifier = new InputFilter(minimumValueInput);
		minimumValueInput.addKeyListener(minVerifier);
		panel1.add(minimumValueInput);
		panel.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 2));
		JLabel label2 = new JLabel();
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(INPUT_FONT);
		label2.setText(Messages.getString("UserInterface.2")); //$NON-NLS-1$
		label2.setOpaque(true);
		label2.setBackground(GREEN);
		panel2.add(label2);
		maximumValueInput = new JTextField();
		maximumValueInput.setHorizontalAlignment(SwingConstants.CENTER);
		maximumValueInput.setFont(INPUT_FONT);
		maximumValueInput.addKeyListener(this);
		KeyListener maxVerifier = new InputFilter(maximumValueInput);
		maximumValueInput.addKeyListener(maxVerifier);
		panel2.add(maximumValueInput);
		panel.add(panel2);
		frame.add(panel);
	}

	/**
	 * Adds the output components to the frame.
	 * 
	 * @return an array of JLabels containing the output components.
	 */
	private JLabel[] addOutputs() {
		for (int i = 0; i < 3; i++) {
			outputs[i] = new JLabel();
			frame.add(outputs[i]);
			outputs[i].setBackground(UserInterface.GOLD);
			outputs[i].setHorizontalAlignment(SwingConstants.CENTER);
			outputs[i].setFont(UserInterface.OUTPUT_FONT);
			outputs[i].setOpaque(true);
		}
		return outputs;
	}

	/**
	 * Adds the 'go' button to the frame.
	 * 
	 * @return the button.
	 */
	private JButton addButton() {
		pickNumber = new JButton();
		pickNumber.addKeyListener(this);
		pickNumber.addMouseListener(this);
		pickNumber.setFont(INPUT_FONT);
		pickNumber.setText(Messages.getString("UserInterface.3")); //$NON-NLS-1$
		frame.add(pickNumber);
		return pickNumber;
	}

	/**
	 * The responses for the events.
	 * 
	 * @param arg0
	 *            the source of the event.
	 */
	private void response(Object arg0, Integer keyCode) {
		if (arg0.equals(pickNumber)) {
			if (EventFilter.buttonEventFilter(keyCode)) {
				pickingSequence();
			}
		} else if (KeyEvent.getKeyText(keyCode).equalsIgnoreCase(
				Messages.getString("UserInterface.4"))) { //$NON-NLS-1$
			shiftFocus(arg0);
		}
	}

	/**
	 * Shifts the focus from one component to another.
	 * 
	 * @param arg0
	 *            the source of the event that triggered the response method.
	 */
	private void shiftFocus(Object arg0) {
		if (arg0.equals(minimumValueInput)) {
			maximumValueInput.grabFocus();
		} else if (arg0.equals(maximumValueInput)) {
			pickNumber.grabFocus();
			pickingSequence();
		}
	}

	/**
	 * The sequence for picking the numbers.
	 * 
	 * @throws AWTError
	 *             if a toolkit could not be found, or if one could not be
	 *             accessed or instantiated.
	 */
	private void pickingSequence() {
		getInputs();
		if (getInputs() && verifyValidInputType() && verifyValidRange()
				&& verifyValidValues()) {
			np.setValues(minimumValue, maximumValue);
			np.run();
		} else {
			np.inputProblem();
			frame.repaint();
			Toolkit.getDefaultToolkit().sync();
		}
	}

	/**
	 * Pushes the new value into the system.
	 * 
	 * @param out1
	 *            the top output component.
	 * @param out2
	 *            the middle output component.
	 * @param out3
	 *            the bottom output component.
	 * @param value
	 *            the value to introduce to the system.
	 */
	public void pushValue(JLabel out1, JLabel out2, JLabel out3, int value) {
		out3.setText(out2.getText());
		out2.setText(out1.getText());
		out1.setText(value + Messages.getString("UserInterface.5")); //$NON-NLS-1$
	}

	/**
	 * Gets the minimum and maximum values.
	 * 
	 * @return if the inputs are valid.
	 */
	private boolean getInputs() {
		Scanner scanner1 = new Scanner(minimumValueInput.getText());
		if (!scanner1.hasNextInt()) {
			scanner1.close();
			return false;
		}
		minimumValue = scanner1.nextInt();
		scanner1.close();
		Scanner scanner2 = new Scanner(maximumValueInput.getText());
		if (!scanner2.hasNextInt()) {
			scanner2.close();
			return false;
		}
		maximumValue = scanner2.nextInt();
		scanner2.close();
		return true;
	}

	/**
	 * Verifies that the proper input type has been provided.
	 * 
	 * @return if the proper input type has been provided.
	 */
	private boolean verifyValidInputType() {
		Scanner scanner1 = new Scanner(minimumValueInput.getText());
		Scanner scanner2 = new Scanner(maximumValueInput.getText());
		if (scanner1.hasNextInt() && scanner2.hasNextInt()) {
			if (scanner1.nextLong() > Integer.MAX_VALUE
					|| scanner2.nextLong() > Integer.MAX_VALUE) {
				scanner1.close();
				scanner2.close();
				return false;
			}
			scanner1.close();
			scanner2.close();
			return true;
		}
		scanner1.close();
		scanner2.close();
		return false;
	}

	/**
	 * Verifies that the inputs are within the range supported by an integer.
	 * 
	 * @return if the inputs are in the valid range.
	 * @throws NumberFormatError
	 *             if the inputs does not contain a parsable long.
	 */
	private boolean verifyValidValues() {
		long minInput = Long.parseLong(minimumValueInput.getText());
		long maxInput = Long.parseLong(maximumValueInput.getText());
		if (minInput > Integer.MAX_VALUE || maxInput > Integer.MAX_VALUE) {
			return false;
		}
		return true;
	}

	/**
	 * Verifies that a valid range has been provided.
	 * 
	 * @return if the range provided is valid.
	 */
	private boolean verifyValidRange() {
		if (minimumValue <= maximumValue && minimumValue >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {/*
										 * Required by the MouseListener
										 * interface.
										 */
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(Messages.getString("UserInterface.6")); //$NON-NLS-1$
		pickingSequence();
		/*
		 * if (arg0.getSource().equals(pickNumber)) { pickingSequence(); } else
		 * { response(arg0.getSource(), null); }
		 */
	}

	@Override
	public void keyPressed(KeyEvent arg0) {/*
											 * Required by the KeyListener
											 * interface.
											 */
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println(Messages.getString("UserInterface.7")); //$NON-NLS-1$
		response(arg0.getSource(), arg0.getKeyCode());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {/*
												 * Required by the MouseListener
												 * interface.
												 */
	}

	@Override
	public void mouseExited(MouseEvent arg0) {/*
											 * Required by the MouseListener
											 * interface.
											 */
	}

	@Override
	public void mousePressed(MouseEvent arg0) {/*
												 * Required by the MouseListener
												 * interface.
												 */
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {/*
												 * Required by the MouseListener
												 * interface.
												 */
	}
}
