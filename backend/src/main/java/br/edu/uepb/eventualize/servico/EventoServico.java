package br.edu.uepb.eventualize.evento.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uepb.eventualize.evento.repositorio.EventoRepository;
import br.edu.uepb.eventualize.modelo.Evento;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    
    public Evento editarEvento(Long id, Evento eventoAtualizado) {
        Optional<Evento> eventoExistente = eventoRepository.findById(id);

        if (eventoExistente.isPresent()) {
            Evento evento = eventoExistente.get();

            evento.setTitulo(eventoAtualizado.getTitulo());
            evento.setData(eventoAtualizado.getData());
            evento.setLocal(eventoAtualizado.getLocal());
            evento.setDescricao(eventoAtualizado.getDescricao());

            return eventoRepository.save(evento);
        }

        return null; 
    }

    public void excluirEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}

