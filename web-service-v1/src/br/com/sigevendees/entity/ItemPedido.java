package br.com.sigevendees.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*Esta classe representa a tabela associativa entre o relacionamento de Pedido e Produto,
 * no qual um pedido é composta por um ou vários produtos*/

@Entity(name = "Item_Pedido")
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = -9036365606019508533L;

	@Id
	@ManyToOne
	@JoinColumn(name = "codPedido")
	private Pedido pedido;

	@Id
	@ManyToOne
	@JoinColumn(name = "codProduto")
	private Produto produto;

	@Column(nullable = false)
	private int qtdSolicitado;

	private float vlrUndItem; // valor unitario do item.

	private float vlrDescUndItem; // valor do desconto unitario do item.

	public ItemPedido() {

	}

	public ItemPedido(Pedido pedido, Produto produto, int qtdSolicitado, float vlrDescItem) {
		this.pedido = pedido;
		this.produto = produto;
		this.qtdSolicitado = qtdSolicitado;
		this.vlrUndItem = produto.getPreco();
		this.vlrDescUndItem = vlrDescItem;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQtdSolicitado() {
		return qtdSolicitado;
	}
	
	public void setQtdSolicitado(int qtdSolicitado) {
		this.qtdSolicitado = qtdSolicitado;
	}

	public float getVlrUndItem() {
		return vlrUndItem;
	}

	public float getVlrDescItem() {
		return vlrDescUndItem;
	}

	public float calcularVlrTotalItem() {
		return (this.vlrUndItem - this.vlrDescUndItem) * this.qtdSolicitado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n" + produto.getCodigo() + "-" + produto.getDescricao() + ", qtd=" + qtdSolicitado + ", vlrUndItem="
				+ vlrUndItem + ", desconto=" + vlrDescUndItem + ", total=" + calcularVlrTotalItem();
	}
}
