package br.com.sigevendees.enums;

/*Esta classe representa os valores para o Status do Pedido.*/

public enum Status {
	PRODUCAO("Producao"),
	PRONTO("Pronto"),
	VENDIDO("Vendido"),
	CANCELADO("Cancelado");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
