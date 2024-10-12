package com.ecomm.ecomm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomm.ecomm.model.Usuario;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model, @RequestParam(value = "error", required = false) String error) {
		if (error != null) {
			model.addAttribute("error", "Nome de usuário ou senha inválidos.");
		}
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
}
