package controller.netScan.thread;

import controller.netScan.scanner.*;

public class ServerScanThread extends Thread {
	private ServerScanner ss;
	private int start;
	private int stop;

	public ServerScanThread(ServerScanner ss, int start, int stop) {
		this.ss = ss;
		this.start = start;
		this.stop = stop;
		this.ss.getSubnet();
		this.ss.getTimeout();
	}

	public void run() {
		for (int i = this.start; i <= this.stop; i++) {
			if (this.ss.isServer(this.ss.getSubnet() + "." + i, this.ss.getPort())) {
				System.out.println(
						"Server found at " + this.ss.getSubnet() + "." + i + " with port " + this.ss.getPort());
			}
		}
	}
}
