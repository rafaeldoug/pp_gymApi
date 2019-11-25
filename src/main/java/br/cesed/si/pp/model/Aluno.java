package br.cesed.si.pp.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import br.cesed.si.pp.model.enums.RoleUsuario;
import br.cesed.si.pp.model.enums.TipoAluno;
import lombok.Data;

@Data
@Entity
public class Aluno extends Pessoa {

	@Enumerated(EnumType.STRING)
	private TipoAluno tipoAluno = TipoAluno.PADRAO;
	@OneToOne
	private Treino treino;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfil_aluno")
	private Set<Integer> tipoUsuario = new HashSet<Integer>();

	public Aluno() {
	}

	public Aluno(String nome, String endereco, Date dtNascimento, String email, String senha, TipoAluno tipoAluno,
			Treino treino, Set<Integer> tipoUsuario) {
		super(nome, endereco, dtNascimento, email, senha);
		this.tipoAluno = tipoAluno;
		this.treino = treino;
		this.tipoUsuario = tipoUsuario;
	}

	public Set<RoleUsuario> getTipo() {
		return tipoUsuario.stream().map(x -> RoleUsuario.toEnum(x)).collect(Collectors.toSet());
	}

	public void addTipo(RoleUsuario tipo) {
		this.tipoUsuario.add(tipo.getCod());
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
