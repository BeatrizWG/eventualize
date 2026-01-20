package br.edu.uepb.eventualize.dto;

import jakarta.validation.constraints.*;

public class CadastroDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 letras")
    @Pattern(
        regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$",
        message = "Nome não pode conter números"
    )
    private String nome;

    @NotBlank(message = "Email não pode ser vazio")
    @Pattern(
    	    regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z.-]+\\.com$",
    	    message = "Email deve conter @ e domínio sem números, terminando com .com"
    )
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
        message = "Senha deve ter 8 caracteres, letra maiúscula, minúscula, número e caractere especial"
    )
    private String senha;

    @NotBlank(message = "Matrícula não pode ser vazia")
    @Pattern(
        regexp = "^(\\d{7}|\\d{9})$",
        message = "Matrícula deve ter 7 dígitos (professor) ou 9 dígitos (aluno)"
    )
    private String matricula;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


}
