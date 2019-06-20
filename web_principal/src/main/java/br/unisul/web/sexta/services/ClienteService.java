package br.unisul.web.sexta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unisul.web.sexta.domain.Cidade;
import br.unisul.web.sexta.domain.Cliente;
import br.unisul.web.sexta.domain.Endereco;
import br.unisul.web.sexta.domain.enums.TipoCliente;
import br.unisul.web.sexta.dtos.ClienteDTO;
import br.unisul.web.sexta.dtos.ClienteNewDTO;
import br.unisul.web.sexta.repositories.ClienteRepository;
import br.unisul.web.sexta.repositories.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	//Mesmo service para cliente e endereço
	@Autowired
	private EnderecoRepository enderecoRepository;

	//Localizar pelo código
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElse(null);
	}

	//Recebe o objeto Cliente
	//Sempre com o dominio.
	public Cliente update(Cliente obj) {
		//Recupera o que está no banco de dados 
		Cliente newObj = find(obj.getId());
		//Troca apenas as atualizações sem anulação
		updateData(newObj, obj);
		//Salva e chama o objeto com os dados alterados
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	//Conversão de ClienteDTO para Cliente
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	//Conversão de ClienteNewDTO para Cliente
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente findByEmail(String email) {
		Cliente obj = repo.findByEmail(email);
		return obj;
	}

}
