package br.com.horus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {
	@Column(length = 32, nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private Character tipoUsuario;
	
	@Column(nullable = false)
	private Boolean ativo;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipoUsuario() {
		return tipoUsuario;
	}
	
	@Transient
	public String getTipoFormatado() {
		String tipoFormatado = null;
		
		if (tipoUsuario == 'A') {
			tipoFormatado = "Administrador";
		} else if (tipoUsuario == 'G') {
			tipoFormatado = "Gerente";
		} else {
			tipoFormatado = "Funcionário";
		}
		
		return tipoFormatado;
	}
	
	public void setTipoUsuario(Character tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Boolean getAtivo() {
		return ativo;
	}
	
	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = null;
		
		if (ativo){
			ativoFormatado = "Sim";
		} else {
			ativoFormatado = "Não";
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
