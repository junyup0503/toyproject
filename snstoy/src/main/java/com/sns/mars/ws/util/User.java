package com.sns.mars.ws.util;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.EqualsAndHashCode;
import lombok.Getter;


//사용자
@EqualsAndHashCode(of = {"webSocketSession"})
public class User {
	@Getter
	private WebSocketSession webSocketSession;
	
	public void setWebSocketsession(WebSocketSession webSocketSession) {
		this.webSocketSession = webSocketSession;
		Map<String, Object> attr = webSocketSession.getAttributes();
		this.memberId = (String) attr.get("login");
	}
	
	@Getter
	private String memberId;
	
	
	public User(WebSocketSession webSocketSession) {
		this.setWebSocketsession(webSocketSession);
		
	}
	
	public boolean isMember() {
		return this.memberId != null;
	}
	
	public void send(TextMessage message) throws IOException {
		webSocketSession.sendMessage(message);
	}
	
}
