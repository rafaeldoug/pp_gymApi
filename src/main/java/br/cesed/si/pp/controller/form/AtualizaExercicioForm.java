package br.cesed.si.pp.controller.form;

import br.cesed.si.pp.model.Exercicio;
import br.cesed.si.pp.repository.ExercicioRepository;

public class AtualizaExercicioForm {

	private String nome;
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Exercicio atualizar(Long id, ExercicioRepository exercicioRepository) {
		
		Exercicio exercicio = exercicioRepository.getOne(id);
		
		exercicio.setNome(nome);
		exercicio.setDescricao(descricao);
		
		return exercicio;
		
	}
}
