package br.unisul.farmacia.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.unisul.farmacia.domain.Cliente;
import br.unisul.farmacia.domain.ProdutoVenda;

public class VendaInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private Set<ProdutoVenda> itens = new HashSet<>();

	public VendaInsertDTO() {

	}

	public VendaInsertDTO(VendaInsertDTO obj) {

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ProdutoVenda> getItens() {
		return itens;
	}

	public void setItens(Set<ProdutoVenda> itens) {
		this.itens = itens;
	}
	
}