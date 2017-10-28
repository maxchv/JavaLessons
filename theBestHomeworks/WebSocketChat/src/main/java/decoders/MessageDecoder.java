package decoders;

import models.CheckerMessage;
import models.Message;
import models.User;
import models.UserMessage;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class MessageDecoder implements Decoder.Text<Message>{
    @Override
    public Message decode(String s) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
        try{
            if(jsonObject.getString("username") != null){
                return new User(jsonObject);
            }
        }
        catch (NullPointerException e) {}
        try{
            if(jsonObject.getString("deleteUsername") != null){
                return new User(jsonObject);
            }
        }
        catch (NullPointerException e) {}
        try{
            if(jsonObject.getString("checker") != null){
                return new CheckerMessage(jsonObject);
            }
        }
        catch (NullPointerException e) {}
        return new UserMessage(jsonObject);
    }

    @Override
    public boolean willDecode(String s) {
        try {
            Json.createReader(new StringReader(s)).readObject();
            return true;
        } catch (JsonException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {
    }
}
