package in.rk.learn.java.websocket.application;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import vo.Message;

public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(final Message message) throws EncodeException {
        String jsonString=Json.createObjectBuilder()
                .add("message", message.getContent())
                .add("sender", message.getSender())
                .add("received", "")
                .build().toString();
        return jsonString;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
