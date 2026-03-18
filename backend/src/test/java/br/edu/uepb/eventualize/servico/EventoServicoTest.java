package br.edu.uepb.eventualize.servico;

import br.edu.uepb.eventualize.dto.CadastroEventoDTO;
import br.edu.uepb.eventualize.modelo.Evento;
import br.edu.uepb.eventualize.repositorio.EventoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventoServicoTest {

    @Mock
    private EventoRepositorio eventoRepositorio;

    @InjectMocks
    private EventoServico eventoServico;

    @Test
    void salvarEventoTeste() {

        CadastroEventoDTO dto = new CadastroEventoDTO(
                "Festa",
                LocalDate.now().plusDays(1),
                "UEPB",
                "Descricao",
                1L
        );

        Evento eventoSalvo = new Evento();
        eventoSalvo.setTitulo("Festa");

        when(eventoRepositorio.save(any(Evento.class)))
                .thenReturn(eventoSalvo);

        Evento resultado = eventoServico.salvarEvento(dto);

        assertNotNull(resultado);
        assertEquals("Festa", resultado.getTitulo());

        verify(eventoRepositorio, times(1))
                .save(any(Evento.class));
    }
}