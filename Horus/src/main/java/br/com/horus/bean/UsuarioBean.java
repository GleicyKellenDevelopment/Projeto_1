package br.com.horus.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.horus.dao.PessoaDAO;
import br.com.horus.dao.UsuarioDAO;
import br.com.horus.model.Pessoa;
import br.com.horus.model.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
	
	private Usuario usuario;
	private List<Usuario> listarUsuarios;
	private List<Pessoa> listarPessoas;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getListarUsuarios() {
		return listarUsuarios;
	}
	public void setListarUsuarios(List<Usuario> listarUsuarios) {
		this.listarUsuarios = listarUsuarios;
	}
	public List<Pessoa> getListarPessoas() {
		return listarPessoas;
	}
	public void setListarPessoas(List<Pessoa> listarPessoas) {
		this.listarPessoas = listarPessoas;
	}
	
	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			listarUsuarios = usuarioDAO.listar("tipoUsuario");
			
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar os usuários.");
			error.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			usuario = new Usuario();
			
			PessoaDAO pessoaDAO =  new PessoaDAO();
			listarPessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Gerar um usuário.");
			error.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);
			
			novo();
			PessoaDAO pessoaDAO = new PessoaDAO();
			listarPessoas = pessoaDAO.listar("nome");
			
			listarUsuarios = usuarioDAO.listar("tipoUsuario");
			
			Messages.addGlobalInfo("Usuário Salvo com Sucesso.");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Salvar o Usuário.");
			error.printStackTrace();
		}
	}
}
