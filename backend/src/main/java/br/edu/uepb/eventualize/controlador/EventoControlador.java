package br.edu.uepb.eventualize.controlador;

import br.edu.uepb.eventualize.dto.CadastroEventoDTO;
import br.edu.uepb.eventualize.modelo.Evento;
import br.edu.uepb.eventualize.servico.EventoServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoControlador {

    @Autowired
    private EventoServico eventoServico;

    @PostMapping
    public ResponseEntity<Evento> cadastrar(@RequestBody @Valid CadastroEventoDTO dados) {
        Evento novoEvento = eventoServico.salvarEvento(dados);
        return ResponseEntity.ok(novoEvento);
    }
}