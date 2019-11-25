package br.cesed.si.pp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cesed.si.pp.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{

	List<Professor> findByNome(String nome);
	Professor findByEmail(String email);

}
