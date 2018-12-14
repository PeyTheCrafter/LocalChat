package controller.netScan.thread;

import java.util.ArrayList;

import controller.netScan.scanner.*;
import model.ServerData;

public class ServerScanThread extends Thread {
	
	private ServerScanner ss;
	private int start;
	private int stop;
	private ArrayList<ServerData> serversFound;

	public ServerScanThread(ServerScanner ss, int start, int stop) {
		this.ss = ss;
		this.start = start;
		this.stop = stop;
		this.ss.getSubnet();
		this.ss.getTimeout();
		this.serversFound = new ArrayList<>();
	}

	/*public void run() {
		for (int i = this.start; i <= this.stop; i++) {
			if (this.ss.isServer(this.ss.getSubnet() + "." + i, this.ss.getPort())) {
				this.serversFound.add(new ServerData(this.ss.getSubnet(), String.valueOf(this.ss.getPort())));
			}
		}
	}*/
	
	public ArrayList<ServerData> scan() {
		start();
		return this.serversFound;
	}
}
