package br.com.learning.aluraforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.learning.aluraforum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);


}
