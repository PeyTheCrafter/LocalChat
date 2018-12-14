package controller.netScan.scanner;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import controller.netScan.thread.ServerScanThread;
import model.ServerData;

public class ServerScanner {
	
	private String subnet;
	private int port;
	private int timeout;
	private ArrayList<ServerData> serversFound;

	public ServerScanner(String subnet, int port, int timeout) {
		this.subnet = subnet;
		this.port = port;
		this.timeout = timeout;
		this.serversFound = new ArrayList<>();
	}
	
	public ServerScanner() {
		extractIP();
		this.port = 6544;
		this.timeout = 50;
		this.serversFound = new ArrayList<>();
	}
	
	/**
	 * Scans the network using threads.
	 * 
	 * @param threads
	 *            the number of threads to create.
	 * @throws InterruptedException 
	 */
	public ArrayList<ServerData> scan() throws InterruptedException {
		final int ips = 254;
		int ipt = ips / 10;
		System.out.println("Scanning network with " + 10 + " threads. (" + ipt + " ip(s) per thread).");
		
		int current = 0;
		this.serversFound.addAll(new ServerScanThread(this, current, current + ipt).scan());
		current += ipt;
		while (current < ips) {
			int start = current + 1;
			current += ipt;
			if (current >= ips) {
				current = ips;
			}
			int stop = current;
			this.serversFound.addAll(new ServerScanThread(this, start, stop).scan());
		}
		
		return this.serversFound;
	}

	/**
	 * Checks if ip:port is hosting a server or not.
	 * @param ip ip to check.
	 * @param port port listening to the server.
	 * @return true if there is a server, false if not.
	 */
	public boolean isServer(String ip, int port) {
		boolean isServer = false;
		try {
			Socket s = new Socket();
			s.connect(new InetSocketAddress(ip, port), this.timeout);
			s.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private void extractIP() {
		try {
			String subnet = InetAddress.getLocalHost().getHostAddress();
			this.subnet = subnet.substring(0, subnet.lastIndexOf("."));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public String getSubnet() {
		return subnet;
	}

	public int getPort() {
		return port;
	}

	public int getTimeout() {
		return timeout;
	}
	
	
}
