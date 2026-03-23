package br.edu.uepb.eventualize.DTO;

import br.edu.uepb.eventualize.dto.LoginDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDTOTest {

    private final Validator validator =
            Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void loginValido() {

        LoginDTO dto = new LoginDTO();

        dto.setEmail("teste@email.com");
        dto.setSenha("Senha@123");

        Set<ConstraintViolation<LoginDTO>> erros =
                validator.validate(dto);

        assertTrue(erros.isEmpty());
    }

    @Test
    void loginInvalido() {

        LoginDTO dto = new LoginDTO();

        dto.setEmail("errado");
        dto.setSenha("123");

        Set<ConstraintViolation<LoginDTO>> erros =
                validator.validate(dto);

        assertFalse(erros.isEmpty());
    }
}