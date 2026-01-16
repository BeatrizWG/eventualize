package br.edu.uepb.eventualize.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "organizadores")
@Data
public class Organizador extends Usuario {

    private String matricula;

    @OneToMany(mappedBy = "organizador")
    private List<Evento> eventosCriados;

	public String getMatricula() {return matricula;}

	public void setMatricula(String matricula) {this.matricula = matricula;}

	public List<Evento> getEventosCriados() {return eventosCriados;}

	public void setEventosCriados(List<Evento> eventosCriados) {this.eventosCriados = eventosCriados;}
	
}
