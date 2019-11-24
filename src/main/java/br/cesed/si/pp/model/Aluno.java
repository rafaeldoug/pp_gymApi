package br.cesed.si.pp.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import br.cesed.si.pp.model.enums.TipoAluno;

@Entity
public class Aluno extends Pessoa {

	@Enumerated(EnumType.STRING)
	private TipoAluno tipo = TipoAluno.PADRAO;
	@OneToOne
	private Treino treino;
	@OneToOne
	private Usuario usuario;

	public Aluno() {
		super();
	}

	public Aluno(String nome, String endereco, Date dtNascimento, TipoAluno tipo, Treino treino, Usuario usuario) {
		super(nome, endereco, dtNascimento);
		this.tipo = tipo;
		this.treino = treino;
		this.usuario = usuario;
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

	public TipoAluno getTipo() {
		return tipo;
	}

	public void setTipo(TipoAluno tipo) {
		this.tipo = tipo;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
