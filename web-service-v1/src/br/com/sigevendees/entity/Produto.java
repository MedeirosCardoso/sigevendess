package br.com.sigevendees.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer codigo;

	@Column(nullable = false)
	private String descricao;

	@Column(name = "tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	private CategoryTypes categoria;

	@Column(name = "tipoUnitario", nullable = false)
	@Enumerated(EnumType.STRING)
	private MeasureUnits simbolo;

	// preço do Produto é referente ao valor de venda.
	// preço do Componente é referente ao valor do custo médio da unidade;
	private float preco;

	@OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Receita receita;

	public Produto() {

	}

	// Construtor utilizado ao criar novo produto;
	public Produto(String descricao, CategoryTypes categoria, MeasureUnits simbolo, float preco, Receita receita) {
		this.descricao = descricao;
		this.categoria = categoria;
		this.simbolo = simbolo;
		this.preco = preco;
		receita.setProduto(this); // para sincronizar este produto com o produto da receita.
		this.receita = receita;
	}

	// Construtor utilizado no SELECT para bucar somente o Produto, sem a sua Receita.
	public Produto(Integer codigo, String descricao, CategoryTypes categoria, MeasureUnits simbolo, float preco) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.simbolo = simbolo;
		this.preco = preco;
	}

	// Construtor utilizado ao criar novo componente.
	public Produto(String descricao, CategoryTypes categoria, MeasureUnits simbolo) {
		this.descricao = descricao;
		this.categoria = categoria;
		this.simbolo = simbolo;
	}
	// Construtor utilizado na conversão de objeto java para JSON.
	public Produto(Integer codigo, String descricao, CategoryTypes categoria, MeasureUnits simbolo, float preco, Receita receita) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.simbolo = simbolo;
		this.preco = preco;
		this.receita = receita;
	}

	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String prod = "Produto [codigo=" + codigo + ", descricao=" + descricao + ", tipoDoProduto=" + categoria
				+ ", tipoUnitatio=" + simbolo + ", preco=" + preco;
		if (getReceita() != null) {
			prod += "\n" + receita + "]";
		} else {
			prod += "]";
		}
		return prod;
	}
}