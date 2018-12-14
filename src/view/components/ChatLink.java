package view.components;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

import model.ServerData;

import java.awt.FlowLayout;

public class ChatLink extends JPanel {

	private JLabel port;
	private JLabel ip;

	public ChatLink(ServerData serverData) {
		setOpaque(false);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));

		this.ip = new JLabel(serverData.getIp());
		this.ip.setFont(new Font("Arial", Font.BOLD, 25));
		this.ip.setForeground(Color.WHITE);
		add(this.ip, BorderLayout.CENTER);

		JPanel infoChat = new JPanel();
		infoChat.setOpaque(false);
		infoChat.setBorder(null);
		add(infoChat, BorderLayout.SOUTH);
		infoChat.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		JLabel labelPort = new JLabel("Port:");
		labelPort.setForeground(Color.WHITE);
		infoChat.add(labelPort);

		this.port = new JLabel(String.valueOf(serverData.getPort()));
		this.port.setForeground(Color.WHITE);
		infoChat.add(this.port);
	}

	public void setIP(String ip) {
		this.ip.setText(ip);
	}

	public void setPort(String port) {
		this.port.setText(port);
	}

	public ServerData getServerData() {
		ServerData sd = new ServerData();

		sd.setIp(this.ip.getText());
		sd.setPort(Integer.parseInt(this.port.getText()));

		return sd;
	}

}
