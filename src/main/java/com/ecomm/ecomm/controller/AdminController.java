package com.ecomm.ecomm.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomm.ecomm.dto.UsuarioSearchFormDTO;
import com.ecomm.ecomm.enums.Role;
import com.ecomm.ecomm.model.Usuario;
import com.ecomm.ecomm.service.UsuarioService;

@Controller
public class AdminController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/admin")
	public String adminPage(Model model, Principal principal) {
		
		int totalAdmins = usuarioService.countByRole(Role.ROLE_ADMIN);
		int totalClients = usuarioService.countByRole(Role.ROLE_CLIENT);
		String username = principal.getName();
		
		model.addAttribute("username", username);
		model.addAttribute("totalAdmins", totalAdmins);
		model.addAttribute("totalClients", totalClients);
		
		return "admin";
	}
	
	@GetMapping("/admin/register-user")
	public String registerPage(Model model) {
//		String username = principal.getName();
//		model.addAttribute("username", username);
		model.addAttribute("usuario", new Usuario());
		return "register-user";
	}
	
	@GetMapping("/admin/edit-user")
	public String editUser(Model model) {
		model.addAttribute("searchForm", new Usuario());
		return "search-user";
	}
	@GetMapping("/admin/view-user")
	public String viewUser(Model model) {
	    model.addAttribute("searchForm", new UsuarioSearchFormDTO());
	    model.addAttribute("users", Collections.emptyList());
	    return "view-user";
	}

	@PostMapping("/admin/view-user")
	public String searchUser(@ModelAttribute("searchForm") UsuarioSearchFormDTO searchForm, Model model) {
	    List<Usuario> usuarios = usuarioService.searchUsers(searchForm);
	    model.addAttribute("users", usuarios);
	    return "view-user";
	}
	
    // Método para exibir o formulário de exclusão
    @GetMapping("admin/delete-user")
    public String showDeleteForm() {
        return "deleteUserForm";
    }

    // Método para processar a exclusão do usuário pelo username
    @PostMapping("admin/delete-user")
    public String deleteUserByUsername(@RequestParam("username") String username, Model model) {
        boolean deleted = usuarioService.deleteByUsername(username);

        if (deleted) {
            model.addAttribute("message", "Usuário excluído com sucesso!");
        } else {
            model.addAttribute("message", "Usuário não encontrado.");
        }
        return "deleteUserForm";
    }

}