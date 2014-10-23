package packets;

public class Sendables {

	public static class SignIn {
		public String username;

		public void setUsername(String user) {
			username = user;
		}
		public String getUsername(){
			return username;
		}
	}

	public static class Message {
		public String message;
		public String username;

		public void setMessage(String message, String user) {
			this.message = message;
			username = user;
		}
	}

}
