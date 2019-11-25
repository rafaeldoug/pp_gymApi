package br.cesed.si.pp.controller.form;

import java.sql.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.cesed.si.pp.model.Professor;
import br.cesed.si.pp.model.enums.RoleUsuario;

public class ProfessorForm {

	private String nome;
	private String endereco;
	private Date dtNascimento;
	private Double salario;
	private String senha;
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Professor converter() {

		Professor professor = new Professor();
		professor.setNome(nome);
		professor.setEndereco(endereco);
		professor.setDtNascimento(dtNascimento);
		professor.setSalario(salario);
		String novaSenha = new BCryptPasswordEncoder().encode(senha);
		professor.setSenha(novaSenha);
		professor.setEmail(email);
		professor.addTipo(RoleUsuario.ADMIN);
		
		return professor;
	}

}
