package encoders;

import models.User;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class UserEncoder implements Encoder.Text<User> {
    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String encode(User object) throws EncodeException {
        return object.getJson().toString();
    }
}
