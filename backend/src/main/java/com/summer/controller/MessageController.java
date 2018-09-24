package com.summer.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.summer.dto.Message;


@RestController
public class MessageController {

	@MessageMapping("/talk")
	@SendTo("/topic/nonsense")
	public Message sendNonsense(Message message) {
		return new Message(" " + HtmlUtils.htmlEscape(message.getMessage()) + " ");
	}

}
