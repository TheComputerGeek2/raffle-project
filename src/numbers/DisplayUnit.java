package numbers;

import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class DisplayUnit {
	
	protected JPanel panel;
	
public DisplayUnit() {
	
}

public JPanel getComponent() {
	return panel;
}

public void update() {
	JComponent[] temp = (JComponent[])panel.getComponents();
	for (int i = 0; i < temp.length; i++) {
		temp[i].paintImmediately(temp[i].getBounds());
	}
	Toolkit.getDefaultToolkit().sync();
}
}
