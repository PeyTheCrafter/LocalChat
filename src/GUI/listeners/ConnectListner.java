package GUI.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.client.ClientMain;

public class ConnectListner implements ActionListener {
	private ClientMain controller;

	public ConnectListner(ClientMain controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ip = this.controller.getTxtIp().getText();
		int port = Integer.valueOf(this.controller.getTxtPort().getText());
		this.controller.connect(ip, port, "username");
	}

}
