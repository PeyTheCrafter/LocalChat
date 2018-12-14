package controller;

import java.util.ArrayList;

import controller.client.Client;
import controller.netScan.scanner.ServerScanner;
import model.Message;
import model.ServerData;

public class Logic {
	
	private ServerScanner serverScanner;
	private Client client;
	
	public Logic() {
		this.serverScanner = new ServerScanner();
		this.client = new Client();
	}
	
	public ArrayList<ServerData> getServers() {
		try {
			return this.serverScanner.scan();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void connect(ServerData serverData, String username) {
		this.client.connect(serverData, username);
	}

	public void sendMessage(Message m) {
		this.client.sendMessage(m);
	}

}
