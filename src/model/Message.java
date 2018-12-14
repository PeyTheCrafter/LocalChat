package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Message implements Serializable {

	private static final long serialVersionUID = 2287092805945386560L;
	private int id;
	private String time;
	private String transmitter;
	private String receiver;
	private String message;

	public Message(String message) {
		this.message = message;
		this.transmitter = "localhost";
		setTimeNow();
	}

	private void setTimeNow() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		this.time = sdf.format(cal.getTime());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(String transmitter) {
		this.transmitter = transmitter;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
