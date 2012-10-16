package ticket;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class TicketFactory {
	
	private ArrayList<Integer> values;
	
	protected ArrayList<Ticket> activeTickets;
	
	private JPanel panel;
	
	private Graphics2D g2;
	
	private int xShift;
	private int yShift;
	
	public static final String IMAGE_PATH = ""; //$NON-NLS-1$
	
	private NumberGenerator ng;

public TicketFactory(JPanel panel, int xShift, int yShift, NumberGenerator ng) {
	this(panel, xShift, yShift);
	loadNumberGenerator(ng);
}

public TicketFactory(JPanel panel, int xShift, int yShift) {
	this.panel = panel;
	this.g2 = (Graphics2D) panel.getGraphics();
	values = new ArrayList<Integer>();
	this.xShift = xShift;
	this.yShift = yShift;
}

public void queueValue() {
	if (ng.hasNextValue())
		values.add(ng.getValue());
}

/**Pulls a value from the front of the list and generates a ticket with that value.
 * @return true if there was a value to pull. false otherwise*/
private boolean generateTicket() {
	if (values.isEmpty()) {
		return false; 
	}
	Ticket temp = new Ticket(panel.getWidth(), panel.getHeight(), g2, panel);
	temp.loadImage(IMAGE_PATH);
	temp.setValue(values.get(0));
	values.remove(0);
	temp.setShift(this.xShift, this.yShift);
	synchronized (this) {
		activeTickets.add(temp);
	}
	return true;
	//TODO take a value from the list and and a ticket with the corresponding value
}

private synchronized void drawTickets() {
	for (Ticket t: activeTickets) {
		t.draw();
		if (t.isFinished()) {
			activeTickets.remove(t);
			System.gc();
		} else {
			t.shiftDisplay();
		}
	}
	generateTicket();
}

/**Loads the number generator.
 * @param ng the number generator to load*/
public void loadNumberGenerator(NumberGenerator ng) {
	this.ng = ng;
}

}
