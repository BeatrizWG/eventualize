package br.edu.uepb.eventualize.evento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uepb.eventualize.modelo.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
