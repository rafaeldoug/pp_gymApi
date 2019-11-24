package br.cesed.si.pp.controller.form;

import java.sql.Date;
import java.util.Optional;

import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.model.Treino;
import br.cesed.si.pp.model.Usuario;
import br.cesed.si.pp.model.enums.TipoAluno;
import br.cesed.si.pp.repository.TreinoRepository;
import br.cesed.si.pp.repository.UsuarioRepository;

public class AlunoForm {

	private String nome;
	private String endereco;
	private Date dtNascimento;
	private TipoAluno tipo;
	private Long treinoId;
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

	public TipoAluno getTipo() {
		return tipo;
	}

	public void setTipo(TipoAluno tipo) {
		this.tipo = tipo;
	}

	public Long getTreinoId() {
		return treinoId;
	}

	public void setTreinoId(Long treinoId) {
		this.treinoId = treinoId;
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

	public Aluno converter(TreinoRepository treinoRepository, UsuarioRepository usuarioRepository) {
		Optional<Treino> opt = treinoRepository.findById(treinoId);
		Treino treino = new Treino();
		if(opt.isPresent()) {
			 treino = opt.get();
		} else {
			treino = null;
		}
		Usuario usuario = new Usuario(nomeUsuario, senha);
		usuarioRepository.save(usuario);
		Optional<Usuario> novoUsuario = usuarioRepository.findByNomeUsuario(nomeUsuario);

		return new Aluno(nome, endereco, dtNascimento, tipo, treino, novoUsuario.get());

	}

}
