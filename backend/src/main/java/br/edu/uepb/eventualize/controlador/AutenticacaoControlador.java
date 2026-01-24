package br.edu.uepb.eventualize.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uepb.eventualize.dto.CadastroDTO;
import br.edu.uepb.eventualize.dto.LoginDTO;
import br.edu.uepb.eventualize.modelo.Usuario;
import br.edu.uepb.eventualize.servico.UsuarioServico;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AutenticacaoControlador {

    private final UsuarioServico servico;

    public AutenticacaoControlador(UsuarioServico servico) {
        this.servico = servico;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(
            @Valid @RequestBody CadastroDTO dto) {

        servico.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public Usuario login(@Valid @RequestBody LoginDTO dto) {
        return servico.autenticarUsuario(dto.getEmail(), dto.getSenha());
    }
}
