package numbers;

public class InputBaseAdapter {
	
	
	public static enum numberSystem {BINARY, OCTAL, DECIMAL, HEXIDECIMAL};
	
public InputBaseAdapter() {
	
}

public numberSystem getSystem(String input) {
	if (input.startsWith("#")) {
		return numberSystem.HEXIDECIMAL;
	}
	return numberSystem.DECIMAL;
}

}
