package br.com.sigevendees.entity;

import javax.persistence.Entity;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;

@Entity
public class Componente extends Mercadoria {
	
	private float estoqueMin;

	public Componente() {

	}

	// Construtor utilizado ao criar novo Componente.
	public Componente(String descricao, CategoryTypes categoria, MeasureUnits simbolo, float estoqueMin) {
		super(descricao, categoria, simbolo, (float) 0.0);
		this.estoqueMin = estoqueMin;
	}

	public float getEstoqueMin() {
		return estoqueMin;
	}

	public void setEstoqueMin(float estoqueMin) {
		this.estoqueMin = estoqueMin;
	}

	@Override
	public String toString() {
		return "Componente[" + super.toString() + ", estoqueMin=" + estoqueMin + ", estoqueAtual=" + getEstoqueAtual() + "]";
	}
}
