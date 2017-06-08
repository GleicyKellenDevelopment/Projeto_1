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
	private Date data_admissao;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data_nascimento;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;
	
	public Date getData_admissao() {
		return data_admissao;
	}

	public void setData_admissao(Date data_admissao) {
		this.data_admissao = data_admissao;
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
