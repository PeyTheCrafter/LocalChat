package view.components;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;

import model.Message;
import javax.swing.SwingConstants;

public class MessageUI extends JPanel {

	private final Color BACKGROUND_MESSAGE = new Color(24, 37, 51);
	private JTextArea contentMessage;
	private JLabel transmitter;

	public MessageUI() {
		create(new Message("Espero que las lineas largas se adapten (ya funciona) y que todo vaya bien"));
	}
	
	public MessageUI(Message message) {
		create(message);
	}
	
	public void addMessage(String message) {
		this.contentMessage.setText(message);
	}
	
	public void addTransmitter(String transmitter) {
		this.transmitter.setText(transmitter);
	}
	
	private void create(Message message) {
		setOpaque(false);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		contentMessage = new JTextArea();
		add(contentMessage, BorderLayout.CENTER);
		contentMessage.setWrapStyleWord(true);
		contentMessage.setEnabled(false);
		contentMessage.setDisabledTextColor(Color.WHITE);
		contentMessage.setMaximumSize(new Dimension(600, 2147483647));
		contentMessage.setLineWrap(true);
		contentMessage.setBackground(BACKGROUND_MESSAGE);
		contentMessage.setFont(new Font("Arial", Font.PLAIN, 17));
		contentMessage.setBorder(new EmptyBorder(10, 10, 10, 30));
		contentMessage.setText(message.getMessage());
		
		JPanel infoMessage = new JPanel();
		infoMessage.setBorder(new EmptyBorder(0, 10, 5, 20));
		infoMessage.setBackground(BACKGROUND_MESSAGE);
		add(infoMessage, BorderLayout.SOUTH);
		infoMessage.setLayout(new BorderLayout(0, 0));
		
		transmitter = new JLabel(String.valueOf(message.getTransmitter()));
		infoMessage.add(transmitter, BorderLayout.WEST);
		transmitter.setForeground(Color.WHITE);
		transmitter.setFont(new Font("Arial", Font.BOLD, 11));
		
		JLabel time = new JLabel(message.getTime());
		time.setFont(new Font("Arial", Font.BOLD, 12));
		time.setForeground(Color.WHITE);
		infoMessage.add(time, BorderLayout.EAST);
		time.setHorizontalAlignment(SwingConstants.RIGHT);
	}

}
