package com.jsp.project.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import lombok.extern.log4j.Log4j2;

@Component // 해당 클래스가 Spring의 컴포넌트로 등록되어야함을 표시

public class WebSocketHandler extends TextWebSocketHandler {

	// 1:N 의 관계 (서버 1개에 여러 클라이언트가 접속)	
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	// 서버로 접속한 모든 클라이언트와 세션을 유지한다.
	
	
	@Override
	protected void  handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	
		String msg = message.getPayload();
		for (String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				wss.sendMessage(new TextMessage(msg));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//클라이언트가 접속할 경우 
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
	}
	
	
	//클라이언트가 접속을 종료할 경우
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,CloseStatus Sta) throws Exception {
		
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, Sta);
	}
	
	
}
