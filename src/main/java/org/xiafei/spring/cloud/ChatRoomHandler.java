package org.xiafei.spring.cloud;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class ChatRoomHandler extends TextWebSocketHandler {


    private MultiValueMap<String, WebSocketSession> multiValueMap = new LinkedMultiValueMap<>();
    private final UserNameService userNameService;
    private final ChatRecordService chatRecordService;

    public ChatRoomHandler() {
        this.userNameService = new UserNameService();
        this.chatRecordService = new ChatRecordService();
    }


    @Override
    public void handleTextMessage(WebSocketSession receiveSession, TextMessage message) throws IOException {
        String sendText = buildMessageText(receiveSession, message);
        chatRecordService.storeChatRecord(getSessionGroupKey(receiveSession), sendText);
        for (WebSocketSession sendSession : multiValueMap.get(getSessionGroupKey(receiveSession))) {
            sendSession.sendMessage(new TextMessage(sendText.getBytes()));
        }
    }

    private String buildMessageText(WebSocketSession socketSession, TextMessage message) {
        return String.format("[%tT] %s: %s", new Date(), userNameService.getUserName(socketSession), new String(message.asBytes()));

    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        multiValueMap.add(getSessionGroupKey(session), session);
        if (chatRecordService.getChatRecord(getSessionGroupKey(session)) != null) {
            for (String text : chatRecordService.getChatRecord(getSessionGroupKey(session))) {
                session.sendMessage(new TextMessage(text.getBytes()));
            }
        }

    }

    private String getSessionGroupKey(WebSocketSession socketSession) {
        return Objects.requireNonNull(socketSession.getUri()).getPath();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        userNameService.removeName(session);
        multiValueMap.get(Objects.requireNonNull(session.getUri()).getPath()).removeIf(webSocketSession -> webSocketSession.getId().equalsIgnoreCase(session.getId()));

    }


}