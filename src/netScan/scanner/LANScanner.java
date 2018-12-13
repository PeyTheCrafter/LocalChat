package netScan.scanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import netScan.thread.ScanThread;

public class LANScanner {
	private String subnet;
	private int timeout;

	public LANScanner(String subnet, int timeout) {
		this.subnet = subnet;
		this.timeout = timeout;
	}

	/**
	 * Scans the network using threads.
	 * 
	 * @param threads
	 *            the number of threads to create.
	 */
	public void scan(int threads) {
		final int ips = 254;
		int ipt = ips / threads;
		System.out.println("Scanning network with " + threads + " threads. (" + ipt + " ip(s) per thread).");

		int current = 0;
		new ScanThread(this, this.subnet, current, current + ipt, this.timeout).start();
		// System.out.println(current + " - " + (current + ipt));
		current += ipt;
		while (current < ips) {
			int start = current + 1;
			current += ipt;
			if (current >= ips) {
				current = ips;
			}
			int stop = current;
			new ScanThread(this, this.subnet, start, stop, this.timeout).start();
			// System.out.println(start + " - " + stop);
		}
	}

	/**
	 * Scans from subnet.0 to subnet.255
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void scan() throws UnknownHostException, IOException {
		IpScanner ipScanner = new IpScanner(this.timeout);
		for (int i = 0; i < 255; i++) {
			String ip = this.subnet + "." + i;
			InetAddress address = InetAddress.getByName(ip);
			ipScanner.scan(ip);
		}
	}

	/**
	 * Scans subnet.start to subnet.stop.
	 * 
	 * @param start
	 *            first ip to scan.
	 * @param stop
	 *            last ip to scan.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void scan(int start, int stop) throws UnknownHostException, IOException {
		IpScanner ips = new IpScanner(this.timeout);
		for (int i = start; i < stop; i++) {
			String ip = this.subnet + "." + i;
			InetAddress address = InetAddress.getByName(ip);
			ips.scan(ip);
		}
	}

	public String getSubnet() {
		return subnet;
	}

	public int getTimeout() {
		return timeout;
	}

}