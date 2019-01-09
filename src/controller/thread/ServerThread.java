package controller.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import controller.commander.Commander;
import model.Message;

public class ServerThread implements Runnable, ListDataListener {

	private DefaultListModel<String> conversation;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Commander commander;

	public ServerThread(DefaultListModel<String> conversation, Socket client) {
		this.conversation = conversation;
		this.socket = client;
		this.commander = new Commander("/");
		try {
			this.dis = new DataInputStream(this.socket.getInputStream());
			this.dos = new DataOutputStream(this.socket.getOutputStream());
			this.conversation.addListDataListener(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				String message = this.dis.readUTF();
				System.out.println(this.commander.ckechCommand(message));
				if (!this.commander.ckechCommand(message)) {
					synchronized (this.conversation) {
						this.sendMessage(message);
					}
				} else {
					this.executeCommand(this.commander.getExecution(message));
				}
			}
		} catch (IOException e) {
			// e.printStackTrace();
			this.disconnect();
		}
	}

	private void executeCommand(String command) {
		try {
			Method method = this.getClass().getMethod(command, null);
			method.invoke(this, null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void disconnect() {
		System.out.println("User disconnected.");
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread.currentThread().interrupt();
	}

	private void sendMessage(String msg) {
		this.conversation.addElement(msg);
		System.out.println(msg);
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		String message = (String) this.conversation.getElementAt(e.getIndex0());
		try {
			this.dos.writeUTF(message);
		} catch (Exception e1) {
		}
	}

	@Override
	public void contentsChanged(ListDataEvent e) {

	}

	@Override
	public void intervalRemoved(ListDataEvent e) {

	}

}
