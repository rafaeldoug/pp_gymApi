package br.cesed.si.pp.controller.dto;

import java.sql.Date;

import br.cesed.si.pp.model.Aluno;

public class DetalhesDoAlunoDto {

	private Long matricula;
	private String nome;
	private String endereco;
	private Date dtNascimento;
	private String tipo;
	private Long treinoId;

	public DetalhesDoAlunoDto(Aluno aluno) {
		this.matricula = aluno.getMatricula();
		this.nome = aluno.getNome();
		this.endereco = aluno.getEndereco();
		this.dtNascimento = aluno.getDtNascimento();
		this.tipo = aluno.getTipo().name();
		this.treinoId = aluno.getTreino().getId();
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

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public String getTipo() {
		return tipo;
	}

	public Long getTreinoId() {
		return treinoId;
	}

}
