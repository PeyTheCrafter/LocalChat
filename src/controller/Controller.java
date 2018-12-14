package controller;

import model.Message;
import model.ServerData;
import view.View;

public class Controller {
	
	private static Controller instance;
	private View view;
	private Logic logic;
	
	private Controller() {
		this.view = new View();
		this.logic = new Logic();
		loadServers();
	}

	public void loadServers() {
		this.view.loadServers(this.logic.getServers());
	}

	public void connect(ServerData serverData) {
		this.logic.connect(serverData, System.getProperty("user.name"));
	}

	public void sendMessage(Message m) {
		this.logic.sendMessage(m);
	}
	
	public void showMessage(Message message) {
		this.view.showMessage(message);
	}
	
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		
		return instance;
	}

}
