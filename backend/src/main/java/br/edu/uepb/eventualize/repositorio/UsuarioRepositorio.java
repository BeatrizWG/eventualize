package br.edu.uepb.eventualize.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uepb.eventualize.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmailAndSenha(String email, String senha);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(Long idUsuario);
    
}