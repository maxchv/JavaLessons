package models;

import javax.json.JsonObject;
import javax.json.JsonString;

public class User extends Message {

    public User(JsonObject json) {
        super(json);
    }
    public JsonString getUserName() {
        JsonObject js  = getJson();
        return js.getJsonString("username");
    }
    public JsonString getDeletedUserName() {
        JsonObject js  = getJson();
        return js.getJsonString("deleteUsername");
    }
}
