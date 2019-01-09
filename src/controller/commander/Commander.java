package controller.commander;

import java.util.ArrayList;

public class Commander {
	private String prefix;
	private String[] commands = { "disconnect" };
	private ArrayList<String> executions;

	public static void main(String[] args) {
		Commander c = new Commander("/");
		c.ckechCommand("/disconnect");
	}
	
	public Commander(String prefix) {
		this.prefix = prefix;
		this.executions = new ArrayList<>();
		this.createExecutions();
	}
	
	private void createExecutions() {
		this.executions.add("disconnect");
	}
	
	public boolean ckechCommand(String msg) {
		return this.isCommand(msg);
	}

	public boolean isCommand(String msg) {
		boolean found = false;
		for (int i = 0; i < this.commands.length; i++) {
			if (msg.startsWith(prefix + this.commands[i])) {
				found = true;
			}
		}
		return found;
	}
	
	public String getExecution(String msg) {
		if(this.isCommand(msg)) {
			return this.executions.get(this.executions.indexOf(msg.toString()));
		}
		return "";
	}
}
