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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import br.com.sigevendees.enums.Status;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer codigo;

	@OneToOne
	private Cliente cliente;

	@Column(name = "situacao", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status situacao;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataSolicitado;

	@Temporal(TemporalType.DATE)
	private Date dataEntrega;

	@Temporal(TemporalType.DATE)
	private Date dataProduzido;
	// itens foi definido como fetch EAGER para que, ao realiar busca da venda a
	// mesma tras os seus pedidos com os itens de cada Pedido.
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPedido> itens = new ArrayList<>();
	
	private float vlrTotalDesconto;

	private float vlrTotal;

	@ManyToOne
	@JoinColumn(name = "codVenda")
	private Venda venda;

	public Pedido() {

	}

	public Pedido(Cliente cliente, Date dataEntrega) {
		this.cliente = cliente;
		this.situacao = Status.PRODUCAO;
		this.dataSolicitado = new Date();
		this.dataEntrega = dataEntrega;
	}

	public Pedido(Integer codigo, Cliente cliente, Status situacao, Date dataSolicitado, Date dataEntrega,
			List<ItemPedido> itens) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.situacao = situacao;
		this.dataSolicitado = dataSolicitado;
		this.dataEntrega = dataEntrega;
		this.itens = itens;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Status getSituacao() {
		return situacao;
	}

	public void setSituacao(Status situacao) {
		this.situacao = situacao;
	}

	public Date getDataSolicitado() {
		return dataSolicitado;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataProduzido() {
		return dataProduzido;
	}

	public void setDataProduzido(Date dataProduzido) {
		this.dataProduzido = dataProduzido;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public float getVlrTotalDesconto() {
		return vlrTotalDesconto;
	}

	public void setVlrTotalDesconto(float vlrTotalDesconto) {
		this.vlrTotalDesconto = vlrTotalDesconto;
	}

	public float getVlrTotal() {
		return vlrTotal;
	}

	public void setVlrTotal(float vlrTotal) {
		this.vlrTotal = vlrTotal;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	// Método adiciona o item, soma o valor total de desconto e soma o valor total
	// do Pedido.
	public void addItem(Produto produto, int qtd, float vlrDesconto) {
		ItemPedido item = new ItemPedido(this, produto, qtd, vlrDesconto);
		this.vlrTotalDesconto += item.getVlrDescUnit() * item.getQtdSolicitado();
		this.vlrTotal += item.calcularVlrTotalItem();
		this.itens.add(item);
	}

	public void removeItem(Produto produto) {
		ItemPedido item = new ItemPedido();
		item.setProduto(produto);
		item.setPedido(this);
		this.itens.remove(item);
		item.setProduto(null);
		item.setPedido(null);
	}

	public void calcularVlrTotalPedido() {
		this.vlrTotalDesconto = 0;
		this.vlrTotal = 0;
		for (ItemPedido item : this.itens) {
			this.vlrTotalDesconto += item.getVlrDescUnit() * item.getQtdSolicitado();
			this.vlrTotal += item.calcularVlrTotalItem();
		}
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
		Pedido other = (Pedido) obj;
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
		String pedido = "\nPedido [codigo=" + codigo + ", cliente=" + cliente.getNumero() + "-" + cliente.getNome()
				+ ", situacao=" + situacao + ", dataSolicitado=" + fmt.format(dataSolicitado) + ", dataEntrega="
				+ fmt.format(dataEntrega) + ", totalDesconto=" + vlrTotalDesconto + ", total=" + vlrTotal;
		if (dataProduzido != null) {
			pedido += ", dataProduzido=" + fmt.format(dataProduzido) + ", \nitens=" + itens + "]";
		} else {
			pedido += ", dataProduzido=NULL" + ", \nitens=" + itens + "]";
		}
		return pedido;
	}
}
