package br.unisul.farmacia.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.farmacia.domain.Cliente;
import br.unisul.farmacia.domain.ProdutoVenda;
import br.unisul.farmacia.domain.Venda;
import br.unisul.farmacia.repositories.ProdutoVendaRepository;
import br.unisul.farmacia.repositories.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository repo;

	@Autowired
	private ProdutoVendaRepository produtoVendaRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteService clienteService;

	public Venda buscar(Integer id) {
		Optional<Venda> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public Venda insert(Venda obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj = repo.save(obj);

		for (ProdutoVenda ip : obj.getItens()) {
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setValor(ip.getProduto().getValor());
			ip.setVenda(obj);
			obj.getCliente().setSaldoDevedor(obj.getCliente().getSaldoDevedor()+obj.getValorTotal()); 
		}
		produtoVendaRepository.saveAll(obj.getItens());
		return obj;
	}

	public List<Venda> findByCliente(Integer idCliente) {
		Cliente cliente = clienteService.find(idCliente);
		return repo.findByCliente(cliente);
	}
}
