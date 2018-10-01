package com.summer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {
	@RequestMapping("/")
	public String home() {
		return "static/index";
	}
	
	@RequestMapping("/ptf")
	public String ptf() {
		return "static/ptf/index";
	}
}
 