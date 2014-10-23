package client;

public class Utils {
	private final static String FORMAT = "\n";

	public static void writeMessage(String user, String message) {
		ClientChatWindow.getBoard().append(user + ": " + message + FORMAT);
	}

	public static void writeWarning(String warning) {
		ClientChatWindow.getBoard().append(warning + FORMAT);
	}
}
