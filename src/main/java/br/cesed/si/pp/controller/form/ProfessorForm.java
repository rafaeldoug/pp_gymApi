package br.cesed.si.pp.controller.form;

import java.sql.Date;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.cesed.si.pp.model.Professor;
import br.cesed.si.pp.model.Usuario;
import br.cesed.si.pp.repository.UsuarioRepository;

public class ProfessorForm {

	@NotNull @NotEmpty
	private String nome;
	private String endereco;
	private Date dtNascimento;
	private Double salario;
	private String nomeUsuario;
	private String senha;

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
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Professor converter(UsuarioRepository usuarioRepository) {
		Usuario usuario = new Usuario(nomeUsuario, senha);
		usuarioRepository.save(usuario);
		Optional<Usuario> novoUsuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
		
		return new Professor(nome, endereco, dtNascimento, salario, novoUsuario.get());
	}

}
