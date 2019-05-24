package br.unisul.web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unisul.web.domain.Aluno;
import br.unisul.web.domain.Disciplina;
import br.unisul.web.dtos.AlunoDTO;
import br.unisul.web.repositories.AlunoRepository;
import br.unisul.web.repositories.DisciplinaRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repo;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	public Aluno find(Integer id) {
		Optional<Aluno> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public List<Aluno> search(String nome, List<Integer> ids) {
		List<Disciplina> disciplinas = disciplinaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndDisciplinasIn(nome, disciplinas);
	}
	/*
	 * public Aluno update(Aluno obj) { Aluno newObj = find(obj.getId());
	 * updateData(newObj, obj); return repo.save(newObj); }
	 * 
	 * public void delete(Integer id) { find(id); repo.deleteById(id); }
	 * 
	 * public List<Aluno> findAll() { return repo.findAll(); }
	 * 
	 * public Aluno fromDTO(AlunoDTO objDto) { return new Aluno(objDto.getId(),
	 * objDto.getNome(), objDto.getEmail()); }
	 * 
	 * private void updateData(Aluno newObj, Aluno obj) {
	 * newObj.setNome(obj.getNome()); newObj.setEmail(obj.getEmail()); }
	 * 
	 * @Transactional public Aluno insert(Aluno obj) { obj.setId(null); obj =
	 * repo.save(obj); disciplinaRepository.saveAll(obj.getDisciplinas()); return
	 * obj; }
	 */

}
