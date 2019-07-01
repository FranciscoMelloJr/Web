package br.unisul.farmacia.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.farmacia.domain.Cliente;
import br.unisul.farmacia.domain.ProdutoVenda;
import br.unisul.farmacia.domain.Venda;
import br.unisul.farmacia.dtos.VendaInsertDTO;
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

	public Venda fromDTO(VendaInsertDTO objDto) {
		return new Venda(null, null,objDto.getCliente(), objDto.getItens()); 
	}
	
	public Venda insert(Venda obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getCliente().getVendas().addAll(Arrays.asList(obj));
		obj.getCliente().setSaldoDevedor(obj.getCliente().getSaldoDevedor()+obj.getValorTotal()); 
		obj = repo.save(obj);

		for (ProdutoVenda pv : obj.getItens()) {
			pv.setProduto(produtoService.find(pv.getProduto().getId()));
			pv.setValor(pv.getProduto().getValor());
			pv.setVenda(obj);
			pv.getProduto().setEstoque(pv.getProduto().getEstoque()-pv.getQuantidade());
		}	
		
		produtoVendaRepository.saveAll(obj.getItens());
		return obj;
	}

	public List<Venda> findByCliente(Integer idCliente) {
		Cliente cliente = clienteService.find(idCliente);
		return repo.findByCliente(cliente);
	}
	
	public List<Venda> findAll() {
		return repo.findAll();
	}
}
