package br.cesed.si.pp.controller.form;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.model.Treino;
import br.cesed.si.pp.model.enums.Tipo;
import br.cesed.si.pp.repository.AlunoRepository;
import br.cesed.si.pp.repository.TreinoRepository;

public class AtualizaAlunoForm {

	@NotNull @NotEmpty
	private String nome;
	private String endereco;
	@NotNull @NotEmpty
	private Date dtNascimento;
	private Tipo tipo;
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Long getTreinoId() {
		return treinoId;
	}

	public void setTreinoId(Long treinoId) {
		this.treinoId = treinoId;
	}

	public Aluno atualizar(Long id, AlunoRepository alunoRepository, TreinoRepository treinoRepository) {
		Aluno aluno = alunoRepository.getOne(id);

		aluno.setNome(this.nome);
		aluno.setEndereco(this.endereco);
		aluno.setDtNascimento(this.dtNascimento);
		aluno.setTipo(this.tipo);
		Treino treino = treinoRepository.getOne(treinoId);
		aluno.setTreino(treino);

		return aluno;
	}

}
