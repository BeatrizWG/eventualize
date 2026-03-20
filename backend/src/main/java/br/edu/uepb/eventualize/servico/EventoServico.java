package br.edu.uepb.eventualize.servico;

import br.edu.uepb.eventualize.dto.CadastroEventoDTO;
import br.edu.uepb.eventualize.modelo.Evento;
import br.edu.uepb.eventualize.repositorio.EventoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServico {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    public Evento salvarEvento(CadastroEventoDTO dto) {
        // Criando o objeto da entidade a partir dos dados do DTO
        Evento evento = new Evento();
        evento.setTitulo(dto.titulo());
        evento.setData(dto.data());
        evento.setLocal(dto.local());
        evento.setDescricao(dto.descricao());

        return eventoRepositorio.save(evento);
    }

    public Evento editarEvento(Long id, CadastroEventoDTO dto) {
        Evento evento = eventoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado com ID: " + id));

        evento.setTitulo(dto.titulo());
        evento.setData(dto.data());
        evento.setLocal(dto.local());
        evento.setDescricao(dto.descricao());

        return eventoRepositorio.save(evento);
    }

    public void excluirEvento(Long id) {
        if (!eventoRepositorio.existsById(id)) {
            throw new RuntimeException("Evento não encontrado com ID: " + id);
        }
        eventoRepositorio.deleteById(id);
    }
}