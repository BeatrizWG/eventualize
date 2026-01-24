package br.edu.uepb.eventualize.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "participantes")
@Data
public class Participante extends Usuario {
	
	@Column(unique = true)
    private String matricula;

    @OneToMany(mappedBy = "usuario")
    private List<InscricaoEvento> inscricoes;

	public String getMatricula() {return matricula;}

	public void setMatricula(String matricula) {this.matricula = matricula;}

	public List<InscricaoEvento> getInscricoes() {return inscricoes;}

	public void setInscricoes(List<InscricaoEvento> inscricoes) {this.inscricoes = inscricoes;}  
    
}
