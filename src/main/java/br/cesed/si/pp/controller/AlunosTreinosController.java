package br.cesed.si.pp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
	
	private static final int INIT_PAGE = 0;
	private static final int ITEMS_SIZE = 10;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TreinoRepository treinoRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@GetMapping
	public Page<AlunoDto> lista(String nome) {
		
		Pageable paginacao = PageRequest.of(INIT_PAGE, ITEMS_SIZE);
		Page<Aluno> alunos = alunoRepository.findAll(paginacao);
		return AlunoDto.converter(alunos);
	}

	@GetMapping("/{matricula}")
	public ResponseEntity<DetalhesAlunoTreinoDto> detalhar(@PathVariable Long matricula) {
		Optional<Aluno> aluno = alunoRepository.findById(matricula);
		Optional<Treino> treino = treinoRepository.findById(aluno.get().getTreino().getId());
		Optional<Professor> professor = professorRepository.findById(treino.get().getProfessor().getId());
		if (aluno.isPresent() && treino.isPresent() && professor.isPresent()) {
			return ResponseEntity.ok(new DetalhesAlunoTreinoDto(aluno.get(), treino.get(), professor.get()));
		}

		return ResponseEntity.notFound().build();
	}
}
