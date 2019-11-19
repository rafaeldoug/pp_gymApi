package br.cesed.si.pp.controller.dto;

import org.springframework.data.domain.Page;

import br.cesed.si.pp.model.Aluno;

public class AlunoDto {

	private Long matricula;
	private String nome;
	private String tipo;

	public AlunoDto(Aluno aluno) {
		this.matricula = aluno.getMatricula();
		this.nome = aluno.getNome();
		this.tipo = aluno.getTipo().name();
	}

	public Long getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public static Page<AlunoDto> converter(Page<Aluno> alunos) {
		return alunos.map(AlunoDto::new);
	}

}
