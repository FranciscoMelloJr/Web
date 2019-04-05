package br.unisul.web.dtos;

import java.io.Serializable;

import br.unisul.web.domain.Disciplina;

public class DisciplinaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public DisciplinaDTO() {

	}

	public DisciplinaDTO(Disciplina obj) {
		id = obj.getId();
		nome = obj.getNome();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
