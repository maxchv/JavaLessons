package org.itstep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ServerEndpoint(value = "/chat",
        encoders = {EncodeMessage.class})
public class ChatServer {

    public static final Logger logger = LoggerFactory.getLogger(ChatServer.class);

    @OnOpen
    public void init(Session session, EndpointConfig config) {

        logger.info("Session start: " + session.getId());
        logger.info("Thread: " + Thread.currentThread().getId());
    }

    @OnMessage
    public void messageReceive(String message, Session session) {
        logger.info("Session: " + session.getId());
        logger.info("Message receive: " + message);
        for (Session s : session.getOpenSessions()) {
            try {
                //s.getBasicRemote().sendText(message);
                s.getBasicRemote().sendObject(new Message(1, message, LocalDateTime.now()));
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
        //return "response";
    }

    @OnError
    public void error(Throwable throwable) {
        logger.error(throwable.getMessage());
    }

    @OnClose
    public void close(CloseReason closeReason) {
        logger.info(closeReason.toString());
    }
}
