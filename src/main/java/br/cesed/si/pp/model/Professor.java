package br.cesed.si.pp.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Professor extends Pessoa {

	private Double salario;

	public Professor() {
	}

	public Professor(String nome, String endereco, Date dtNascimento, String email, String senha,
			Set<Integer> tipoUsuario, Double salario) {
		super(nome, endereco, dtNascimento, email, senha, tipoUsuario);
		this.salario = salario;
	}
	
	
	public Professor(String nome, String endereco, Date dtNascimento, Double salario2, String senha,
			String email) {
	}

	public Double getSalario() {
		return salario;
	}

}
