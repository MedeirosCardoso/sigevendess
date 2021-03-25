package br.com.sigevendees.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Aquisicao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer codigo;

	@OneToMany(mappedBy = "aquisicao", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemAquisicao> componentes = new ArrayList<>();

	@Temporal(TemporalType.DATE)
	private Date dataAquisicao;

	public Aquisicao() {
		this.dataAquisicao = new Date();
	}

	// Construtor utilizado na conversão de objeto java para JSON.
	public Aquisicao(Integer codigo, Date dataAquisicao, List<ItemAquisicao> componentes) {
		this.codigo = codigo;
		this.dataAquisicao = dataAquisicao;
		this.componentes = componentes;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public List<ItemAquisicao> getComponentes() {
		return componentes;
	}

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	/*
	 * Métodos abaixo utilizados para sincronizar as duas extremidades, sempre que
	 * um elemento filho(Componente) é adicionado ou removido.
	 */
	public void addComponente(Componente componente, float qtdAdquirida, float custo) {
		ItemAquisicao item = new ItemAquisicao(this, componente, qtdAdquirida, custo);
		componentes.add(item);
	}

	public void removeComponente(Componente componente) {
		ItemAquisicao item = new ItemAquisicao();
		item.setComponente(componente);
		item.setAquisicao(this);
		componentes.remove(item);
		item.setComponente(null);
		item.setAquisicao(null);
	}

	public void atualizarEstoqueComponente() {
		float qtdEstoqueAtual, qtdEntrada, precoMedioAtual, precoCompra;
		for (ItemAquisicao item : this.componentes) {
			qtdEstoqueAtual = item.getComponente().getEstoqueAtual();
			qtdEntrada = item.getQtdAdquirida();
			precoMedioAtual = item.getComponente().getPreco();
			precoCompra = item.getCusto();
			float novoPrecoMedio = 0;
			if (qtdEstoqueAtual != 0) {
				novoPrecoMedio = (qtdEstoqueAtual * precoMedioAtual + qtdEntrada * (precoCompra / qtdEntrada))
						/ (qtdEstoqueAtual + qtdEntrada);
			} else {
				novoPrecoMedio = precoCompra / qtdEntrada;
			}
			item.getComponente().setPreco(novoPrecoMedio);
			item.getComponente().setEstoqueAtual(qtdEstoqueAtual + qtdEntrada);
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
		Aquisicao other = (Aquisicao) obj;
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
		return "Aquisicao [codigo=" + codigo + ", dataAquisicao=" + fmt.format(dataAquisicao) + "\ncomponentes="
				+ componentes + "]";
	}
}
