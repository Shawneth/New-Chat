package client;

public class Utils {
	private final static String FORMAT = "\n";

	public static void writeMessage(String user, String message) {
		WindowConstruct.getBoard().append(user + ": " + message + FORMAT);
	}

	public static void writeWarning(String warning) {
		WindowConstruct.getBoard().append(warning + FORMAT);
	}
}
