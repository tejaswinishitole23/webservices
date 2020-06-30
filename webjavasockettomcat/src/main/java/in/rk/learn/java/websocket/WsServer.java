package in.rk.learn.java.websocket;

import java.util.Date;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

/*
 * Start this in tomcat and test using Simple WebSocket Client - chrome extension
 * Connection string - ws://localhost:8080/webjavasockettomcat/messagemanager
 * */
@ServerEndpoint("/messagemanager")
public class WsServer {
	
	@OnMessage
	public String onMessage(String message){
	    System.out.println("Message from the client: " + message);
	    String echoMsg = "Echo from the server : " + new Date()+": "+ message;
	    return echoMsg;
	}
	
	@OnOpen
	public void onOpen(){
	    System.out.println("Open Connection ...");
	}
	
	@OnClose
	public void onClose(){
	    System.out.println("Close Connection ...");
	}
	
	@OnError
	public void onError(Throwable e){
	    e.printStackTrace();
	}
}
