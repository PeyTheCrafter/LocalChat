package model;

import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private String emitter;
	private String receiver;
	private String content;

	public Message(String emitter, String content) {
		this.emitter = emitter;
		this.content = content;
	}
	
	public String getSimpleMessage() {
		return "[" + this.getEmitter() + "]: " + this.getContent();
	}

	public String getEmitter() {
		return emitter;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getContent() {
		return content;
	}

}
