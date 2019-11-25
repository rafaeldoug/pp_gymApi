package br.cesed.si.pp.controller.form;

import java.sql.Date;
import java.util.Optional;

import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.model.Treino;
import br.cesed.si.pp.repository.TreinoRepository;
import lombok.Data;

@Data
public class AlunoForm {

	private String nome;
	private String endereco;
	private Date dtNascimento;
	private String email;
	private String senha;
	private Long treinoId;

	public Aluno converter(TreinoRepository treinoRepository) {
		Optional<Treino> opt = treinoRepository.findById(treinoId);
		Treino treino = new Treino();
		if(opt.isPresent()) {
			 treino = opt.get();
		} else {
			treino = null;
		}

		return new Aluno(nome, endereco, dtNascimento, email, senha, treino);

	}

}
