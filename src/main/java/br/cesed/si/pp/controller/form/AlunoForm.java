package br.cesed.si.pp.controller.form;

import java.sql.Date;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.model.Treino;
import br.cesed.si.pp.model.enums.RoleUsuario;
import br.cesed.si.pp.repository.TreinoRepository;

public class AlunoForm {

	private String nome;
	private String endereco;
	private Date dtNascimento;
	private String email;
	private String senha;
	private Long treinoId;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getTreinoId() {
		return treinoId;
	}

	public void setTreinoId(Long treinoId) {
		this.treinoId = treinoId;
	}

	public Aluno converter(TreinoRepository treinoRepository) {
		Optional<Treino> opt = treinoRepository.findById(treinoId);
		Treino treino = new Treino();
		if (opt.isPresent()) {
			treino = opt.get();
		} else {
			treino = null;
		}

		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setEndereco(endereco);
		aluno.setDtNascimento(dtNascimento);
		aluno.setEmail(email);
		aluno.setTreino(treino);
		String novaSenha = new BCryptPasswordEncoder().encode(senha);
		aluno.setSenha(novaSenha);
		aluno.addTipo(RoleUsuario.PADRAO);
		
		return aluno;

	}

}
