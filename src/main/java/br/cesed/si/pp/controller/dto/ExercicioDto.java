package br.cesed.si.pp.controller.dto;

import br.cesed.si.pp.model.Exercicio;

public class ExercicioDto {

	private Long id;
	private String nome;

	public ExercicioDto(Exercicio exercicio) {
		this.id = exercicio.getId();
		this.nome = exercicio.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
