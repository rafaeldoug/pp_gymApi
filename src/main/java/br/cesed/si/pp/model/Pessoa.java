package br.cesed.si.pp.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.cesed.si.pp.model.enums.RoleUsuario;

@MappedSuperclass
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matricula;
	private String nome;
	private String endereco;
	private Date dtNascimento;
	private String email;
	private String senha;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfil_pessoa")
	private Set<Integer> tipoUsuario = new HashSet<Integer>();

	public Pessoa() {
	}

	public Pessoa(String nome, String endereco, Date dtNascimento, String email, String senha,
			Set<Integer> tipoUsuario) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.dtNascimento = dtNascimento;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}

	public Set<RoleUsuario> getTipo() {
		return tipoUsuario.stream().map(x -> RoleUsuario.toEnum(x)).collect(Collectors.toSet());
	}

	public void addTipo(RoleUsuario tipo) {
		this.tipoUsuario.add(tipo.getCod());
	}

	public Long getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Set<Integer> getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Set<Integer> tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
