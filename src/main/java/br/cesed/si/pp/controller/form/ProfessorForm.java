package br.cesed.si.pp.controller.form;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.cesed.si.pp.model.Professor;

public class ProfessorForm {

	@NotNull @NotEmpty
	private String nome;
	private String endereco;
	private Date dtNascimento;
	private Double salario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Professor converter() {
		return new Professor(nome, endereco, dtNascimento, salario);
	}

}
