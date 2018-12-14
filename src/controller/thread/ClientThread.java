package controller.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controller.Controller;
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
			/*this.ois = new ObjectInputStream(this.socket.getInputStream());
			this.oos = new ObjectOutputStream(this.socket.getOutputStream());*/
			Thread thread = new Thread(this);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				Message message = new Message(this.dis.readUTF());
				Controller.getInstance().showMessage(message);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Communication lost.");
		}
	}

	public void sendMessage(Message message) {
		try {
			this.dos.writeUTF(message.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String formatUsername() {
		return "[" + this.username + "]: ";
	}
	
	/*public void deleteClient() {
		this.thread.interrupt();
	}*/
}
