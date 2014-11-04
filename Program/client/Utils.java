package client;

public class Utils {
	private final static String FORMAT = "\n";

	public static void writeMessage(String user, String message) {
		formatMessage(user, message);
		ClientChatWindow.getBoard().append(user + ": " + formatMessage(user,message) + FORMAT);
	}
	private static String formatMessage(String user, String message){
		System.out.println("This is happening!");
		int chars = user.length() + message.length() + 2;
		StringBuilder b = new StringBuilder(message);
		if(chars > 45){
			b.insert(45-2-user.length(), '\n');
		}
		return b.toString();
	}
	public static void writeWarning(String warning) {
		ClientChatWindow.getBoard().append(warning + FORMAT);
	}
}
