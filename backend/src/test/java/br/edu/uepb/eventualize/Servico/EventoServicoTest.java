package br.edu.uepb.eventualize.Servico;

import br.edu.uepb.eventualize.dto.CadastroEventoDTO;
import br.edu.uepb.eventualize.modelo.Evento;
import br.edu.uepb.eventualize.repositorio.EventoRepositorio;
import br.edu.uepb.eventualize.servico.EventoServico;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventoServicoTest {

    @Mock
    private EventoRepositorio eventoRepositorio;

    @InjectMocks
    private EventoServico eventoServico;

    @Test
    void deveSalvarEvento() {
        CadastroEventoDTO dto = new CadastroEventoDTO(
                "Festa",
                LocalDate.now().plusDays(1),
                "UEPB",
                "Descricao",
                1L
        );

        Evento evento = new Evento();
        evento.setTitulo("Festa");

        when(eventoRepositorio.save(any(Evento.class))).thenReturn(evento);

        Evento resultado = eventoServico.salvarEvento(dto);

        assertNotNull(resultado);
        assertEquals("Festa", resultado.getTitulo());
    }

    @Test
    void deveEditarEvento() {
        CadastroEventoDTO dto = new CadastroEventoDTO(
                "Novo",
                LocalDate.now(),
                "UEPB",
                "Desc",
                1L
        );

        Evento evento = new Evento();
        evento.setTitulo("Antigo");

        when(eventoRepositorio.findById(1L)).thenReturn(Optional.of(evento));
        when(eventoRepositorio.save(any())).thenReturn(evento);

        Evento resultado = eventoServico.editarEvento(1L, dto);

        assertEquals("Novo", resultado.getTitulo());
    }

    @Test
    void deveLancarErroAoEditarEventoInexistente() {
        CadastroEventoDTO dto = new CadastroEventoDTO(
                "Novo",
                LocalDate.now(),
                "UEPB",
                "Desc",
                1L
        );

        when(eventoRepositorio.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                eventoServico.editarEvento(1L, dto)
        );
    }

    @Test
    void deveExcluirEvento() {
        when(eventoRepositorio.existsById(1L)).thenReturn(true);

        eventoServico.excluirEvento(1L);

        verify(eventoRepositorio).deleteById(1L);
    }

    @Test
    void deveLancarErroAoExcluirEventoInexistente() {
        when(eventoRepositorio.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () ->
                eventoServico.excluirEvento(1L)
        );
    }
}