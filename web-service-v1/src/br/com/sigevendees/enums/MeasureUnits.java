package br.com.sigevendees.enums;

/*Esta classe representa os valores dos simbolos de unidade de medida que Produto e Componente podem receber.*/

public enum MeasureUnits {
	GRAMA("G"),
	MILILITRO("ML"),
	UNIDADE("UND"),
	CENTO("Cento");

	private String descricao;

	MeasureUnits(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
