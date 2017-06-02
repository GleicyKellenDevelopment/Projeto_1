package br.com.horus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Cliente extends GenericDomain {
	@Column(nullable = false)
	// CRIAR NO BANCO DE DADOS DO TIPO DATA
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	// UMA PESSOA PODE TER APENAS UM CADASTRO COMO CLIENTE
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getAtivo() {
		return ativo;
	}
	
	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = null;
		
		if (ativo){
			ativoFormatado = "Ativo";
		} else {
			ativoFormatado = "Não ativo";
		}
		
		return ativoFormatado;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
