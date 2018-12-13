package controller.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.DefaultListModel;

import CLI.CLI;
import controller.thread.ServerThread;

public class Server {

	private DefaultListModel<String> conversation = new DefaultListModel<>();
	private int port;

	public static void main(String[] args) {
		new CLI().server();
	}
	
	public Server(int port) {
		this.port = port;
	}

	public void start() {
		this.logInfo("Starting server...");
		try {
			this.logInfo("Attempting to start server on port " + this.port);
			ServerSocket ss = new ServerSocket(this.port);
			this.logInfo("Server running on " + InetAddress.getLocalHost() + ":" + port);
			while(true) {
				Socket socket = ss.accept();
				this.logInfo("Connection from " + socket.getInetAddress());
				Runnable serverThread = new ServerThread(this.conversation, socket);
				Thread thread = new Thread(serverThread);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void logInfo(String msg) {
		System.out.println("[INFO] " + msg);
	}
}
