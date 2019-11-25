package br.cesed.si.pp.controller.form;

import java.sql.Date;
import java.util.List;

import br.cesed.si.pp.model.Professor;
import br.cesed.si.pp.model.Treino;
import br.cesed.si.pp.repository.ProfessorRepository;

public class TreinoForm {

	private String nomeProfessor;
	private Date dtIni;
	private String descricao;

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public Date getDtIni() {
		return dtIni;
	}

	public void setDtIni(Date dtIni) {
		this.dtIni = dtIni;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Treino converter(ProfessorRepository professorRepository) {
		List<Professor> professores = professorRepository.findByNome(nomeProfessor);
		Professor professor = professores.get(0);
		return new Treino(dtIni, professor, descricao);
	}

}
