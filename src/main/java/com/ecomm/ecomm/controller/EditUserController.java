package com.ecomm.ecomm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomm.ecomm.dto.UsuarioDTO;
import com.ecomm.ecomm.model.Usuario;
import com.ecomm.ecomm.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class EditUserController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/admin/search-user")
	public String searchUser(@Valid @ModelAttribute("searchForm") UsuarioDTO searchForm, BindingResult result, Model model) {

		String username = searchForm.getUsername();
		if (result.hasErrors()) {
			return "search-user";
		}
		try {
			Usuario usuario = usuarioService.findByUsername(username);
			model.addAttribute("usuario", usuario);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Usuário não encontrado ou erro na busca.");
			return "search-user";
		}
		return "edit-user";
	}

	@PostMapping("/admin/edit-user")
	public String editUser(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "edit-user";
		}

		model.addAttribute("usuario", usuario);
		usuarioService.updateUser(usuario);
		return "redirect:/admin/user-details?username=" + usuario.getUsername();
	}

	@GetMapping("/admin/user-details")
	public String viewUserDetails(@RequestParam("username") String username, Model model) {
		Usuario usuario = usuarioService.findByUsername(username);

		if (usuario == null) {
			model.addAttribute("errorMessage", "User not found.");
			return "redirect:/admin/edit-user";
		}

		model.addAttribute("usuario", usuario);
		return "user-details";
	}
}
