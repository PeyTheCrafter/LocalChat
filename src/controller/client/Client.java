package controller.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import CLI.CLI;
import controller.netScan.scanner.ServerScanner;
import controller.thread.ClientThread;

public class Client {

	private Socket socket;
	private String ip;
	private int port;
	private String username;

	public static void main(String[] args) {
		new CLI().client();
	}

	public Client(String ip, int port, String username) {
		this.ip = ip;
		this.port = port;
		this.username = username;
	}

	public void createClient() {
		try {
			this.socket = new Socket(this.ip, this.port);
			ClientThread clientThread = new ClientThread(this.socket, this.username);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
