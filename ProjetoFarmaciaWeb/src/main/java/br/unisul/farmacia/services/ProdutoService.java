package br.unisul.farmacia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unisul.farmacia.domain.Produto;
import br.unisul.farmacia.domain.enums.TipoRemedio;
import br.unisul.farmacia.dtos.ProdutoDTO;
import br.unisul.farmacia.dtos.ProdutoInsertDTO;
import br.unisul.farmacia.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository rep;

	public Produto find(Integer id) {
		Optional<Produto> obj = rep.findById(id);
		return obj.orElse(null);
	}

	public List<Produto> findAll() {
		return rep.findAll();
	}
	
	public Produto update(Produto obj) {
		Produto newObj = find(obj.getId());
		updateData(newObj, obj);
		return rep.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		rep.deleteById(id);
	}
	
	public Produto fromDTO(ProdutoDTO objDto) {
		return new Produto(objDto.getId(), objDto.getNome(), objDto.getEstoque(),objDto.getValor(), null);
	}

	public Produto fromDTO(ProdutoInsertDTO objDto) {
		return new Produto(null, objDto.getNome(), objDto.getEstoque(), objDto.getValor(), TipoRemedio.toEnum(objDto.getTarja())); 
	}
	
	private void updateData(Produto newObj, Produto obj) {
		newObj.setNome(obj.getNome());
	}
	
	@Transactional
	public Produto insert(Produto obj) {
		obj.setId(null);
		obj = rep.save(obj);
		return obj;
	}
}
