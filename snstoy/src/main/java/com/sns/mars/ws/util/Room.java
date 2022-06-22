package com.sns.mars.ws.util;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


public class Room {
	private Set<User> users = new CopyOnWriteArraySet<>();
	
	
	//기능
	public boolean enter(User user) {
		return users.add(user);
	}
	
	
	public boolean leave(User user) {
		return users.remove(user);
	}
	
	public boolean contains(User user) {
		return users.contains(user);
	}
	
	
	
	public void broadcast(TextMessage message) throws IOException {
		for(User user : users) {
			user.send(message);
		}
	}
}
