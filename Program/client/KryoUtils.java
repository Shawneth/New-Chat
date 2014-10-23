package client;

import java.io.IOException;

import packets.Sendables.*;
import packets.ClientInformation;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class KryoUtils extends Listener {
	static Kryo k;
	static Client client;
	static ClientInformation info;

	public KryoUtils(ClientInformation info) {
		this.info = info;
		client = new Client();
		k = client.getKryo();
		registerKryo();
		client.start();
		try {
			client.connect(80000, info.ip, Integer.parseInt(info.port));
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		client.addListener(this);
	}

	private static void registerKryo() {
		k.register(SignIn.class);
		k.register(Message.class);
	}

	public static void sendMessage(String message) {
		
		if(message.equals(""))
			return;
		
		if(client == null){
			Utils.writeWarning("You're not connected to a server!");
			return;
		}
		
		Message m = new Message();
		m.setMessage(message, info.username);
		client.sendTCP(m);
		Utils.writeMessage(info.username, message);
		WindowConstruct.getField().setText("");
	}

	private static void gotMessage(String user, String message) {
		Utils.writeMessage(user, message);
	}

	@Override
	public void received(Connection c, Object o) {
		if (o instanceof Message) {
			Message m = (Message) o;
			gotMessage(m.username, m.message);
		}
	}
}
