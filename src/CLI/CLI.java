package CLI;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.client.Client;
import controller.netScan.scanner.ServerScanner;
import controller.server.Server;
import model.ServerData;

public class CLI {
	public CLI() {

	}

	public void client() {
		System.out.println("Scan servers? (y/n)");
		String response = new Scanner(System.in).nextLine();
		if (response.equals("y")) {
			System.out.println("Port: ");
			int scanPort = new Scanner(System.in).nextInt();
			System.out.println("Threads: ");
			int threads = new Scanner(System.in).nextInt();
			try {
				String address = InetAddress.getLocalHost().getHostAddress();
				ServerScanner ss = new ServerScanner(address.substring(0, address.lastIndexOf(".")), scanPort, 50);
				ss.scan();
			} catch (Exception e) {
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

	public void server() {
		System.out.println("Port: ");
		new Server(new Scanner(System.in).nextInt()).start();
	}

}
