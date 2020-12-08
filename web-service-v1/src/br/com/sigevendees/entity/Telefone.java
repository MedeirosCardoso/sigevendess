package br.com.sigevendees.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telefone implements Serializable{
	private static final long serialVersionUID = 1L;

	/*
	 * NÃO UTILIZAR O NÚMERO ZERO NA FRENTE DO DDD,
	 * Variáveis primitivas aceita receber números inteiros, octais, hexadecimais e até binário.
	 * Se for utilizado o zero na frente do número, indicamos que estamos lidando com número na base 8 (octais).
	 */
	@Column(nullable = false)
	private byte ddd;

	@Column(name = "telefone", nullable = false)
	private int digito;

	public Telefone() {

	}

	public Telefone(byte ddd, int digito) {
		this.ddd = ddd;
		this.digito = digito;
	}

	public byte getDdd() {
		return ddd;
	}

	public void setDdd(byte ddd) {
		this.ddd = ddd;
	}

	public int getDigito() {
		return digito;
	}

	public void setDigito(int digito) {
		this.digito = digito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ddd;
		result = prime * result + digito;
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
		Telefone other = (Telefone) obj;
		if (ddd != other.ddd)
			return false;
		if (digito != other.digito)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + ddd + ")" + digito;
	}
}
