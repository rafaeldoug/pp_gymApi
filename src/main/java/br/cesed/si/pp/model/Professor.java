package br.cesed.si.pp.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import br.cesed.si.pp.model.enums.RoleUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Professor extends Pessoa {

	private Double salario;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfil_professor")
	private Set<Integer> tipoUsuario = new HashSet<Integer>();

	public Professor() {
	}

	public Professor(String nome, String endereco, Date dtNascimento, String email, String senha, Double salario,
			Set<Integer> tipoUsuario) {
		super(nome, endereco, dtNascimento, email, senha);
		this.salario = salario;
		this.tipoUsuario = tipoUsuario;
	}

	public Set<RoleUsuario> getTipo() {
		return tipoUsuario.stream().map(x -> RoleUsuario.toEnum(x)).collect(Collectors.toSet());
	}

	public void addTipo(RoleUsuario tipo) {
		this.tipoUsuario.add(tipo.getCod());
	}

	public Double getSalario() {
		return salario;
	}

	public Set<Integer> getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Set<Integer> tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

}
