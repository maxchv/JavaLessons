package encoders;

import models.CheckerMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class CheckerMessageEncoder implements Encoder.Text<CheckerMessage>{

    @Override
    public String encode(CheckerMessage object) throws EncodeException {
        return object.getJson().toString();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
