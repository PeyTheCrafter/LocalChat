package view.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.itr_rescue.dataGuard.ui.VerticalFlowLayout;

import controller.Controller;
import model.Message;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

import view.visualElements.*;

import javax.swing.JTextField;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Chat extends JPanel {

	private JTextField textField;
	private JPanel contentChat;
	private JScrollPane scrollPane;

	public Chat() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				resizeChat();
			}
		});
		setMaximumSize(new Dimension(500, 2147483647));
		setMinimumSize(new Dimension(500, 700));
		setPreferredSize(new Dimension(500, 700));
		setFocusTraversalPolicyProvider(true);
		setBounds(100, 100, 501, 700);

		setBackground(new Color(14, 22, 33));
		setBorder(null);
		setLayout(new BorderLayout(0, 0));

		JPanel controlChatPanel = new JPanel();
		controlChatPanel.setMaximumSize(new Dimension(32767, 200));
		controlChatPanel.setBackground(new Color(23, 33, 43));
		controlChatPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		add(controlChatPanel, BorderLayout.SOUTH);
		controlChatPanel.setLayout(new BorderLayout(15, 0));

		textField = new JTextField();
		textField.addActionListener(e -> send());
		textField.setCaretColor(Color.WHITE);
		textField.setBorder(new EmptyBorder(5, 10, 5, 5));
		textField.setForeground(Color.WHITE);
		textField.setOpaque(false);
		textField.setFont(new Font("Arial", Font.PLAIN, 17));
		controlChatPanel.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);

		TextPrompt placeholderTextField = new TextPrompt("Write a message...", textField);
		placeholderTextField.setBorder(null);
		placeholderTextField.setFont(new Font("Arial", Font.PLAIN, 17));
		placeholderTextField.setForeground(Color.GRAY);

		JButton btnNewButton = new JButton("SEND");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(false);
		btnNewButton.addActionListener(e -> send());
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Dubai", Font.BOLD, 18));
		btnNewButton.setBorder(new EmptyBorder(3, 20, 3, 20));
		controlChatPanel.add(btnNewButton, BorderLayout.EAST);

		contentChat = new JPanel();
		contentChat.setAutoscrolls(true);
		contentChat.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentChat.setOpaque(false);
		VerticalFlowLayout vfl_contentChat = new VerticalFlowLayout();
		vfl_contentChat.setHorizontalFill(VerticalFlowLayout.FILL_SPACE);
		vfl_contentChat.setVgap(0);
		vfl_contentChat.setHgap(0);
		vfl_contentChat.setHorizontalAlign(0.0f);
		contentChat.setLayout(vfl_contentChat);

		add(contentChat, BorderLayout.CENTER);

		
		scrollPane = new JScrollPane(contentChat);
	    scrollPane.setComponentZOrder(scrollPane.getVerticalScrollBar(), 0);
	    scrollPane.setComponentZOrder(scrollPane.getViewport(), 1);
	    scrollPane.getVerticalScrollBar().setOpaque(false);

	    /*scrollPane.setLayout(new ScrollPaneLayout() {
	      @Override
	      public void layoutContainer(Container parent) {
	        JScrollPane scrollPane = (JScrollPane) parent;

	        Rectangle availR = scrollPane.getBounds();
	        availR.x = availR.y = 0;

	        Insets parentInsets = parent.getInsets();
	        availR.x = parentInsets.left;
	        availR.y = parentInsets.top;
	        availR.width -= parentInsets.left + parentInsets.right;
	        availR.height -= parentInsets.top + parentInsets.bottom;

	        Rectangle vsbR = new Rectangle();
	        vsbR.width = 12;
	        vsbR.height = availR.height;
	        vsbR.x = availR.x + availR.width - vsbR.width;
	        vsbR.y = availR.y;

	        if (viewport != null) {
	          viewport.setBounds(availR);
	        }
	        if (vsb != null) {
	          vsb.setVisible(true);
	          vsb.setBounds(vsbR);
	        }
	      }
	    });*/
	    scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		scrollPane.setBorder(null);
		scrollPane.setLayout(new ScrollPaneLayout());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void add(Message message) {
		this.contentChat.add(new MessageUI(message));
		this.revalidate();
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}
	
	private void send() {
		Message m = new Message(this.textField.getText());
		
		Controller.getInstance().sendMessage(m);
		this.textField.setText("");
		this.revalidate();
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}

	@Deprecated
	private void add() {
		String message = this.textField.getText();
		
		if(!message.isEmpty()) {
			this.contentChat.add(new MessageUI(new Message(this.textField.getText())));
			this.textField.setText("");
			this.revalidate();
			scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		}
	}
	
	private void resizeChat() {
		this.contentChat.setBounds(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void removeAllMessage() {
		this.contentChat.removeAll();
		this.revalidate();
	}
}
