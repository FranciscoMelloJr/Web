package br.unisul.farmacia.dtos;

import java.io.Serializable;

public class ClienteInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private Double saldoDevedor;
	private Integer tipo;
	
	public ClienteInsertDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSaldoDevedor() {
		return saldoDevedor;
	}

	public void setSaldoDevedor(Double saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
