package br.cesed.si.pp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cesed.si.pp.controller.dto.AlunoDto;
import br.cesed.si.pp.controller.dto.DetalhesAlunoTreinoDto;
import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.model.Professor;
import br.cesed.si.pp.model.Treino;
import br.cesed.si.pp.repository.AlunoRepository;
import br.cesed.si.pp.repository.ProfessorRepository;
import br.cesed.si.pp.repository.TreinoRepository;

@RestController
@RequestMapping("/alunos-treinos")
public class AlunosTreinosController {
	
	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TreinoRepository treinoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@GetMapping
	public List<AlunoDto> lista(String nome) {
			List<Aluno> alunos = alunoRepository.findAll();
			return AlunoDto.converter(alunos);
	}
	
	@GetMapping("/{matricula}")
	public DetalhesAlunoTreinoDto detalhar(@PathVariable Long matricula) {
		Aluno aluno = alunoRepository.getOne(matricula);
		Treino treino = treinoRepository.getOne(aluno.getTreino().getId());
		Professor professor = professorRepository.getOne(treino.getProfessor().getMatricula());
		
		return new DetalhesAlunoTreinoDto(aluno, treino, professor);
	}
}
