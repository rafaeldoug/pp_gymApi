package br.cesed.si.pp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cesed.si.pp.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByNomeUsuario(String nomeUsuario);
}
