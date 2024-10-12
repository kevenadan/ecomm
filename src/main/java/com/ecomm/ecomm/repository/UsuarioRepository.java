package com.ecomm.ecomm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecomm.ecomm.enums.Role;
import com.ecomm.ecomm.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUsername(String username);

	int countByRole(Role role);
	
	// Busca por username
    Page<Usuario> findByUsername(String username, Pageable pageable);

	// Busca por email
    Page<Usuario> findByEmail(String email, Pageable pageable);

    // Busca por role
    Page<Usuario> findByRole(Role role, Pageable pageable);
    
    @Query(value = "SHOW COLUMNS FROM usuarios LIKE 'role'", nativeQuery = true)
    List<Object[]> findAllRoles();

    // Busca com filtros de username e email sem considerar o role
    Page<Usuario> findByUsernameContainingAndEmailContaining(String username, String email, Pageable pageable);

    // Busca com filtros de username, email e role
    Page<Usuario> findByUsernameContainingAndEmailContainingAndRole(String username, String email, Role role, Pageable pageable);


}