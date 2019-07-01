package br.unisul.farmacia.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unisul.farmacia.domain.Venda;
import br.unisul.farmacia.dtos.VendaInsertDTO;
import br.unisul.farmacia.services.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaResource {

	@Autowired
	private VendaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Venda>> findAll() {
		List<Venda> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Venda obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{clienteId}/cliente", method = RequestMethod.GET)
	ResponseEntity<List<Venda>> findByCliente(@PathVariable Integer clienteId) {
		List<Venda> list = service.findByCliente(clienteId);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody VendaInsertDTO objDto) {
		Venda obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}