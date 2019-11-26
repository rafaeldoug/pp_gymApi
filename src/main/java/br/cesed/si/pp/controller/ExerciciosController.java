package br.cesed.si.pp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.cesed.si.pp.controller.dto.ExercicioDto;
import br.cesed.si.pp.controller.form.AtualizaExercicioForm;
import br.cesed.si.pp.controller.form.ExercicioForm;
import br.cesed.si.pp.model.Exercicio;
import br.cesed.si.pp.repository.ExercicioRepository;

@RestController
@RequestMapping("/exercicios")
public class ExerciciosController {

	@Autowired
	ExercicioRepository exercicioRepository;

	@GetMapping
	public List<ExercicioDto> lista() {
		List<Exercicio> exercicios = exercicioRepository.findAll();
		return ExercicioDto.conveter(exercicios);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_PROFESSOR')")
	@PostMapping
	@Transactional
	public ResponseEntity<ExercicioDto> cadastrar(ExercicioForm form, UriComponentsBuilder uriBuilder) {
		Exercicio exercicio = form.converter();
		exercicioRepository.save(exercicio);

		URI uri = uriBuilder.path("/exercicios/{id}").buildAndExpand(exercicio.getId()).toUri();
		return ResponseEntity.created(uri).body(new ExercicioDto(exercicio));
	}
	
	@PreAuthorize("hasRole('ROLE_PROFESSOR')")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ExercicioDto> atualizar(@PathVariable Long id, @RequestBody AtualizaExercicioForm form) {
		
		Optional<Exercicio> optional = exercicioRepository.findById(id);
		if(optional.isPresent()) {
			Exercicio exercicio = form.atualizar(id, exercicioRepository);
			return ResponseEntity.ok(new ExercicioDto(exercicio));
		}
		
		
		return ResponseEntity.notFound().build();
	}

	@PreAuthorize("hasRole('ROLE_PROFESSOR')")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(Long id) {

		Optional<Exercicio> optional = exercicioRepository.findById(id);
		if (optional.isPresent()) {
			exercicioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
