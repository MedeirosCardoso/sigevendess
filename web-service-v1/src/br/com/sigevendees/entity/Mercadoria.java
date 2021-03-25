package br.com.sigevendees.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Mercadoria {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer codigo;

	@Column(nullable = false, length = 60)
	private String descricao;

	@Column(name = "tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	private CategoryTypes categoria;

	@Column(name = "tipoUnitario", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private MeasureUnits simbolo;

	// preço do Produto é o valor de venda.
	// preço do Componente é o valor de custo médio unitario.
	private float preco;
	
	private float estoqueAtual;

	public Mercadoria() {

	}

	// Construtor utilizado ao criar nova Mercadoria.
	public Mercadoria(String descricao, CategoryTypes categoria, MeasureUnits simbolo, float preco) {
		this.descricao = descricao;
		this.categoria = categoria;
		this.simbolo = simbolo;
		this.preco = preco;
	}

	// Construtor utilizado no SELECT JPQL.
	public Mercadoria(Integer codigo, String descricao, CategoryTypes categoria, MeasureUnits simbolo, float preco) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.simbolo = simbolo;
		this.preco = preco;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.trim();
	}

	public CategoryTypes getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoryTypes categoria) {
		this.categoria = categoria;
	}

	public MeasureUnits getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(MeasureUnits simbolo) {
		this.simbolo = simbolo;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public float getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(float estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
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
		Mercadoria other = (Mercadoria) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "codigo=" + codigo + ", descricao=" + descricao + ", categoria=" + categoria + ", simbolo=" + simbolo
				+ ", preco=" + preco;
	}

}
