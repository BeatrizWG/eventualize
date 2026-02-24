package br.edu.uepb.eventualize.servico;

import br.edu.uepb.eventualize.dto.CadastroEventoDTO;
import br.edu.uepb.eventualize.modelo.Evento;
import br.edu.uepb.eventualize.repositorio.EventoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}