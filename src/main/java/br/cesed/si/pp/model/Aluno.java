package br.cesed.si.pp.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import br.cesed.si.pp.model.enums.Tipo;

@Entity
public class Aluno extends Pessoa {

	@Enumerated(EnumType.STRING)
	private Tipo tipo = Tipo.PADRAO;
	@OneToOne
	private Treino treino;

	public Aluno() {
		super();
	}

	public Aluno(String nome, String endereco, Date dtNascimento, Tipo tipo, Treino treino) {
		super(nome, endereco, dtNascimento);
		this.tipo = tipo;
		this.treino = treino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((treino == null) ? 0 : treino.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (tipo != other.tipo)
			return false;
		if (treino == null) {
			if (other.treino != null)
				return false;
		} else if (!treino.equals(other.treino))
			return false;
		return true;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}

}
