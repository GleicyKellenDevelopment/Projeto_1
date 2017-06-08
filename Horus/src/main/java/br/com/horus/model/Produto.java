package br.com.horus.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {
	
	@Column(nullable = false)
	private Integer nota_fiscal;
		
	@Column(length = 50, nullable = false)
	private String nome_produto;

	@Column(nullable = false)
	private Short qtd_estoque;

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal valor_compra;
	// PRECISION ==> QUANTOS NUMEROS ANTES DA VIRGULA
	// SCALE ==> QUANTOS NUMEROS DEPOIS DA VIRGULA
	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal valor_unitario ; // PREÇO
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Marca marca;

	public Integer getNota_fiscal() {
		return nota_fiscal;
	}

	public void setNota_fiscal(Integer nota_fiscal) {
		this.nota_fiscal = nota_fiscal;
	}

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

	public BigDecimal getValor_compra() {
		return valor_compra;
	}

	public void setValor_compra(BigDecimal valor_compra) {
		this.valor_compra = valor_compra;
	}

	public BigDecimal getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(BigDecimal valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	
}
