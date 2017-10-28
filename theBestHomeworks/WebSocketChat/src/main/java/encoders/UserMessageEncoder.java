package encoders;

import models.UserMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class UserMessageEncoder implements Encoder.Text<UserMessage>{
    @Override
    public String encode(UserMessage message) throws EncodeException {
        return message.getJson().toString();
    }

    @Override
    public void init(EndpointConfig config) {
        //System.out.println("init MessageEncoder");
    }

    @Override
    public void destroy() {
        //System.out.println("destroy MessageEncoder");
    }
}
