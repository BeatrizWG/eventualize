package br.edu.uepb.eventualize.evento.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.uepb.eventualize.evento.servico.EventoService;
import br.edu.uepb.eventualize.modelo.Evento;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PutMapping("/{id}")
    public ResponseEntity<Evento> editarEvento(
            @PathVariable Long id,
            @RequestBody Evento evento) {

        Evento eventoAtualizado = eventoService.editarEvento(id, evento);

        if (eventoAtualizado != null) {
            return ResponseEntity.ok(eventoAtualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEvento(@PathVariable Long id) {
        eventoService.excluirEvento(id);
        return ResponseEntity.noContent().build();
    }
}
