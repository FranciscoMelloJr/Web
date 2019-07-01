package br.unisul.farmacia.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProdutoVenda implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@JsonIgnore
	@EmbeddedId
	private ProdutoVendaPK id = new ProdutoVendaPK();

	private Integer quantidade;
	private Double valor;

	public ProdutoVenda() {
	
	}

	public ProdutoVenda(Venda venda, Produto produto, Integer quantidade) {
		super();
		id.setVenda(venda);
		id.setProduto(produto);
		this.quantidade = quantidade;
		this.valor = produto.getValor();
	}
	
	public double getSubTotal() {
		return (valor) * quantidade;
	}

	@JsonIgnore
	public Venda getVenda() {
		return id.getVenda();
	}

	public void setVenda(Venda venda) {
		id.setVenda(venda);
	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public ProdutoVendaPK getId() {
		return id;
	}

	public void setId(ProdutoVendaPK id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoVenda other = (ProdutoVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Qte: ");
		builder.append(getQuantidade());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getValor()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

}