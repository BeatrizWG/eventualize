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
        return ResponseEntity.status(201).body(novoEvento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> editarEvento(
            @PathVariable Long id,
            @RequestBody @Valid CadastroEventoDTO dados) {

        try {
            Evento eventoAtualizado = eventoServico.editarEvento(id, dados);
            return ResponseEntity.ok(eventoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEvento(@PathVariable Long id) {
        try {
            eventoServico.excluirEvento(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}