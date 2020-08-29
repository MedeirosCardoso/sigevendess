package br.com.sigevendees.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*Esta classe representa a tabela associativa entre o relacionamento de componete e receita,
 * no qual uma receita pode ser composta por vários componentes*/

@Entity(name = "Item_Receita")
public class ItemReceita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "codReceita")
	private Receita receita;

	@Id
	@ManyToOne
	@JoinColumn(name = "codComponente")
	private Componente componete;

	@Column(nullable = false)
	private float qtdUtilizada;

	public ItemReceita() {

	}

	public ItemReceita(Receita receita, Componente componente, float qtdUtilizada) {
		this.receita = receita;
		this.componete = componente;
		this.qtdUtilizada = qtdUtilizada;
	}
	// Construtor utilizado na conversão de objeto java para JSON.
	public ItemReceita(Componente componete, float qtdUtilizada) {
		this.componete = componete;
		this.qtdUtilizada = qtdUtilizada;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Componente getComponete() {
		return componete;
	}

	public void setComponete(Componente componete) {
		this.componete = componete;
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
		result = prime * result + ((componete == null) ? 0 : componete.hashCode());
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
		if (componete == null) {
			if (other.componete != null)
				return false;
		} else if (!componete.equals(other.componete))
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
		return "\n" + componete.getCodigo() + "-" + componete.getDescricao() + ", qtdUtilizada=" + qtdUtilizada;
	}

}
