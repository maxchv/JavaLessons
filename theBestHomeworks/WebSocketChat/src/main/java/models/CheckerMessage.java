package models;

import javax.json.JsonObject;

public class CheckerMessage extends Message{

    public CheckerMessage(JsonObject json) {
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
