package br.com.learning.aluraforum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.learning.aluraforum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	List<Topico> findByCurso_Nome(String nomeCurso);

}
