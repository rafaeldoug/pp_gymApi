package br.cesed.si.pp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cesed.si.pp.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	List<Aluno> findByNome(String nome);


}
