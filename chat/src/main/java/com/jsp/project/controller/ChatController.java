package com.jsp.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ChatController {

	@GetMapping("/chat") 
	public String getChat() {
		return "chat";
		
	}
}
