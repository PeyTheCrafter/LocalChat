package controller.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import controller.thread.ClientThread;
import model.Message;
import model.ServerData;

public class Client {

	private Socket socket;
	private ClientThread clientThread;
	
	public void connect(ServerData serverData, String username) {
		try {
			this.socket = new Socket(serverData.getIp(), serverData.getPort());
			this.clientThread = new ClientThread(this.socket, username);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(Message message) {
		this.clientThread.sendMessage(message);
	}
	
	/*public void deleteClient() {
		this.clientThread.deleteClient();
	}*/
}
