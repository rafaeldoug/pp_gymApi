package br.cesed.si.pp.model;

import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class Professor extends Pessoa {

	private Double salario;
	
	public Professor() {
		super();
	}

	public Professor(String nome, String endereco, Date dtNascimento, Double salario) {
		super(nome, endereco, dtNascimento);
		this.salario = salario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((salario == null) ? 0 : salario.hashCode());
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
		Professor other = (Professor) obj;
		if (salario == null) {
			if (other.salario != null)
				return false;
		} else if (!salario.equals(other.salario))
			return false;
		return true;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

}
