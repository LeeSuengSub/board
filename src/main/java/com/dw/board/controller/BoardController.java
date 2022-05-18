package com.dw.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BoardController {

	@GetMapping("/home")
	public String callHomepage() {
		return "index";
	}
}
