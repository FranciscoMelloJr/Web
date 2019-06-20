package br.unisul.web.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.web.domain.Aluno;
import br.unisul.web.domain.Disciplina;
import br.unisul.web.domain.enums.TipoAluno;
import br.unisul.web.repositories.AlunoRepository;
import br.unisul.web.repositories.DisciplinaRepository;

@Service
public class DbService {

	@Autowired
	private AlunoRepository aluRep;

	@Autowired
	private DisciplinaRepository discRep;

	public void inicializaBancoDeDados() throws ParseException {

		Aluno aluno1 = new Aluno(null, "Francisco", TipoAluno.ADOLESCENTE);
		Aluno aluno2 = new Aluno(null, "Lucas", TipoAluno.ADOLESCENTE);
		Aluno aluno3 = new Aluno(null, "Vitor", TipoAluno.INFANTIL);

		aluno1.getEmails().addAll(Arrays.asList("francisco@gmail.com", "francisco@unisul.br", "francisco@outlook.com"));
		aluno2.getEmails().addAll(Arrays.asList("lucas@gmail.com", "lucas@unisul.br", "lucas@outlook.com"));
		aluno3.getEmails().addAll(Arrays.asList("vitor@gmail.com", "vitor@unisul.br", "vitor@outlook.com"));

		Disciplina disc1 = new Disciplina(null, "Prog Web", "Quinto Semestre");
		Disciplina disc2 = new Disciplina(null, "Grafos", "Sexto Semestre");
		Disciplina disc3 = new Disciplina(null, "SAD", "Sétimo Semestre");

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
