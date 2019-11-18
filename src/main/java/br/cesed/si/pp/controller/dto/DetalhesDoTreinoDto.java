package br.cesed.si.pp.controller.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.cesed.si.pp.model.Treino;

public class DetalhesDoTreinoDto {

	private Long id;
	private Date dtIni;
	private String nomeProfessor;
	private List<ExercicioDto> exercicios;

	public DetalhesDoTreinoDto(Treino treino) {
		this.id = treino.getId();
		this.dtIni = treino.getDtIni();
		this.nomeProfessor = treino.getProfessor().getNome();
		this.exercicios = new ArrayList<>();
		this.exercicios.addAll(treino.getExercicios().stream().map(ExercicioDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public Date getDtIni() {
		return dtIni;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public List<ExercicioDto> getExercicios() {
		return exercicios;
	}

}
