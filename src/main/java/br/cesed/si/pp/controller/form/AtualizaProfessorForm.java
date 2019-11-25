package br.cesed.si.pp.controller.form;

import br.cesed.si.pp.model.Professor;
import br.cesed.si.pp.repository.ProfessorRepository;

public class AtualizaProfessorForm {

	private String nome;
	private String endereco;

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

	public Professor atualizar(Long matricula, ProfessorRepository professorRepository) {
		
		Professor professor = professorRepository.getOne(matricula);
		
		professor.setNome(nome);
		professor.setEndereco(endereco);
		
		return professor;
	}

	

}
