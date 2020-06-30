package in.rk.learn.java.websocket.application;

import org.glassfish.tyrus.server.Server;
import javax.websocket.DeploymentException;
import java.util.Scanner;

public class ChatServer {

    public static void main(String[] args) {
        
		Server server = new Server("localhost", Constants.port, "/ws", ChatServerEndpoint.class);
        try (Scanner scanner = new Scanner(System.in)){
            server.start();
            System.out.println("Press any key to stop the server..");
            scanner.nextLine();
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }

}
