package br.com.sigevendees.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;

@Entity
public class Produto extends Mercadoria {

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "codReceita")
	private Receita receita;

	public Produto() {

	}

	// Construtor utilizado ao criar novo Produto.
	public Produto(String descricao, CategoryTypes categoria, MeasureUnits simbolo, float preco, Receita receita) {
		super(descricao, categoria, simbolo, preco);
		receita.setProduto(this); // para sincronizar este produto com o produto da receita.
		this.receita = receita;
	}

	// Construtor utilizado no SELECT JPQL para bucar somente o Produto, sem a sua Receita.
	public Produto(Integer codigo, String descricao, CategoryTypes categoria, MeasureUnits simbolo, float preco) {
		super(codigo, descricao, categoria, simbolo, preco);
	}

	// Construtor utilizado na conversão de objeto java para JSON.
	public Produto(Integer codigo, String descricao, CategoryTypes categoria, MeasureUnits simbolo, float preco, Receita receita) {
		super(codigo, descricao, categoria, simbolo, preco);
		this.receita = receita;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	@Override
	public String toString() {
		String prod = "Produto[" + super.toString();
		if (receita != null) {
			prod += "\n" + receita + "]";
		} else {
			prod += "]";
		}
		return prod;
	}
}