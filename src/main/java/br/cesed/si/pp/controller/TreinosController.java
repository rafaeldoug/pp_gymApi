package br.cesed.si.pp.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.cesed.si.pp.controller.dto.DetalhesDoTreinoDto;
import br.cesed.si.pp.controller.dto.TreinoDto;
import br.cesed.si.pp.controller.form.TreinoForm;
import br.cesed.si.pp.model.Treino;
import br.cesed.si.pp.repository.ProfessorRepository;
import br.cesed.si.pp.repository.TreinoRepository;

@RestController
@RequestMapping("/treinos")
public class TreinosController {
	
	@Autowired
	private TreinoRepository treinoRepository;

	@Autowired
	private ProfessorRepository professorRepository;
	
	@GetMapping
	public List<TreinoDto> lista() {
		List<Treino> treinos = treinoRepository.findAll();
		return  TreinoDto.converter(treinos);
	}

	@PostMapping
	public ResponseEntity<TreinoDto> cadastrar(@RequestBody @Valid TreinoForm form, UriComponentsBuilder uriBuilder) {
		Treino treino = form.converter(professorRepository);
		treinoRepository.save(treino);
		
		URI uri = uriBuilder.path("/treinos/{id}").buildAndExpand(treino.getId()).toUri();
		return ResponseEntity.created(uri).body(new TreinoDto(treino));
	}
	
	@GetMapping("/{id}")
	public DetalhesDoTreinoDto detalhar(@PathVariable Long id) {
		Treino treino = treinoRepository.getOne(id);
		return new DetalhesDoTreinoDto(treino);
	}

}
