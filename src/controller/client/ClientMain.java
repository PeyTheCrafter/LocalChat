package controller.client;

import java.awt.EventQueue;

import GUI.ClientGUI;
import GUI.listeners.ConnectListner;
import GUI.thread.ClientWindowThread;

public class ClientMain extends ClientGUI {
	Client client;

	public static void main(String[] args) {
		Runnable thread = new ClientWindowThread();
		Thread window = new Thread(thread);
		window.start();
	}

	public ClientMain() {
		super();
		this.assignListeners();
	}

	public void assignListeners() {
		this.getBtnConnect().addActionListener(new ConnectListner(this));
	}

	public void connect(String ip, int port, String username) {
		this.client = new Client(ip, port, username);
		this.client.createClient();
		System.out.println("Client created");
	}
}
