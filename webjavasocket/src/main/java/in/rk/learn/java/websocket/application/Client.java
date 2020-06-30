package in.rk.learn.java.websocket.application;

import org.glassfish.tyrus.client.ClientManager;

import javax.json.Json;
import javax.websocket.DeploymentException;
import javax.websocket.Session;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.connectToServer();
	}

	public void connectToServer() {
		ClientManager clientManager = ClientManager.createClient();

		Session session = null;
		try {
			session = clientManager.connectToServer(ChatClientEndpoint.class,
					new URI("ws://localhost:" + Constants.port + "/ws/chat"));
		} catch (DeploymentException | URISyntaxException e1) {
			e1.printStackTrace();
		}

		try (Scanner commandLineScanner = new Scanner(System.in)) {
			System.out.println("Welcome to Tiny Chat!");
			System.out.println("What's your name?");
			String user = commandLineScanner.nextLine();
			System.out.println("You are logged in as: " + user);

			String userInput;
			do {
				userInput = commandLineScanner.nextLine();
				session.getBasicRemote().sendText(formatMessage(userInput, user));
			} while (!userInput.equalsIgnoreCase("quit"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String formatMessage(String message, String user) {
		return Json.createObjectBuilder().add("message", message).add("sender", user).add("received", "").build()
				.toString();
	}

}
