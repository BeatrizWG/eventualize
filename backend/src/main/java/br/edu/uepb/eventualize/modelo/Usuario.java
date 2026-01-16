package br.edu.uepb.eventualize.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
@Data
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    private String tipo;

	public Long getIdUsuario() {return idUsuario;}

	public String getNome() {return nome;}

	public void setNome(String nome) {this.nome = nome;}

	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}

	public String getSenha() {return senha;}

	public void setSenha(String senha) {this.senha = senha;}

	public String getTipo() {return tipo;}

	public void setTipo(String tipo) {this.tipo = tipo;}
    
    
    
}
