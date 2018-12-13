package GUI.thread;

import controller.client.ClientMain;

public class ClientWindowThread implements Runnable {

	@Override
	public void run() {
		try {
			ClientMain frame = new ClientMain();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
