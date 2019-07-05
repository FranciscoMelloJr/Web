package br.unisul.farmacia.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.farmacia.domain.Cliente;
import br.unisul.farmacia.domain.Produto;
import br.unisul.farmacia.domain.enums.TipoCliente;
import br.unisul.farmacia.domain.enums.TipoRemedio;
import br.unisul.farmacia.repositories.ClienteRepository;
import br.unisul.farmacia.repositories.ProdutoRepository;

@Service
public class DbService {

	@Autowired
	private ProdutoRepository prodRep;

	@Autowired
	private ClienteRepository clieRep;

	public void inicializaBancoDeDados() throws ParseException {

		//Meus clientes iniciais
		Cliente cli1 = new Cliente(null, "Francisco Mello",TipoCliente.CLIENTE_COM_RECEITA);
		Cliente cli2 = new Cliente(null, "Gustavo Casagrande",TipoCliente.CLIENTE_SEM_RECEITA);
		Cliente cli3 = new Cliente(null, "Luana Silveira",TipoCliente.CLIENTE_SEM_RECEITA);
		
		//Meus produtos iniciais
		Produto p1 = new Produto(null, "Paracetamol", 200, 9.90, TipoRemedio.PRODUTO_SEM_TARJA);
		Produto p2 = new Produto(null, "Dipirona", 150, 4.90, TipoRemedio.PRODUTO_SEM_TARJA);
		Produto p3 = new Produto(null, "Cimetidina", 100, 7.50, TipoRemedio.PRODUTO_SEM_TARJA);
		Produto p4 = new Produto(null, "Dorflex", 300, 10.50, TipoRemedio.PRODUTO_SEM_TARJA);
		Produto p5 = new Produto(null, "Torsilax", 250, 12.50, TipoRemedio.PRODUTO_SEM_TARJA);
		Produto p6 = new Produto(null, "Benegrip", 100, 10.00, TipoRemedio.PRODUTO_SEM_TARJA);
		Produto p7 = new Produto(null, "Nimesulida", 100, 10.00, TipoRemedio.PRODUTO_SEM_TARJA);
		Produto p8 = new Produto(null, "Rivotril", 20, 84.90, TipoRemedio.TARJA_PRETA);
		Produto p9 = new Produto(null, "Ritalina", 15, 74.50, TipoRemedio.TARJA_PRETA);

		clieRep.saveAll(Arrays.asList(cli1, cli2, cli3));
		prodRep.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));

	}
}