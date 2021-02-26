package br.com.sigevendees.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import br.com.sigevendees.enums.PaymentOptions;
import br.com.sigevendees.enums.Status;

@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer codigo;

	@Column(name = "pagamento", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentOptions opcPagamento;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(nullable = false)
	private float vlrTotalDesc;

	@Column(nullable = false)
	private float vlrTotalVenda;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pedido> pedidos = new ArrayList<>();

	public Venda() {

	}

	public Venda(PaymentOptions opcPagamento, Date data) {
		this.opcPagamento = opcPagamento;
		this.data = data;
	}

	public Venda(Integer codigo, PaymentOptions opcPagamento, Date data, float vlrTotalVenda, float vlrTotalDesc,
			List<Pedido> pedidosDaVenda) {
		this.codigo = codigo;
		this.opcPagamento = opcPagamento;
		this.data = data;
		this.vlrTotalVenda = vlrTotalVenda;
		this.vlrTotalDesc = vlrTotalDesc;
		this.pedidos = pedidosDaVenda;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public PaymentOptions getOpcPagamento() {
		return opcPagamento;
	}

	public void setOpcPagamento(PaymentOptions opcPagamento) {
		this.opcPagamento = opcPagamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getVlrTotalDesc() {
		return vlrTotalDesc;
	}

	public void setVlrTotalDesc(float vlrTotalDesc) {
		this.vlrTotalDesc = vlrTotalDesc;
	}

	public float getVlrTotalVenda() {
		return vlrTotalVenda;
	}

	public void setVlrTotalVenda(float vlrTotalVenda) {
		this.vlrTotalVenda = vlrTotalVenda;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	// Método adiciona o Pedido, soma o valor total de desconto da Venda,
	// soma o valor total da Venda e altera o status do Pedido para VENDIDO.
	public void addPedido(Pedido pedido) {
		this.vlrTotalDesc += pedido.getVlrTotalDesconto();
		this.vlrTotalVenda += pedido.getVlrTotal();
		pedidos.add(pedido);
		pedido.setVenda(this);
		pedido.setSituacao(Status.VENDIDO);
	}

	public void removePedido(Pedido pedido) {
		pedidos.remove(pedido);
		pedido.setVenda(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Venda other = (Venda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		return "Venda [codigo=" + codigo + ", opcPagamento=" + opcPagamento + ", data=" + fmt.format(data)
				+ ", vlrTotalDesc=" + vlrTotalDesc + ", vlrTotalVenda=" + vlrTotalVenda + ", \npedidos" + pedidos + "]";
	}

}
