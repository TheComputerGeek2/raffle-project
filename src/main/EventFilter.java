package main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EventFilter {

	public static boolean buttonEventFilter(Integer keyCode) {
		String keyText = KeyEvent.getKeyText(keyCode);
		if (keyCode.equals(null)) {
			return true;
		}
		ArrayList<String> approvedKeys = new ArrayList<String>();
		approvedKeys.add(KeyEvent.getKeyText(KeyEvent.VK_ENTER));
		for (int i = 0; i < approvedKeys.size(); i++) {
			if (keyText.equalsIgnoreCase(approvedKeys.get(i))) {
				return true;
			}
		}
		return false;
	}
}
