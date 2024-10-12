package com.ecomm.ecomm.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecomm.ecomm.model.Produto;
import com.ecomm.ecomm.service.ProdutoService;

@Controller
public class homeController {
	
	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/")
	public String homePage(Model model, Principal principal) {
		
		String username = (principal !=null) ? principal.getName() : "Guest";
		
		List<Produto> produtos = produtoService.listarProdutos();
		model.addAttribute("produtos", produtos);
		model.addAttribute("username", username);
		return "home";
	}
}
