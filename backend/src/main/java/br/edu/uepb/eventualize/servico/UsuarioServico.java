package br.edu.uepb.eventualize.servico;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.uepb.eventualize.dto.CadastroDTO;
import br.edu.uepb.eventualize.modelo.Organizador;
import br.edu.uepb.eventualize.modelo.Participante;
import br.edu.uepb.eventualize.modelo.Usuario;
import br.edu.uepb.eventualize.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServico {

    private final UsuarioRepositorio repositorio;
    private final PasswordEncoder codificadorSenha;

    public UsuarioServico(UsuarioRepositorio repositorio,
                           PasswordEncoder codificadorSenha) {
        this.repositorio = repositorio;
        this.codificadorSenha = codificadorSenha;
    }

    public void cadastrarUsuario(CadastroDTO dto) {

        Usuario usuario;

        int tamanho = dto.getMatricula().length();

        if (tamanho == 7) {
            Organizador organizador = new Organizador();
            organizador.setMatricula(dto.getMatricula());
            organizador.setTipo("ORGANIZADOR");
            usuario = organizador;

        } else if (tamanho == 9) {
            Participante participante = new Participante();
            participante.setMatricula(dto.getMatricula());
            participante.setTipo("PARTICIPANTE");
            usuario = participante;

        } else {
            throw new RuntimeException("Matrícula inválida");
        }

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(codificadorSenha.encode(dto.getSenha()));

        repositorio.save(usuario);
    }
     
    public Usuario autenticarUsuario(String email, String senhaInformada) {

        Usuario usuario = repositorio.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        boolean senhaValida = codificadorSenha.matches(
                senhaInformada,
                usuario.getSenha()
        );

        if (!senhaValida) {
            throw new RuntimeException("Senha inválida");
        }

        return usuario;
    }
}
