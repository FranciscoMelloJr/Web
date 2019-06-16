package br.unisul.farmacia.dtos;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.unisul.farmacia.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 4, max = 99, message = "O tamanho deve ser entre 4 e 99 caracteres")
	private String nome;

	private Integer saldoDevedor;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		saldoDevedor = obj.getSaldoDevedor();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getSaldoDevedor() {
		return saldoDevedor;
	}

	public void setSaldoDevedor(Integer saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}
}