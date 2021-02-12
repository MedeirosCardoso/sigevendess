package br.com.sigevendees.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*Esta classe representa a tabela associativa entre o relacionamento de Aquisicao e Componente,
 * no qual uma aquisicao pode ser composta por um ou vários componentes*/

@Entity(name = "Item_Aquisicao")
public class ItemAquisicao implements Serializable {
	private static final long serialVersionUID = -4267271788741946918L;

	@Id
	@ManyToOne
	@JoinColumn(name = "codAquisicao")
	private Aquisicao aquisicao;

	@Id
	@ManyToOne
	@JoinColumn(name = "codComponente")
	private Componente componente;

	@Column(nullable = false)
	private float qtdAdquirida;

	@Column(nullable = false)
	private float custo;

	public ItemAquisicao() {

	}
	
	public ItemAquisicao(Aquisicao aquisicao, Componente componente, float qtdAdquirida, float custo) {
		this.aquisicao = aquisicao;
		this.componente = componente;
		this.qtdAdquirida = qtdAdquirida;
		this.custo = custo;
	}
	
	// Construtor utilizado na conversão de objeto java para JSON.
	public ItemAquisicao(Componente componete, float qtdAdquirida, float custo) {
		this.componente = componete;
		this.qtdAdquirida = qtdAdquirida;
		this.custo = custo;
	}

	public Aquisicao getAquisicao() {
		return aquisicao;
	}

	public void setAquisicao(Aquisicao aquisicao) {
		this.aquisicao = aquisicao;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public float getQtdAdquirida() {
		return qtdAdquirida;
	}

	public void setQtdAdquirida(float qtdAdquirida) {
		this.qtdAdquirida = qtdAdquirida;
	}

	public float getCusto() {
		return custo;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aquisicao == null) ? 0 : aquisicao.hashCode());
		result = prime * result + ((componente == null) ? 0 : componente.hashCode());
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
		ItemAquisicao other = (ItemAquisicao) obj;
		if (aquisicao == null) {
			if (other.aquisicao != null)
				return false;
		} else if (!aquisicao.equals(other.aquisicao))
			return false;
		if (componente == null) {
			if (other.componente != null)
				return false;
		} else if (!componente.equals(other.componente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n" + componente.getCodigo() + "-" + componente.getDescricao() + ", qtdAdquirida=" + qtdAdquirida
				+ ", custo=" + custo;
	}

}
