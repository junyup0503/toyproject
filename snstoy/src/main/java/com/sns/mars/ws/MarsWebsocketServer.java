package com.sns.mars.ws;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.mars.ws.util.RoomManager;

public class MarsWebsocketServer extends TextWebSocketHandler {
	
	private RoomManager manager = new RoomManager();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public static final int JOIN = 1, CHAT = 2;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		manager.enterWaitingRoom(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		manager.leave(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
	}
}
