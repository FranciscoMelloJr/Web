package br.unisul.web.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unisul.web.domain.Disciplina;
import br.unisul.web.services.DisciplinaService;

@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaResource {

	@Autowired
	private DisciplinaService service;

	// BUSCAR POR ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Disciplina> find(@PathVariable Integer id) {
		Disciplina obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// INSERIR
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Disciplina obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// ATUALIZAR
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Disciplina obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	// EXCLUIR
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// LISTAR TODAS
//	@RequestMapping(method = RequestMethod.GET)
	// public ResponseEntity<List<DisciplinaDTO>> findAll() {
	// List<Disciplina> lista = service.findAll();
	// List<DisciplinaDTO> listaDTO = lista.stream().map(obj -> new
	// DisciplinaDTO(obj)).collect(Collectors.toList());
	// return ResponseEntity.ok().body(listaDTO);
//	}

}
