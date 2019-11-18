package br.cesed.si.pp.controller.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.cesed.si.pp.model.Treino;

public class TreinoDto {
	
	private Long id;
	private Date dtIni;
	private String nomeProfessor;
	
	public TreinoDto(Treino treino) {
		this.id = treino.getId();
		this.dtIni = treino.getDtIni();
		this.nomeProfessor = treino.getProfessor().getNome();
	}
	
	public Long getId() {
		return id;
	}
	public Date getDtIni() {
		return dtIni;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public static List<TreinoDto> converter(List<Treino> treinos) {
		return treinos.stream().map(TreinoDto::new).collect(Collectors.toList());
	}
	
	

}
