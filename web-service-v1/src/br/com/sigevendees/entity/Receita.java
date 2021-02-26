package br.com.sigevendees.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer codigo;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codProduto")
	private Produto produto;

	@Column(nullable = false)
	private float rendimento;

	@Column(nullable = false)
	private float tempoPreparo;

	@OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemReceita> componentes = new ArrayList<>();

	public Receita() {

	}

	public Receita(float rendimento, float tempoPreparo) {
		this.rendimento = rendimento;
		this.tempoPreparo = tempoPreparo;
	}

	// Construtor utilizado na conversão de objeto java para JSON.
	public Receita(Integer codigo, float rendimento, float tempoPreparo, List<ItemReceita> componentes) {
		this.codigo = codigo;
		this.rendimento = rendimento;
		this.tempoPreparo = tempoPreparo;
		this.componentes = componentes;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public float getRendimento() {
		return rendimento;
	}

	public void setRendimento(float rendimento) {
		this.rendimento = rendimento;
	}

	public float getTempoPreparo() {
		return tempoPreparo;
	}

	public void setTempoPreparo(float tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}

	public List<ItemReceita> getComponentes() {
		return componentes;
	}

	/*
	 * Métodos abaixo utilizados para sincronizar as duas extremidades, sempre que
	 * um elemento filho(Componente) é adicionado ou removido.
	 */
	public void addComponente(Componente componente, float qtdUtilizada) {
		ItemReceita item = new ItemReceita(this, componente, qtdUtilizada);
		this.componentes.add(item);
	}

	public void removeComponente(Componente componente) {
		ItemReceita item = new ItemReceita();
		item.setComponente(componente);
		item.setReceita(this);
		this.componentes.remove(item);
		item.setComponente(null);
		item.setReceita(null);
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
		Receita other = (Receita) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Receita [codigo=" + codigo + ", rendimento=" + rendimento + ", tempoPreparo=" + tempoPreparo
				+ "\ncomponentes=" + componentes + "]";
	}

}
