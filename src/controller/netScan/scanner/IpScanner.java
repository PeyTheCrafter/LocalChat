package controller.netScan.scanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class IpScanner {
	private int timeout;
	public IpScanner(int timeout) {
		this.timeout = timeout;
	}
	
	public static void main(String[] args) {
		IpScanner ips = new IpScanner(50);
		InetAddress result = ips.scan("localhost");
	}
	
	/**
	 * Scans the ip.
	 * 
	 * @param ip
	 *            ip to scan.
	 * @return 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public InetAddress scan(String ip) {
		InetAddress address;
		try {
			address = InetAddress.getByName(ip);
			if (address.isReachable(this.timeout)) {
				return address;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
