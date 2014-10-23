package client;

public class ClientInformation {
	public final String username, ip, port;

	public ClientInformation(String username, String ip, String port) {
		this.username = username;
		this.port = port;
		this.ip = ip;
	}
	public String getUsername(){
		return username;
	}

}
