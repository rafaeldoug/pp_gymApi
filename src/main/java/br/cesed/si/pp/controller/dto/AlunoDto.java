package br.cesed.si.pp.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.cesed.si.pp.model.Aluno;

public class AlunoDto {

	private Long matricula;
	private String nome;
	private String endereco;
	private String tipo;

	public AlunoDto(Aluno aluno) {
		this.matricula = aluno.getMatricula();
		this.nome = aluno.getNome();
		this.endereco = aluno.getEndereco();
		this.tipo = aluno.getTipo().name();
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

	public String getTipo() {
		return tipo;
	}

	public static List<AlunoDto> converter(List<Aluno> alunos) {
		return alunos.stream().map(AlunoDto::new).collect(Collectors.toList());
	}

}
