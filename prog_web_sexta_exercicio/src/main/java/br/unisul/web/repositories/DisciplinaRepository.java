package br.unisul.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unisul.web.domain.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

}
