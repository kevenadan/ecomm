package com.ecomm.ecomm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecomm.ecomm.model.Usuario;
import com.ecomm.ecomm.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class RegistationUserAdminController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/admin/register-user")
	public String registerUser(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
		if( result.hasErrors()) {
			return "register-user";
		}
		
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioService.saveUser(usuario);
		return "redirect:/admin/user-details?username=" + usuario.getUsername();
	}
}
