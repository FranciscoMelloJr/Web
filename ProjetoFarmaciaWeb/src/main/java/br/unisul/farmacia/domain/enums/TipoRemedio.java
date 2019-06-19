package br.unisul.farmacia.domain.enums;

public enum TipoRemedio {

	PRODUTO_SEM_TARJA(1, "Produto sem tarja"), TARJA_PRETA(2, "Produto com tarja preta");

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
