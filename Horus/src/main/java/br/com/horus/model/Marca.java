package br.com.horus.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Marca extends GenericDomain {
	@Column(length = 40, nullable = false)
	private String nome_marca;

	public String getNome_marca() {
		return nome_marca;
	}

	public void setNome_marca(String nome_marca) {
		this.nome_marca = nome_marca;
	}
}
