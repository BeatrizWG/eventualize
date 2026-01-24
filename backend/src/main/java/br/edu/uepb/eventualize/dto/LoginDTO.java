package br.edu.uepb.eventualize.dto;

import jakarta.validation.constraints.*;

public class LoginDTO {

    @NotBlank(message = "Email não pode ser vazio")
    @Pattern(
    	    regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z.-]+\\.com$",
    	    message = "Formato de email inválido"
    )
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
        message = "Senha deve ter 8 caracteres, letra maiúscula, minúscula, número e caractere especial"
    )
    private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
