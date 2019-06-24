package br.unisul.web.sexta.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.unisul.web.sexta.domain.Cidade;
import br.unisul.web.sexta.dtos.CidadeDTO;
import br.unisul.web.sexta.services.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findAll() {
		List<Cidade> lista = service.findAll();
		List<CidadeDTO> listDto = new ArrayList<CidadeDTO>();
		for (Cidade c : lista) {
			listDto.add(new CidadeDTO(c));
		}
		return ResponseEntity.ok().body(listDto);
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Cidade obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
