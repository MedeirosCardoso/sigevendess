package br.com.sigevendees.enums;

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
