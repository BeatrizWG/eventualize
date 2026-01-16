package br.edu.uepb.eventualize.modelo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eventos")
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    private String titulo;
    private LocalDate data;
    private String local;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "organizador_id")
    private Organizador organizador;

    @OneToMany(mappedBy = "evento")
    private List<InscricaoEvento> inscricoes;

	public Long getIdEvento() {return idEvento;}

	public String getTitulo() {return titulo;}

	public void setTitulo(String titulo) {this.titulo = titulo;}

	public LocalDate getData() {return data;}

	public void setData(LocalDate data) {this.data = data;}

	public String getLocal() {return local;}

	public void setLocal(String local) {this.local = local;}

	public String getDescricao() {return descricao;}

	public void setDescricao(String descricao) {this.descricao = descricao;}

	public Organizador getOrganizador() {return organizador;}

	public void setOrganizador(Organizador organizador) {this.organizador = organizador;}

	public List<InscricaoEvento> getInscricoes() {return inscricoes;}

	public void setInscricoes(List<InscricaoEvento> inscricoes) {this.inscricoes = inscricoes;}
    
}
