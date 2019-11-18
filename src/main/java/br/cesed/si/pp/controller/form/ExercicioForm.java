package br.cesed.si.pp.controller.form;

import br.cesed.si.pp.model.Exercicio;

public class ExercicioForm {

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

	public Exercicio converter() {
		return new Exercicio(nome, descricao);
	}

}
