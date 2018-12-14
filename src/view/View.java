package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Message;
import model.ServerData;
import view.components.Chat;
import view.components.ChatLink;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;

public class View extends JFrame {

	private JPanel contentPane;
	private Chat chat;
	private JPanel chatsPanel;
	private JTextField ip;
	private JTextField port;
	private JButton btnConnect;

	public View() {
		setMinimumSize(new Dimension(490, 634));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				toggleLeftBar();
			}
		});
		setTitle("LocalChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 668);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		chatsPanel = new JPanel();
		chatsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		chatsPanel.setBackground(new Color(23, 33, 43));
		
		chat = new Chat();
		contentPane.add(chat, BorderLayout.CENTER);
		
		JPanel panelConfig = new JPanel();
		panelConfig.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelConfig.setForeground(Color.WHITE);
		panelConfig.setFont(new Font("Arial", Font.PLAIN, 14));
		panelConfig.setOpaque(false);
		chat.add(panelConfig, BorderLayout.NORTH);
		GridBagLayout gbl_panelConfig = new GridBagLayout();
		gbl_panelConfig.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelConfig.rowHeights = new int[]{0, 0, 0};
		gbl_panelConfig.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelConfig.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelConfig.setLayout(gbl_panelConfig);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setForeground(Color.WHITE);
		lblIp.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		panelConfig.add(lblIp, gbc_lblIp);
		
		ip = new JTextField();
		ip.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_ip = new GridBagConstraints();
		gbc_ip.insets = new Insets(0, 0, 5, 5);
		gbc_ip.fill = GridBagConstraints.HORIZONTAL;
		gbc_ip.gridx = 1;
		gbc_ip.gridy = 0;
		panelConfig.add(ip, gbc_ip);
		ip.setColumns(10);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(e -> connect());
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.insets = new Insets(0, 0, 5, 0);
		gbc_btnConnect.gridx = 2;
		gbc_btnConnect.gridy = 0;
		panelConfig.add(btnConnect, gbc_btnConnect);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setForeground(Color.WHITE);
		lblPort.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.EAST;
		gbc_lblPort.insets = new Insets(0, 0, 0, 5);
		gbc_lblPort.gridx = 0;
		gbc_lblPort.gridy = 1;
		panelConfig.add(lblPort, gbc_lblPort);
		
		port = new JTextField();
		port.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_port = new GridBagConstraints();
		gbc_port.insets = new Insets(0, 0, 0, 5);
		gbc_port.fill = GridBagConstraints.HORIZONTAL;
		gbc_port.gridx = 1;
		gbc_port.gridy = 1;
		panelConfig.add(port, gbc_port);
		port.setColumns(10);
		
		setVisible(true);
	}
	
	private void toggleLeftBar() {
		this.chatsPanel.setVisible(this.getWidth() > 815);
	}
	
	public void loadServers(List<ServerData> serversData) {
		for (ServerData dataServer : serversData) {
			this.chatsPanel.add(new ChatLink(dataServer));
		}
		
		this.revalidate();
	}
	
	public void showMessage(Message message) {
		this.chat.add(message);
	}
	
	private void connect() {
		Thread h = new Thread(new Runnable() {
			@Override
			public void run() {
				ServerData sd = new ServerData();
				
				sd.setIp(ip.getText());
				sd.setPort(Integer.parseInt(port.getText()));
				
				Controller.getInstance().connect(sd);
			}
			
		});
		h.start();
	}

}
