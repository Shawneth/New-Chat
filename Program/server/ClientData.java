package server;

import com.esotericsoftware.kryonet.Connection;

public class ClientData {
	
	private final Connection c;
	
	private final String name;
	
	public int getID(){
		return c.getID();
	}
	public String getName(){
		return name;
	}
	public Connection getConnection(){
		return c;
	}
	public ClientData(Connection c, String name){
		this.c = c;
		this.name = name;
	}
}
