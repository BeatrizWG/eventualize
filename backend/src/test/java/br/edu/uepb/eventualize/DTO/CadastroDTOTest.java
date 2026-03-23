package br.edu.uepb.eventualize.DTO;

import br.edu.uepb.eventualize.dto.CadastroDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CadastroDTOTest {

    private final Validator validator =
            Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void cadastroValido() {

        CadastroDTO dto = new CadastroDTO();

        dto.setNome("Joao");
        dto.setEmail("joao@email.com");
        dto.setSenha("Senha@123");
        dto.setMatricula("1234567");

        Set<ConstraintViolation<CadastroDTO>> erros =
                validator.validate(dto);

        assertTrue(erros.isEmpty());
    }

    @Test
    void cadastroInvalido() {

        CadastroDTO dto = new CadastroDTO();

        dto.setNome("1");
        dto.setEmail("errado");
        dto.setSenha("123");
        dto.setMatricula("1");

        Set<ConstraintViolation<CadastroDTO>> erros =
                validator.validate(dto);

        assertFalse(erros.isEmpty());
    }
}