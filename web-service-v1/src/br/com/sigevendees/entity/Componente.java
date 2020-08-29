package br.com.sigevendees.entity;

import javax.persistence.Entity;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;

@Entity
public class Componente extends Produto {

	private float estoqueMin;

	private float estoqueAtual;

	public Componente() {

	}

	public Componente(String descricao, CategoryTypes categoria, MeasureUnits simbolo, float estoqueMin) {
		super(descricao, categoria, simbolo);
		this.estoqueMin = estoqueMin;
	}

	// Construtor utilizado no SELECT para bucar somente dados do Componente.
	public Componente(Integer codigo, String descricao, CategoryTypes categoria, MeasureUnits simbolo, float preco, float estoqueMin, float estoqueAtual) {
		super(codigo, descricao, categoria, simbolo, preco);
		this.estoqueMin = estoqueMin;
		this.estoqueAtual = estoqueAtual;
	}

	public float getEstoqueMin() {
		return estoqueMin;
	}

	public void setEstoqueMin(float estoqueMin) {
		this.estoqueMin = estoqueMin;
	}

	public float getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(float estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	@Override
	public String toString() {
		return "Componente [codigo=" + getCodigo() + ", descricao=" + getDescricao() + ", tipoProduto=" + getCategoria()
				+ ", tipoUnitario=" + getSimbolo() + ", custo=" + getPreco() + ", estoqueMin=" + estoqueMin
				+ ", estoqueAtual=" + estoqueAtual + "]";
	}

}
