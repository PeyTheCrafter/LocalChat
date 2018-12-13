package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import model.Message;

public class ClientThread implements Runnable {
	private DataInputStream dis;
	private DataOutputStream dos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket socket;
	private String username;

	public ClientThread(Socket socket, String username) {
		this.socket = socket;
		this.username = username;
		try {
			this.dis = new DataInputStream(this.socket.getInputStream());
			this.dos = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread thread = new Thread(this);
		thread.start();
		this.listener();
	}

	@Override
	public void run() {
		try {
			while (true) {
				String message = this.dis.readUTF();
				System.out.println(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listener() {
		this.sendMessage(this.username + " joined.");
		while (true) {
			String message = new Scanner(System.in).nextLine();
			this.sendMessage(message);
		}
	}

	private void sendMessage(String msg) {
		try {
			this.dos.writeUTF(this.formatUsername() + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String formatUsername() {
		return "[" + this.username + "]: ";
	}
}
