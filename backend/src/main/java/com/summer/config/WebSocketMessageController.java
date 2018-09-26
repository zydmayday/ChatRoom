package com.summer.config;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketMessageController {

	@MessageMapping("/talk")
	@SendTo("/topic/nonsense")
	public Nonsense handleNonsense(Nonsense message) {
		return new Nonsense(HtmlUtils.htmlEscape(message.getMessage()));
	}
}
