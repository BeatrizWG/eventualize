package br.edu.uepb.eventualize.DTO;

import br.edu.uepb.eventualize.dto.CadastroEventoDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CadastroEventoDTOTest {

    private final Validator validator =
            Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void deveSerValido() {
        CadastroEventoDTO dto = new CadastroEventoDTO(
                "Festa",
                LocalDate.now().plusDays(1),
                "UEPB",
                "Descricao",
                1L
        );

        Set<ConstraintViolation<CadastroEventoDTO>> erros = validator.validate(dto);

        assertTrue(erros.isEmpty());
    }

    @Test
    void deveSerInvalido() {
        CadastroEventoDTO dto = new CadastroEventoDTO(
                "",
                LocalDate.now().minusDays(1),
                "",
                "a".repeat(600),
                null
        );

        Set<ConstraintViolation<CadastroEventoDTO>> erros = validator.validate(dto);

        assertFalse(erros.isEmpty());
    }
}