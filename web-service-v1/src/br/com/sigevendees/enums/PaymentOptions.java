package br.com.sigevendees.enums;

public enum PaymentOptions {
	DINHEIRO("Dinheiro"),
	CREDITO("Credito"),
	DEBITO("Debito"),
	PRAZO("Prazo");

	private String descricao;

	PaymentOptions(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
