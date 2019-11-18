package br.cesed.si.pp.controller.dto;

import java.sql.Date;

import br.cesed.si.pp.model.Professor;

public class DetalhesDoProfessorDto {

	private Long matricula;
	private String nome;
	private String endereco;
	private Date dtNascimento;
	private Double salario;

	public DetalhesDoProfessorDto(Professor professor) {
		this.matricula = professor.getMatricula();
		this.nome = professor.getNome();
		this.endereco = professor.getEndereco();
		this.dtNascimento = professor.getDtNascimento();
		this.salario = professor.getSalario();
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

	public Double getSalario() {
		return salario;
	}

}
