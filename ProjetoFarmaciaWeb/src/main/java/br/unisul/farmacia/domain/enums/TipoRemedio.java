package br.unisul.farmacia.domain.enums;

public enum TipoRemedio {

	TARJA_PRETA(1, "Produto com tarja preta"), PRODUTO_SEM_TARJA(2, "Produto sem tarja");

	private int cod;
	private String descricao;

	private TipoRemedio(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoRemedio toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TipoRemedio x : TipoRemedio.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
