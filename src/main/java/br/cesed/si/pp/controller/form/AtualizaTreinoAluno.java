package br.cesed.si.pp.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.model.Treino;
import br.cesed.si.pp.repository.AlunoRepository;
import br.cesed.si.pp.repository.TreinoRepository;

public class AtualizaTreinoAluno {

	@NotNull
	@NotEmpty
	private Long treinoId;

	public Long getTreinoId() {
		return treinoId;
	}

	public void setTreinoId(Long treinoId) {
		this.treinoId = treinoId;
	}

	public Aluno atualizar(Long id, AlunoRepository alunoRepository, TreinoRepository treinoRepository) {

		Aluno aluno = alunoRepository.getOne(id);
		Treino treino = treinoRepository.getOne(treinoId);

		aluno.setTreino(treino);

		return aluno;

	}

}
