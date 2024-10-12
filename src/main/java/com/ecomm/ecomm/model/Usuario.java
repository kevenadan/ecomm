package com.ecomm.ecomm.model;

import com.ecomm.ecomm.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome de usuário é obrigatório.")
    @Size(min = 5, max = 15, message = "O nome de usuário deve ter entre 5 e 15 caracteres.")
    private String username;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail fornecido não é válido.")
    private String email;
	
    @Enumerated(EnumType.STRING)
    private Role role;
}

