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

		Cliente cli1 = new Cliente(null, "Francisco Mello", 0);
		Cliente cli2 = new Cliente(null, "Gustavo Casagrande", 0);
		Cliente cli3 = new Cliente(null, "Luana Silveira", 0);

		Produto p1 = new Produto(null, "Acebrofilina", 20, 38.90);
		Produto p2 = new Produto(null, "Anador", 100, 2.00);
		Produto p3 = new Produto(null, "Benegrip", 100, 20.00);

		clieRep.saveAll(Arrays.asList(cli1, cli2, cli3));
		prodRep.saveAll(Arrays.asList(p1, p2, p3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Venda venda1 = new Venda(null, sdf.parse("05/06/2019 08:47"), cli1);
		Venda venda2 = new Venda(null, sdf.parse("10/06/2019 10:35"), cli2);
		Venda venda3 = new Venda(null, sdf.parse("15/06/2019 12:35"), cli3);
		
		cli1.getVendas().addAll(Arrays.asList(venda1));
		cli2.getVendas().addAll(Arrays.asList(venda2));
		cli3.getVendas().addAll(Arrays.asList(venda3));
		vendaRepository.saveAll(Arrays.asList(venda1, venda2, venda3));

		ProdutoVenda produtovenda1 = new ProdutoVenda(venda1, p1, 1, 0.00);
		ProdutoVenda produtovenda2 = new ProdutoVenda(venda1, p3, 2, 0.00);
		ProdutoVenda produtovenda3 = new ProdutoVenda(venda2, p2, 3, 0.00);

		venda1.getItens().addAll(Arrays.asList(produtovenda1));
		venda2.getItens().addAll(Arrays.asList(produtovenda2));
		venda3.getItens().addAll(Arrays.asList(produtovenda3));
		
		p1.getItens().addAll(Arrays.asList(produtovenda1));
		p2.getItens().addAll(Arrays.asList(produtovenda2));
		p3.getItens().addAll(Arrays.asList(produtovenda3));

		produtoVendaRepository.saveAll(Arrays.asList(produtovenda1, produtovenda2, produtovenda3));
	}
}