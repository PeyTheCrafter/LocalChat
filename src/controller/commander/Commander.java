package controller.commander;

public class Commander {
	private String prefix = "/";
	private String[] commands = { "disconnect" };

	public static void main(String[] args) {
		Commander c = new Commander();
		c.ckechCommand("/disconnect");
	}
	
	public Commander() {

	}
	
	public void ckechCommand(String msg) {
		if(this.isCommand(msg)) {
			System.out.println("Command readed.");
		}
	}

	public boolean isCommand(String msg) {
		boolean found = false;
		for (int i = 0; i < this.commands.length; i++) {
			if (msg.contains(prefix + this.commands[i])) {
				found = true;
			}
		}
		return found;
	}
}
