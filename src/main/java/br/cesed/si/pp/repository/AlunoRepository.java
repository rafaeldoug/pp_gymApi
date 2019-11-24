package br.cesed.si.pp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.cesed.si.pp.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Page<Aluno> findByNome(String nome, Pageable paginacao);

}
