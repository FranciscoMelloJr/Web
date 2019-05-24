package br.unisul.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unisul.web.domain.Aluno;
import br.unisul.web.domain.Disciplina;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

	List<Aluno> findDistinctByNomeContainingAndDisciplinasIn(String nome, List<Disciplina> disciplinas);

}
