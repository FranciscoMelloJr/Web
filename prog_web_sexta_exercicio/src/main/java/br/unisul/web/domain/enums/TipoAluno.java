package br.unisul.web.domain.enums;

public enum TipoAluno {

	ADULTO(1, "Universitário"), ADOLECENTE(2, "Ensino Médio");

	private int cod;
	private String descricao;

	private TipoAluno(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoAluno toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TipoAluno x : TipoAluno.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}