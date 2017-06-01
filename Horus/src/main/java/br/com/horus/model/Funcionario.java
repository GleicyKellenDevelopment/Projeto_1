package br.com.horus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Funcionario extends GenericDomain {
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data_admisssao;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data_nascimento;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;
	
	public Date getData_admisssao() {
		return data_admisssao;
	}

	public void setData_admisssao(Date data_admisssao) {
		this.data_admisssao = data_admisssao;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
