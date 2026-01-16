package br.edu.uepb.eventualize.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inscricoes_evento")
@Data
public class InscricaoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscricao;

    private String status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Participante usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

	public Long getIdInscricao() {return idInscricao;}

	public String getStatus() {return status;}

	public void setStatus(String status) {this.status = status;}

	public Participante getUsuario() {return usuario;}

	public void setUsuario(Participante usuario) {this.usuario = usuario;}

	public Evento getEvento() {return evento;}

	public void setEvento(Evento evento) {this.evento = evento;}
        
}
