package br.com.sigevendees.enums;

/*Esta classe representa os valores dos tipos de categoria que Produto e Componente podem receber.*/

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
