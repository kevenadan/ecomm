package com.ecomm.ecomm.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecomm.ecomm.dto.UsuarioSearchFormDTO;
import com.ecomm.ecomm.enums.Role;
import com.ecomm.ecomm.model.Usuario;
import com.ecomm.ecomm.repository.UsuarioRepository;
import com.ecomm.ecomm.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void saveUser(Usuario usuario) {
		userRepository.save(usuario);
	}

	@Override
	public Usuario findByUsername(String username) {
		Usuario existingUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found with username: " + username));

		return existingUser;
	}

	@Override
	public void updateUser(Usuario usuario) {
		Usuario existingUser = userRepository.findByUsername(usuario.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found with username: " + usuario.getUsername()));

		existingUser.setUsername(usuario.getUsername());
		existingUser.setEmail(usuario.getEmail());
		existingUser.setPassword(passwordEncoder.encode(usuario.getPassword()));
		existingUser.setRole(usuario.getRole());

		userRepository.save(existingUser);
	}

	@Override
	public int countByRole(Role role) {

		return userRepository.countByRole(role);
	}

	@Override
	public List<Usuario> searchUsers(UsuarioSearchFormDTO searchForm) {
	    Pageable pageable = PageRequest.of(0, searchForm.getLimit() != null ? searchForm.getLimit() : 10);

	    // Caso o ID não seja nulo, realiza a busca por ID
	    if (searchForm.getId() != null ) {
	        return searchById(searchForm.getId());
	    }

	    // Caso o username não seja nulo, realiza a busca por username
	    if (searchForm.getUsername() != null && !searchForm.getUsername().isEmpty()) {
	        return searchByUsername(searchForm.getUsername(), pageable);
	    }

	    // Caso o email não seja nulo, realiza a busca por email
	    if (searchForm.getEmail() != null && !searchForm.getEmail().isEmpty()) {
	        return searchByEmail(searchForm.getEmail(), pageable);
	    }

	    // Caso o role não seja nulo e não seja "ALL", realiza a busca por role
	    Role roleEnum = parseRole(searchForm.getRole());
	    if (roleEnum != null) {
	        return searchByRole(roleEnum, pageable);
	    }

	    // Retorna todos os usuários paginados caso nenhum campo específico seja preenchido
	    List<Usuario> usuarios = userRepository.findAll(pageable).getContent();
	    
	    System.out.println(usuarios.toString());
	    return usuarios;
	}


	// Busca por ID de usuário
	private List<Usuario> searchById(Long id) {
	    return userRepository.findById(id)
	            .map(Collections::singletonList)
	            .orElse(Collections.emptyList());
	}

	// Busca por username
	private List<Usuario> searchByUsername(String username, Pageable pageable) {
	    return userRepository.findByUsername(username, pageable)
	            .getContent();
	}

	// Busca por email
	private List<Usuario> searchByEmail(String email, Pageable pageable) {
	    return userRepository.findByEmail(email, pageable)
	            .getContent();
	}

	// Busca por role
	private List<Usuario> searchByRole(Role role, Pageable pageable) {
	    return userRepository.findByRole(role, pageable)
	            .getContent();
	}

	// Converte a string de role para o enum Role, ignorando "ALL"
	private Role parseRole(String role) {
	    if (role != null && !"ALL".equalsIgnoreCase(role)) {
	        try {
	            return Role.valueOf(role.toUpperCase());
	        } catch (IllegalArgumentException e) {
	            // Log ou tratamento de erro pode ser adicionado aqui
	        }
	    }
	    return null;
	}

	@Override
	public boolean deleteByUsername(String username) {
		Optional<Usuario> usuario = userRepository.findByUsername(username);

        if (usuario.isPresent()) {
            userRepository.delete(usuario.get());
            return true;
        }
		return false;
	}

}
