package br.edu.uepb.eventualize.repositorio;

import br.edu.uepb.eventualize.modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Long> {
}