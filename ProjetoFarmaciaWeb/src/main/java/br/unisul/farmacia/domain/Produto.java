package br.unisul.farmacia.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import br.unisul.farmacia.domain.enums.TipoRemedio;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private Integer estoque;
	private Double valor;
	private Integer tarja;

	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ProdutoVenda> itens = new HashSet<>();

	public Produto() {

	}

	public Produto(Integer id, String nome, Integer estoque, Double valor, TipoRemedio tarja) {
		super();
		this.id = id;
		this.nome = nome;
		this.estoque = estoque;
		this.valor = valor;
		this.tarja = (tarja == null) ? null : tarja.getCod();
	}

	public TipoRemedio getTarja() {
		return TipoRemedio.toEnum(tarja);
	}

	public void setTarja(TipoRemedio tarja) {
		this.tarja = tarja.getCod();
	}

	@JsonIgnore
	public List<Venda> getPedidos() {
		List<Venda> lista = new ArrayList<>();
		for (ProdutoVenda x : itens) {
			lista.add(x.getVenda());
		}
		return lista;
	}

	public Set<ProdutoVenda> getItens() {
		return itens;
	}

	public void setItens(Set<ProdutoVenda> itens) {
		this.itens = itens;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
