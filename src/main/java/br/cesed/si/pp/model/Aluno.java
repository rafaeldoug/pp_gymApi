package br.cesed.si.pp.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import br.cesed.si.pp.model.enums.TipoAluno;
import lombok.Data;

@Data
@Entity
public class Aluno extends Pessoa {

	@Enumerated(EnumType.STRING)
	private TipoAluno tipoAluno = TipoAluno.PADRAO;
	@OneToOne
	private Treino treino;

	public Aluno() {
	}

	public Aluno(String nome, String endereco, Date dtNascimento, String email, String senha, Set<Integer> tipo,
			TipoAluno tipoAluno, Treino treino) {
		super(nome, endereco, dtNascimento, email, senha, tipo);
		this.tipoAluno = tipoAluno;
		this.treino = treino;
	}

	public Aluno(String nome, String endereco, Date dtNascimento, String email, String senha, Treino treino2) {
	}

	public TipoAluno getTipoAluno() {
		return tipoAluno;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTipoAluno(TipoAluno tipoAluno) {
		this.tipoAluno = tipoAluno;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}
	
	

}
