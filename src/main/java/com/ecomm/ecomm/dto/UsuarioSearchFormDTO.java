package com.ecomm.ecomm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSearchFormDTO {
	private Long id;
	private String username;
	private String email;
	private String role;
	private Integer limit;
}
