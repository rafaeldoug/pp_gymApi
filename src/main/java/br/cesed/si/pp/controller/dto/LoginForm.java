package br.cesed.si.pp.controller.dto;

import java.io.Serializable;

public class LoginForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;

	public LoginForm() {
	}

	public LoginForm(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String password) {
		this.senha = password;
	}
}
