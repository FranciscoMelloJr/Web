package br.unisul.farmacia.dtos;

import java.io.Serializable;

public class ProdutoInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer estoque;
	private Double valor;
	private Integer tarja;

	public ProdutoInsertDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getTarja() {
		return tarja;
	}

	public void setTarja(Integer tarja) {
		this.tarja = tarja;
	}
}
