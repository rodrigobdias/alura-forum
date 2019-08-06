package br.com.learning.aluraforum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.learning.aluraforum.controller.dto.TopicoDto;
import br.com.learning.aluraforum.modelo.Topico;
import br.com.learning.aluraforum.repository.TopicoRepository;

@RestController
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@RequestMapping("/topicos")
	public List<TopicoDto> lista(String nomeCurso) {
		
		List<Topico> topicos = null;
		
		if(nomeCurso == null) {
			topicos = topicoRepository.findAll();			
		}else {
			topicos = topicoRepository.findByCurso_Nome(nomeCurso);
		}
		
		return TopicoDto.converter(topicos);
	}
	
}
