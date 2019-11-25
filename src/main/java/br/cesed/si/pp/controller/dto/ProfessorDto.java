package br.cesed.si.pp.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.cesed.si.pp.model.Professor;

public class ProfessorDto {

	private Long matricula;
	private String nome;
	private String endereco;

	public ProfessorDto(Professor professor) {
		this.matricula = professor.getId();
		this.nome = professor.getNome();
		this.endereco = professor.getEndereco();
	}

	public Long getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public static List<ProfessorDto> converter(List<Professor> professores) {
		return professores.stream().map(ProfessorDto::new).collect(Collectors.toList());
	}

}
