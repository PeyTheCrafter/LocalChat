package netScan.scanner;

import java.net.InetSocketAddress;
import java.net.Socket;

import netScan.thread.ServerScanThread;

public class ServerScanner {
	private String subnet;
	private int port;
	private int timeout;

	public ServerScanner(String subnet, int port, int timeout) {
		this.subnet = subnet;
		this.port = port;
		this.timeout = timeout;
	}
	
	public static void main(String[] args) {
		ServerScanner ss = new ServerScanner("192.168.1", 6544, 50);
		//ss.scan(24);
	}
	
	/**
	 * Scans the network using threads.
	 * 
	 * @param threads
	 *            the number of threads to create.
	 * @throws InterruptedException 
	 */
	public void scan(int threads) throws InterruptedException {
		final int ips = 254;
		int ipt = ips / threads;
		System.out.println("Scanning network with " + threads + " threads. (" + ipt + " ip(s) per thread).");

		int current = 0;
		new ServerScanThread(this, current, current + ipt).start();
		current += ipt;
		while (current < ips) {
			int start = current + 1;
			current += ipt;
			if (current >= ips) {
				current = ips;
			}
			int stop = current;
			new ServerScanThread(this, start, stop).start();
			// System.out.println(start + " - " + stop);
		}
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
			// System.out.println("No server.");
			return false;
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
