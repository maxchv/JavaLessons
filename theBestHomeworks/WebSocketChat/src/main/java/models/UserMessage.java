package models;

import javax.json.JsonObject;

public class UserMessage extends Message{
    public UserMessage(JsonObject json) {
        super(json);
    }
    public String getRecipient(){
        try {
            return getJson().getString("recipient");
        }
        catch (NullPointerException e){
            return null;
        }
    }
}
