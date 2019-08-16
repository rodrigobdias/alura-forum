package br.com.learning.aluraforum.controller;

import java.net.URI;
import java.util.List;

import br.com.learning.aluraforum.controller.dto.DetalhesDoTopicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.learning.aluraforum.controller.dto.TopicoDto;
import br.com.learning.aluraforum.modelo.Topico;
import br.com.learning.aluraforum.repository.CursoRepository;
import br.com.learning.aluraforum.repository.TopicoForm;
import br.com.learning.aluraforum.repository.TopicoRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {
		
		List<Topico> topicos = null;
		
		if(nomeCurso == null) {
			topicos = topicoRepository.findAll();			
		}else {
			topicos = topicoRepository.findByCurso_Nome(nomeCurso);
		}
		
		return TopicoDto.converter(topicos);
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		
		Topico topico = form.converter(cursoRepository);
		
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@GetMapping("/{id}")
	public DetalhesDoTopicoDto detalhar(@PathVariable Long id){

	    Topico topico = topicoRepository.getOne(id);

	    return new DetalhesDoTopicoDto(topico);
    }
}
