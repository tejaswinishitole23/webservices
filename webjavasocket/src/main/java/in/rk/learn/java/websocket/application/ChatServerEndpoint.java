package in.rk.learn.java.websocket.application;

import static java.lang.String.format;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import vo.Message;

@ServerEndpoint(value = "/chat", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ChatServerEndpoint {

    private static Set<Session> allUserSessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(format("%s joined the chat room.", session.getId()));
        allUserSessions.add(session);
    }

    @OnMessage
    public void onMessage(Message message, Session messagingSession) throws IOException, EncodeException {
        for (Session session : allUserSessions) {
            if (!messagingSession.getId().equals(session.getId())) { // do not resend the message to its sender
                session.getBasicRemote().sendObject(message);
            }
        }
    }

    @OnClose
    public void onClose(Session messagingSession) throws IOException, EncodeException {
        System.out.println(format("%s left the chat room.", messagingSession.getId()));
        allUserSessions.remove(messagingSession);
        for (Session session : allUserSessions) {
            Message message = new Message();
            message.setSender("ChatServer");
            message.setContent(format("%s left the chat room", (String) messagingSession.getUserProperties().get("user")));
            message.setReceived(new Date());
            session.getBasicRemote().sendObject(message);
        }
    }

}
