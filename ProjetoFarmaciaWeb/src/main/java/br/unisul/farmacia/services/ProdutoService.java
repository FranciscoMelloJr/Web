package br.unisul.farmacia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.farmacia.domain.Produto;
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
	
}
