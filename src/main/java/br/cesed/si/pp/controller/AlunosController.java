package br.cesed.si.pp.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.cesed.si.pp.controller.dto.AlunoDto;
import br.cesed.si.pp.controller.dto.DetalhesDoAlunoDto;
import br.cesed.si.pp.controller.form.AlunoForm;
import br.cesed.si.pp.controller.form.AtualizaAlunoForm;
import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.repository.AlunoRepository;
import br.cesed.si.pp.repository.TreinoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunosController {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TreinoRepository treinoRepository;

	@GetMapping
	public List<AlunoDto> lista(String nome) {
		if (nome == null) {
			List<Aluno> alunos = alunoRepository.findAll();
			return AlunoDto.converter(alunos);
		} else {
			List<Aluno> alunos = alunoRepository.findByNome(nome);
			return AlunoDto.converter(alunos);
		}

	}

	@PostMapping
	@Transactional
	public ResponseEntity<AlunoDto> cadastrar(@RequestBody @Valid AlunoForm form, UriComponentsBuilder uriBuilder) {
		Aluno aluno = form.converter(treinoRepository);
		alunoRepository.save(aluno);

		URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getMatricula()).toUri();
		return ResponseEntity.created(uri).body(new AlunoDto(aluno));
	}

	@GetMapping("/{matricula}")
	public DetalhesDoAlunoDto detalhar(@PathVariable Long matricula) {
		Aluno aluno = alunoRepository.getOne(matricula);
		return new DetalhesDoAlunoDto(aluno);
	}
	
	@PutMapping("/{matricula}")
	@Transactional
	public ResponseEntity<AlunoDto> atualizar(@PathVariable Long matricula, @RequestBody @Valid AtualizaAlunoForm form) {
		Aluno aluno = form.atualizar(matricula, alunoRepository, treinoRepository);

		return ResponseEntity.ok(new AlunoDto(aluno));
	}
	
	@DeleteMapping("/{matricula}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long matricula) {
		alunoRepository.deleteById(matricula);
		
		return ResponseEntity.ok().build();
	}
}
