package br.cesed.si.pp.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.cesed.si.pp.controller.dto.AlunoDto;
import br.cesed.si.pp.controller.dto.DetalhesDoAlunoDto;
import br.cesed.si.pp.controller.form.AlunoForm;
import br.cesed.si.pp.controller.form.AtualizaAlunoForm;
import br.cesed.si.pp.controller.form.AtualizaTreinoAluno;
import br.cesed.si.pp.model.Aluno;
import br.cesed.si.pp.repository.AlunoRepository;
import br.cesed.si.pp.repository.TreinoRepository;
import br.cesed.si.pp.repository.UsuarioRepository;

@RestController
@RequestMapping("/alunos")
public class AlunosController {
	
	private static final int INIT_PAGE = 0;
	private static final int ITEMS_SIZE = 10;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TreinoRepository treinoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public Page<AlunoDto> lista(@RequestParam(required = false) String nome) {
		
		Pageable paginacao = PageRequest.of(INIT_PAGE, ITEMS_SIZE);

		if (nome == null) {
			Page<Aluno> alunos = alunoRepository.findAll(paginacao);
			return AlunoDto.converter(alunos);
		} else {
			Page<Aluno> alunos = alunoRepository.findByNome(nome, paginacao);
			return AlunoDto.converter(alunos);
		}

	}

	@PostMapping
	@Transactional
	public ResponseEntity<AlunoDto> cadastrar(@RequestBody @Valid AlunoForm form, UriComponentsBuilder uriBuilder) {
		Aluno aluno = form.converter(treinoRepository, usuarioRepository);
		alunoRepository.save(aluno);

		URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getMatricula()).toUri();
		return ResponseEntity.created(uri).body(new AlunoDto(aluno));
	}

	@GetMapping("/{matricula}")
	public ResponseEntity<DetalhesDoAlunoDto> detalhar(@PathVariable Long matricula) {
		Optional<Aluno> aluno = alunoRepository.findById(matricula);
		if (aluno.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoAlunoDto(aluno.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{matricula}")
	@Transactional
	public ResponseEntity<AlunoDto> atualizar(@PathVariable Long matricula,
			@RequestBody @Valid AtualizaAlunoForm form) {

		Optional<Aluno> optional = alunoRepository.findById(matricula);
		if (optional.isPresent()) {
			Aluno aluno = form.atualizar(matricula, alunoRepository, treinoRepository);
			return ResponseEntity.ok(new AlunoDto(aluno));
		}

		return ResponseEntity.notFound().build();

	}
	
	@PatchMapping("/{matricula}")
	@Transactional
	public ResponseEntity<AlunoDto> atualizarTreino(@PathVariable Long matricula, @RequestBody @Valid AtualizaTreinoAluno form) {
		
		Optional<Aluno> optional = alunoRepository.findById(matricula);
		if(optional.isPresent()) {
			Aluno aluno = form.atualizar(matricula, alunoRepository, treinoRepository);
			return ResponseEntity.ok(new AlunoDto(aluno));
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{matricula}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long matricula) {

		Optional<Aluno> optional = alunoRepository.findById(matricula);
		if (optional.isPresent()) {
			alunoRepository.deleteById(matricula);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}
}
