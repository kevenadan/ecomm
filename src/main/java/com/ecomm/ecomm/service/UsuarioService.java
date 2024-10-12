package com.ecomm.ecomm.service;

import java.util.List;

import com.ecomm.ecomm.dto.UsuarioSearchFormDTO;
import com.ecomm.ecomm.enums.Role;
import com.ecomm.ecomm.model.Usuario;

public interface UsuarioService {
	public void saveUser(Usuario user);
	
	public Usuario findByUsername(String username);
	
	public void updateUser(Usuario usuario);
	
	public int countByRole(Role role);

	public List<Usuario> searchUsers(UsuarioSearchFormDTO searchForm);

	public boolean deleteByUsername(String username);
}
