package br.unisul.farmacia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unisul.farmacia.domain.Cliente;
import br.unisul.farmacia.domain.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

	@Transactional(readOnly = true)
	List<Venda> findByCliente(Cliente cliente);

}
