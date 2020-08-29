package br.com.sigevendees.enums;

public enum CategoryTypes {
	INGREDIENTE("Ingrediente"),
	DOCE("Doce"),
	EMBALAGEM("Embalagem"),
	SALGADO("Salgado");

	private String descricao;

	CategoryTypes(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
