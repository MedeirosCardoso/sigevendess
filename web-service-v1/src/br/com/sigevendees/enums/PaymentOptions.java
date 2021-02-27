package br.com.sigevendees.enums;

/*Esta classe representa os valores da forma de pagamento da Venda.*/

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
