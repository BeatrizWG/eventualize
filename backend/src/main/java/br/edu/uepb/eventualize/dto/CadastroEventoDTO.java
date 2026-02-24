package br.edu.uepb.eventualize.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CadastroEventoDTO(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotNull(message = "A data é obrigatória")
        @Future(message = "A data deve ser futura")
        LocalDate data,

        @NotBlank(message = "O local é obrigatório")
        String local,

        @Size(max = 500)
        String descricao,

        @NotNull(message = "O ID do organizador é obrigatório")
        Long organizadorId
) {
    // Este parêntese ")" acima e esta chave "}" são obrigatórios!
}