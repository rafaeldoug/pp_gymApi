package br.cesed.si.pp.controller.form;

import java.sql.Date;

import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.model.Treino;
import br.cesed.si.pp.model.enums.TipoAluno;
import br.cesed.si.pp.repository.TreinoRepository;

public class AlunoForm {

	private String nome;
	private String endereco;
	private Date dtNascimento;
	private TipoAluno tipo;
	private Long treinoId;

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

	public TipoAluno getTipo() {
		return tipo;
	}

	public void setTipo(TipoAluno tipo) {
		this.tipo = tipo;
	}

	public Long getTreinoId() {
		return treinoId;
	}

	public void setTreinoId(Long treinoId) {
		this.treinoId = treinoId;
	}

	public Aluno converter(TreinoRepository treinoRepository) {
		Treino treino = treinoRepository.getOne(treinoId);
		return new Aluno(nome, endereco, dtNascimento, tipo, treino);
	}

}
