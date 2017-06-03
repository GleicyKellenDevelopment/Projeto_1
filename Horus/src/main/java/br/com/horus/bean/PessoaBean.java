package br.com.horus.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.horus.dao.CidadeDAO;
import br.com.horus.dao.EstadoDAO;
import br.com.horus.dao.PessoaDAO;
import br.com.horus.model.Cidade;
import br.com.horus.model.Estado;
import br.com.horus.model.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable{
	
	private Pessoa pessoa;
	private Estado estadoSelecionado;
	private List<Pessoa> listarPessoas;
	private List<Estado> listarEstados;
	private List<Cidade> listarCidades;
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getListarPessoas() {
		return listarPessoas;
	}
	
	public void setListarPessoas(List<Pessoa> listarPessoas) {
		this.listarPessoas = listarPessoas;
	}
	
	public List<Estado> getListarEstados() {
		return listarEstados;
	}
	
	public void setListarEstados(List<Estado> listarEstados) {
		this.listarEstados = listarEstados;
	}
	
	public List<Cidade> getListarCidades() {
		return listarCidades;
	}
	
	public void setListarCidades(List<Cidade> listarCidades) {
		this.listarCidades = listarCidades;
	}
	
	
	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			listarPessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar as Pessoas.");
			error.printStackTrace(); 
		}
	}
	
	public void novo() {
		try {
			pessoa = new Pessoa();
			estadoSelecionado = new Estado();
			
			EstadoDAO estadoDAO = new EstadoDAO();
			listarEstados = estadoDAO.listar("nome");
			
			listarCidades = new ArrayList<Cidade>();
			
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Gerar uma Pessoa.");
			error.printStackTrace();
		}
	}
	
	public void selecionarCidadePorEstado() {
		try {
			if (estadoSelecionado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				listarCidades = cidadeDAO.buscarPorEstado(estadoSelecionado.getId());
				
			}
		} catch (Exception error) {
			Messages.addGlobalError("Erro ao tentar filtrar as Cidades.");
			error.printStackTrace(); 
		}
		
	}
	
	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);
			
			listarPessoas = pessoaDAO.listar("nome");
			//	Chamando Método Novo para limpar		
			novo();
			Messages.addGlobalInfo("Pessoa Salva com Sucesso.");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Salvar a Pessoa.");
			error.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionado");
			
			estadoSelecionado = pessoa.getCidade().getEstado();
			EstadoDAO estadoDAO = new EstadoDAO();
			listarEstados = estadoDAO.listar("nome");
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			listarCidades = cidadeDAO.listar("nome");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro Selecionar uma Pessoa.");
			error.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionado");
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			listarPessoas = pessoaDAO.listar("nome");
			Messages.addGlobalInfo("Pessoa removida com sucesso");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro Remover a Pessoa.");
			error.printStackTrace();
		}
	}
}
