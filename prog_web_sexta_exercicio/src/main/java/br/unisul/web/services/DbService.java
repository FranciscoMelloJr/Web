package br.unisul.web.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.web.domain.Aluno;
import br.unisul.web.domain.Disciplina;
import br.unisul.web.repositories.AlunoRepository;
import br.unisul.web.repositories.DisciplinaRepository;

@Service
public class DbService {

	@Autowired
	private AlunoRepository aluRep;

	@Autowired
	private DisciplinaRepository discRep;

	public void inicializaBancoDeDados() throws ParseException {

		Aluno aluno1 = new Aluno(null, "Francisco", "francisco@gmail.com");
		Aluno aluno2 = new Aluno(null, "Lucas", "lucas@gmail.com");
		Aluno aluno3 = new Aluno(null, "Vitor", "vitor@gmail.com");

		Disciplina disc1 = new Disciplina(null, "Prog Web", "Primeiro Semestre");
		Disciplina disc2 = new Disciplina(null, "Integrais", "Segundo Semestre");
		Disciplina disc3 = new Disciplina(null, "Grafos", "Terceiro Semestre");

		aluno1.getDisciplinas().addAll(Arrays.asList(disc1, disc2, disc3));
		aluno2.getDisciplinas().addAll(Arrays.asList(disc1, disc2));
		aluno3.getDisciplinas().addAll(Arrays.asList(disc1));

		disc1.getAlunos().addAll(Arrays.asList(aluno1, aluno2, aluno3));
		disc2.getAlunos().addAll(Arrays.asList(aluno1, aluno2));
		disc3.getAlunos().addAll(Arrays.asList(aluno1));

		discRep.saveAll(Arrays.asList(disc1, disc2, disc3));
		aluRep.saveAll(Arrays.asList(aluno1, aluno2, aluno3));
	}

}
