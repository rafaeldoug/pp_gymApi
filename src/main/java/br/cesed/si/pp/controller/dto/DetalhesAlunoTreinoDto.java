package br.cesed.si.pp.controller.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.model.Professor;
import br.cesed.si.pp.model.Treino;

public class DetalhesAlunoTreinoDto {

	private Long matricula;
	private String nomeAluno;
	private Long treinoId;
	private String descricaoTreino;
	private String nomeProfessor;
	private Date dtIniTreino;
	private List<ExercicioDto> exercicios;

	public DetalhesAlunoTreinoDto(Aluno aluno, Treino treino, Professor professor) {
		this.matricula = aluno.getId();
		this.nomeAluno = aluno.getNome();
		this.treinoId = treino.getId();
		this.descricaoTreino = treino.getDescricao();
		this.nomeProfessor = professor.getNome();
		this.dtIniTreino = treino.getDtIni();
		this.exercicios = new ArrayList<>();
		this.exercicios.addAll(treino.getExercicios().stream().map(ExercicioDto::new).collect(Collectors.toList()));
	}

	public Long getMatricula() {
		return matricula;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public Long getTreinoId() {
		return treinoId;
	}

	public String getDescricaoTreino() {
		return descricaoTreino;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public Date getDtIniTreino() {
		return dtIniTreino;
	}

	public List<ExercicioDto> getExercicios() {
		return exercicios;
	}

}
