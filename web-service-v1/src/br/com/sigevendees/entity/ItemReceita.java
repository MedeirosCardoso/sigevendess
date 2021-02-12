package br.com.sigevendees.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*Esta classe representa a tabela associativa entre o relacionamento de Componete e Receita,
 * no qual uma receita é composta por um ou vários componentes*/

@Entity(name = "Item_Receita")
public class ItemReceita implements Serializable{
	private static final long serialVersionUID = 8932694805750610041L;

	@Id
	@ManyToOne
	@JoinColumn(name = "codReceita")
	private Receita receita;

	@Id
	@ManyToOne
	@JoinColumn(name = "codComponente")
	private Componente componente;

	@Column(nullable = false)
	private float qtdUtilizada;

	public ItemReceita() {

	}

	public ItemReceita(Receita receita, Componente componente, float qtdUtilizada) {
		this.receita = receita;
		this.componente = componente;
		this.qtdUtilizada = qtdUtilizada;
	}
	
	// Construtor utilizado na conversão de objeto java para JSON.
	public ItemReceita(Componente componete, float qtdUtilizada) {
		this.componente = componete;
		this.qtdUtilizada = qtdUtilizada;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componete) {
		this.componente = componete;
	}

	public float getQtdUtilizada() {
		return qtdUtilizada;
	}

	public void setQtdUtilizada(float qtdUtilizada) {
		this.qtdUtilizada = qtdUtilizada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((componente == null) ? 0 : componente.hashCode());
		result = prime * result + ((receita == null) ? 0 : receita.hashCode());
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
		ItemReceita other = (ItemReceita) obj;
		if (componente == null) {
			if (other.componente != null)
				return false;
		} else if (!componente.equals(other.componente))
			return false;
		if (receita == null) {
			if (other.receita != null)
				return false;
		} else if (!receita.equals(other.receita))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n" + componente.getCodigo() + "-" + componente.getDescricao() + ", qtdUtilizada=" + qtdUtilizada;
	}

}
