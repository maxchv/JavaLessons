package websockets;

import decoders.MessageDecoder;
import encoders.CheckerMessageEncoder;
import encoders.UserEncoder;
import encoders.UserMessageEncoder;
import models.CheckerMessage;
import models.Message;
import models.User;
import models.UserMessage;

import javax.json.*;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;


@ServerEndpoint(value = "/chat", encoders = {UserMessageEncoder.class, UserEncoder.class, CheckerMessageEncoder.class}, decoders = {MessageDecoder.class})
public class ChatWebSocket {
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private static Map<String, String> usersMap = Collections.synchronizedMap(new HashMap<>());

    @OnOpen
    public void init(Session session, EndpointConfig config) {
        peers.add(session);
    }

    @OnMessage
    public void sendMessage(Message message, Session session) throws IOException, EncodeException {
        if (message instanceof UserMessage) {
            UserMessage msg = (UserMessage) message;
            if (msg.getRecipient() != null) {
                for (Session peer : peers) {
                    if (peer.getId().equals(usersMap.get(msg.getRecipient()))) {
                        peer.getBasicRemote().sendObject(msg);
                    }
                }
            } else {
                for (Session peer : peers) {
                    if (!peer.equals(session)) {
                        peer.getBasicRemote().sendObject(msg);
                    }
                }
            }
        } else if (message instanceof User) {
            if (((User) message).getDeletedUserName() != null) {
                deleteUser((User) message, session);
            } else {
                JsonString JsonUsername = ((User) message).getUserName();
                JsonObjectBuilder users = Json.createObjectBuilder();
                usersMap.put(JsonUsername.getString(), session.getId());
                JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
                for (String s : usersMap.keySet()) {
                    arrBuilder.add(s);
                }
                users.add("users", arrBuilder.build());
                JsonObject userOBJ = users.build();
                User sendedArrayUsers = new User(userOBJ);
                for (Session peer : peers) {
                    peer.getBasicRemote().sendObject(sendedArrayUsers);
                }
            }
        }
        else if(message instanceof CheckerMessage){
            CheckerMessage msg = (CheckerMessage) message;
            if (msg.getRecipient() != null) {
                for (Session peer : peers) {
                    if (peer.getId().equals(usersMap.get(msg.getRecipient()))) {
                        peer.getBasicRemote().sendObject(msg);
                    }
                }
            }
        }
    }

    @OnError
    public void error(Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnClose
    public void OnClose(Session session) {
        peers.remove(session);
    }

    static void deleteUser(User user, Session session) {
        String deletedUserName = user.getDeletedUserName().getString();
        for (String userName : usersMap.keySet()) {
            if (usersMap.get(userName).equals(session.getId())) {
                deletedUserName = userName;
                usersMap.remove(userName);
                break;
            }
        }
        JsonObjectBuilder deletedUserBuilder = Json.createObjectBuilder()
                .add("deleteUsername", deletedUserName);
        JsonObject deletedUserJsonObj = deletedUserBuilder.build();
        User deletedUser = new User(deletedUserJsonObj);
        try {
            for (Session peer : peers) {
                if (!peer.equals(session)) {
                    peer.getBasicRemote().sendObject(deletedUser);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }
}
