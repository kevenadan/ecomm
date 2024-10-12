package com.ecomm.ecomm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDTO {
	
	@NotBlank(message = "O nome não pode estar em branco")
	private String username;

}
