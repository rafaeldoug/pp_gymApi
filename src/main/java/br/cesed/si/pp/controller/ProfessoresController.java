package br.cesed.si.pp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.cesed.si.pp.controller.dto.DetalhesDoProfessorDto;
import br.cesed.si.pp.controller.dto.ProfessorDto;
import br.cesed.si.pp.controller.form.AtualizaProfessorForm;
import br.cesed.si.pp.controller.form.ProfessorForm;
import br.cesed.si.pp.model.Professor;
import br.cesed.si.pp.repository.ProfessorRepository;
import br.cesed.si.pp.repository.UsuarioRepository;

@RestController
@RequestMapping("/professores")
public class ProfessoresController {

	@Autowired
	ProfessorRepository professorRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping
	public List<ProfessorDto> lista(String nome) {
		if (nome == null) {
			List<Professor> professores = professorRepository.findAll();
			return ProfessorDto.converter(professores);
		} else {
			List<Professor> professores = professorRepository.findByNome(nome);
			return ProfessorDto.converter(professores);

		}

	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProfessorDto> cadastrar(@RequestBody @Valid ProfessorForm form,
			UriComponentsBuilder uriBuilder) {
		Professor professor = form.converter(usuarioRepository);
		professorRepository.save(professor);

		URI uri = uriBuilder.path("/professores/{matricula}").buildAndExpand(professor.getMatricula()).toUri();
		return ResponseEntity.created(uri).body(new ProfessorDto(professor));

	}

	@GetMapping("/{matricula}")
	public ResponseEntity<DetalhesDoProfessorDto> detalhar(@PathVariable Long matricula) {
		Optional<Professor> professor = professorRepository.findById(matricula);
		if (professor.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoProfessorDto(professor.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{matricula}")
	@Transactional
	public ResponseEntity<ProfessorDto> atualizar(@PathVariable Long matricula,
			@RequestBody @Valid AtualizaProfessorForm form) {

		Optional<Professor> optional = professorRepository.findById(matricula);
		if (optional.isPresent()) {
			Professor professor = form.atualizar(matricula, professorRepository);
			return ResponseEntity.ok(new ProfessorDto(professor));
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{matricula}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long matricula) {

		Optional<Professor> optional = professorRepository.findById(matricula);
		if (optional.isPresent()) {
			professorRepository.deleteById(matricula);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}

}
