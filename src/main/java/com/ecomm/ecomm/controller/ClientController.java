package com.ecomm.ecomm.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

	@GetMapping("/client")
	public String clientPage(Model model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("username", username);
		return "client";
	}
}
