package br.edu.uepb.eventualize.servico;

import br.edu.uepb.eventualize.dto.CadastroDTO;
import br.edu.uepb.eventualize.modelo.Usuario;
import br.edu.uepb.eventualize.modelo.Organizador;
import br.edu.uepb.eventualize.repositorio.UsuarioRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServicoTest {

    @Mock
    private UsuarioRepositorio repositorio;

    @Mock
    private PasswordEncoder codificadorSenha;

    @InjectMocks
    private UsuarioServico usuarioServico;

    @Test
    void cadastrarOrganizadorTeste() {

        CadastroDTO dto = new CadastroDTO();
        dto.setNome("Joao");
        dto.setEmail("joao@email.com");
        dto.setSenha("Senha@123");
        dto.setMatricula("1234567"); // 7 = organizador

        when(codificadorSenha.encode(any()))
                .thenReturn("senhaCodificada");

        usuarioServico.cadastrarUsuario(dto);

        verify(repositorio, times(1))
                .save(any());
    }

    @Test
    void cadastrarParticipanteTeste() {

        CadastroDTO dto = new CadastroDTO();
        dto.setNome("Maria");
        dto.setEmail("maria@email.com");
        dto.setSenha("Senha@123");
        dto.setMatricula("123456789"); // 9 = participante

        when(codificadorSenha.encode(any()))
                .thenReturn("senhaCodificada");

        usuarioServico.cadastrarUsuario(dto);

        verify(repositorio, times(1))
                .save(any());
    }

    @Test
    void autenticarUsuarioTeste() {


        Organizador usuario = new Organizador();
        usuario.setEmail("teste@email.com");
        usuario.setSenha("senhaCodificada");

        when(repositorio.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(usuario));

        when(codificadorSenha.matches(
                any(),
                any()
        )).thenReturn(true);

        Usuario resultado =
                usuarioServico.autenticarUsuario(
                        "teste@email.com",
                        "Senha@123"
                );

        assertNotNull(resultado);
    }
}