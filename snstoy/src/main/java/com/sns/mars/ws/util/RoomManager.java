package com.sns.mars.ws.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.mars.vo.MessageVO;

public class RoomManager {
	//방 목록
	private Map<String, Room> rooms = Collections.synchronizedMap(new HashMap<>());

	//대기실
	private Room waitingRoom = new Room();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	//기능
	public void enterWaitingRoom(WebSocketSession session) {
		User user = new User(session);
		waitingRoom.enter(user);
	}
	
	public void enterRoom(WebSocketSession session,String name) {
		//대기실에 있는 사용자를 꺼내고 삭제한 뒤 이름에 해당하는 방으로 입장시킨다
		//(주의) 방이 만들어져 있는지 검사한다.
		User user = new User(session);
		waitingRoom.leave(user);
		
		if(notExist(name)) {
			createRoom(name);
		}
		getRoom(name).enter(user);
		
	}
	
	public void leave(WebSocketSession session) {
		User user = new User(session);
		if(waitingRoom.contains(user)) {
			waitingRoom.leave(user);
		}
		for(String name : rooms.keySet()) {
			Room room = getRoom(name);
			if(room.contains(user)) {
				room.leave(user);
			}
			
		}
		
	}
	
	public Room getRoom(String name) {
		return rooms.get(name);
	}
	
	public void createRoom(String name) {
		Room room = new Room();
		rooms.put(name, room);
	}
	
	public boolean notExist(String name) {
		return !rooms.containsKey(name);
	}

	public void broadcastRoom(WebSocketSession session, String name, String message) throws IOException {
		User user = new User(session);
		if(!user.isMember()) return;
		
		MessageVO messageVO = MessageVO.builder()
											.memberId(user.getMemberId())
											.text(message)
											.time(new Date())
											.build();
		String json = mapper.writeValueAsString(messageVO);
		TextMessage textMessage = new TextMessage(json);
		getRoom(name).broadcast(textMessage);
	}
	
}
