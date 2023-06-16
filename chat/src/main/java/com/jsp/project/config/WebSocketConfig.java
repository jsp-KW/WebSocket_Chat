package com.jsp.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.jsp.project.Handler.WebSocketHandler;



@Configuration
@EnableWebSocket


public class WebSocketConfig implements WebSocketConfigurer{
	
	@Autowired
	WebSocketHandler handler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry reg) {
		reg.addHandler(handler, "/chating");
	}

}
