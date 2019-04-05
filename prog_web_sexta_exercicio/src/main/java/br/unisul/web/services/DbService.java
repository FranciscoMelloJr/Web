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

		Aluno aluno1 = new Aluno(null, "Francisco", null);
		Aluno aluno2 = new Aluno(null, "Lucas", null);
		Aluno aluno3 = new Aluno(null, "Vitor", null);

		Disciplina disc1 = new Disciplina(null, "Prog Web", null, aluno1);
		Disciplina disc2 = new Disciplina(null, "Integrais", null, aluno2);
		Disciplina disc3 = new Disciplina(null, "Grafos", null, aluno3);

		// instanciar cliente.
		Aluno aluno = new Aluno(null, "Francisco Mello", "franciscomello@gmail.com");

		Disciplina disc = new Disciplina(null, "SAD", "7", aluno);

		aluno1.getDisciplinas().addAll(Arrays.asList(disc1));

		discRep.saveAll(Arrays.asList(disc1, disc2, disc3));
		aluRep.saveAll(Arrays.asList(aluno1, aluno2, aluno3));
	}

}
