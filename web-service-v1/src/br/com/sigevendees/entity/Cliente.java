package br.com.sigevendees.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cliente {

	@Id
	private Telefone numero;

	@Column(nullable = false, length = 60)
	private String nome;
	
	@Column(length = 60)
	private String email;
	
	@Column(nullable = false, length = 60)
	private String nomEstabelecimento;

	@Column(nullable = false, length = 255)
	private String observacao;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "endereco")
	private Endereco endereco;

	public Cliente() {

	}

	public Cliente(Telefone numero, String nome, String email, String nomEstabelecimento, String observacao, Endereco endereco) {
		this.numero = numero;
		this.nome = nome;
		this.email = email;
		this.nomEstabelecimento = nomEstabelecimento.trim();
		this.observacao = observacao.trim();
		this.endereco = endereco;
	}

	public Telefone getNumero() {
		return numero;
	}

	public void setNumero(Telefone numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomestabelecimento() {
		return nomEstabelecimento;
	}

	public void setNomestabelecimento(String nomEstabelecimento) {
		this.nomEstabelecimento = nomEstabelecimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Cliente other = (Cliente) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [telefone=" + numero + ", nome=" + nome + ", email=" + email + ", nomeEstabelecimento="
				+ nomEstabelecimento + ", observacao=" + observacao + "\n" + endereco + "]";
	}
}
