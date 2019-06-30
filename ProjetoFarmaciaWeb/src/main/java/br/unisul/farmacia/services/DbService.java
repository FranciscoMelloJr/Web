package br.unisul.farmacia.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.farmacia.domain.Cliente;
import br.unisul.farmacia.domain.Produto;
import br.unisul.farmacia.domain.ProdutoVenda;
import br.unisul.farmacia.domain.Venda;
import br.unisul.farmacia.domain.enums.TipoCliente;
import br.unisul.farmacia.domain.enums.TipoRemedio;
import br.unisul.farmacia.repositories.ClienteRepository;
import br.unisul.farmacia.repositories.ProdutoRepository;
import br.unisul.farmacia.repositories.ProdutoVendaRepository;
import br.unisul.farmacia.repositories.VendaRepository;

@Service
public class DbService {

	@Autowired
	private ProdutoRepository prodRep;

	@Autowired
	private ClienteRepository clieRep;

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ProdutoVendaRepository produtoVendaRepository;

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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		//Minha vendas já cadastradas de cada cliente
		Venda venda1 = new Venda(null, sdf.parse("05/06/2019 08:47"), cli3);
		Venda venda2 = new Venda(null, sdf.parse("10/06/2019 10:35"), cli2);
		Venda venda3 = new Venda(null, sdf.parse("15/06/2019 12:35"), cli1);

		//Registra a venda para tal cliente respectivamente
		cli1.getVendas().addAll(Arrays.asList(venda3));
		cli2.getVendas().addAll(Arrays.asList(venda2));
		cli3.getVendas().addAll(Arrays.asList(venda1));
		vendaRepository.saveAll(Arrays.asList(venda1, venda2, venda3));

		//Define os produtos para tal venda
		ProdutoVenda produtovenda0 = new ProdutoVenda(venda1, p1, 1);
		ProdutoVenda produtovenda1 = new ProdutoVenda(venda1, p3, 3);
		ProdutoVenda produtovenda2 = new ProdutoVenda(venda2, p2, 2);
		ProdutoVenda produtovenda3 = new ProdutoVenda(venda3, p1, 1);

		//Define os produtos da venda respectivamente
		venda1.getItens().addAll(Arrays.asList(produtovenda0,produtovenda1));
		venda2.getItens().addAll(Arrays.asList(produtovenda2));
		venda3.getItens().addAll(Arrays.asList(produtovenda3));
		
		//Informa a classe produto sobre a venda
		p1.getItens().addAll(Arrays.asList(produtovenda0,produtovenda3));
		p2.getItens().addAll(Arrays.asList(produtovenda2));
		p3.getItens().addAll(Arrays.asList(produtovenda1));

		produtoVendaRepository.saveAll(Arrays.asList(produtovenda0,produtovenda1, produtovenda2, produtovenda3));
	}
}