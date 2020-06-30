package in.rk.learn.java.websocket.application;


import java.text.SimpleDateFormat;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

import vo.Message;


@ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ChatClientEndpoint {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

    @OnMessage
    public void onMessage(Message message) {
        System.out.println(String.format("[%s:%s] %s",
                simpleDateFormat.format(message.getReceived()), message.getSender(), message.getContent()));
    }

}
