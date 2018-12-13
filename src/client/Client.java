package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import netScan.scanner.LANScanner;
import netScan.scanner.ServerScanner;
import thread.ClientThread;

public class Client {

	private Socket socket;
	private String ip;
	private int port;
	private String username;

	public static void main(String[] args) {
		System.out.println("Scan servers? (y/n)");
		String response = new Scanner(System.in).nextLine();
		if(response.equals("y")) {
			System.out.println("Port: ");
			int scanPort = new Scanner(System.in).nextInt();
			try {
				String address;
				address = InetAddress.getLocalHost().getHostAddress();
				System.out.println(address.substring(0, address.lastIndexOf(".")));
				ServerScanner ss = new ServerScanner(address.substring(0, address.lastIndexOf(".")), scanPort, 50);
				ss.scan(24);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("IP: ");
		String ip = new Scanner(System.in).nextLine();
		System.out.println("Port: ");
		int port = new Scanner(System.in).nextInt();
		System.out.println("Username: ");
		String username = new Scanner(System.in).nextLine();
		Client client = new Client(ip, port, username);
		client.createClient();
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
