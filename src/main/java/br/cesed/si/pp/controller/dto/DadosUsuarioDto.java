package br.cesed.si.pp.controller.dto;

import java.io.Serializable;

public class DadosUsuarioDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;

	private String password;

	public DadosUsuarioDto() {
	}

	public DadosUsuarioDto(String email, String senha) {
		this.email = email;
		this.password = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
