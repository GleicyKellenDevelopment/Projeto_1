package br.com.horus.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String nome_produto;

	@Column(nullable = false)
	private Short qtd_estoque;

	// PRECISION ==> QUANTOS NUMEROS ANTES DA VIRGULA
	// SCALE ==> QUANTOS NUMEROS DEPOIS DA VIRGULA
	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Marca marca;

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public Short getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(Short qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}
