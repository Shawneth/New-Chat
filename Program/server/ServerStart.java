package server;

import java.io.IOException;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import packets.Sendables.*;

public class ServerStart extends Listener{
		private static final String host = "SERVER";
		static HashMap<Integer, ClientData> list = new HashMap<Integer, ClientData>();
	
		static Kryo k;
		static Server server;

		public ServerStart(int port) {
			Log.set(Log.LEVEL_NONE);
			server = new Server();
			try {
				server.bind(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
			k = server.getKryo();
			registerKryo();
			server.addListener(this);
			server.start();
		}

		private static void registerKryo() {
			k.register(SignIn.class);
			k.register(Message.class);
		}


		@Override
		public void connected(Connection c){
			System.out.println("Attempting to authenticate..");
			securityCheck(c);
		}
		
		@Override
		public void received(Connection c, Object o) {
			if(o instanceof Message){
				Message m = (Message) o;
				System.out.println(m.username + ": " + m.message);
				server.sendToAllExceptTCP(c.getID(), m);
			}
		}
		
		@Override
		public void disconnected(Connection c){
			Message m = new Message();
			m.setMessage(list.get(c.getID()).getName() + " has disconnected.", host);
			System.out.println(list.get(c.getID()).getName() + " has disconnected.");
			server.sendToAllExceptTCP(c.getID(), m);
			for(ClientData client : list.values()){
				if(client.getID() == c.getID()) {
					list.remove(client.getID());
					return;
				}
			}
		}
	
		public void securityCheck(Connection c){
			ClientData info = new ClientData(c, c.getRemoteAddressTCP().getHostName());
			list.put(info.getID(), info);
			System.out.println(info.getName());
		}
		
	public static void main(String[] args) {
		
		//Shawn Greene
		System.out.println("Java Chat Server");
		System.out.println("Shawn Greene 2014\n");
		
		
		
		ServerStart s;
		if(args.length > 0) {
			s = new ServerStart(Integer.parseInt(args[0]));
		} else {
			s = new ServerStart(5555);
		}
	}
}
