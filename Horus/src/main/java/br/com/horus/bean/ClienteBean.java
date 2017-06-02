package br.com.horus.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.horus.dao.ClienteDAO;
import br.com.horus.dao.PessoaDAO;
import br.com.horus.model.Cliente;
import br.com.horus.model.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable{
	
	private Cliente cliente;
	private List<Cliente> listarClientes;
	private List<Pessoa> listarPessoas;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListarClientes() {
		return listarClientes;
	}

	public void setListarClientes(List<Cliente> listarClientes) {
		this.listarClientes = listarClientes;
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
			ClienteDAO clienteDAO = new ClienteDAO();
			listarClientes = clienteDAO.listar("dataCadastro");
			
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar os Clientes.");
			error.printStackTrace(); 
		}
	}
	
	public void novo() {
		try {
			cliente = new Cliente();
			
			PessoaDAO pessoaDAO =  new PessoaDAO();
			listarPessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Gerar uma Novo Cliente.");
			error.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);
			
			cliente = new Cliente();
			PessoaDAO pessoaDAO = new PessoaDAO();
			listarPessoas = pessoaDAO.listar("nome");
			
			listarClientes =  clienteDAO.listar("dataCadastro");
			
			Messages.addGlobalInfo("Cliente Salvo com Sucesso.");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Salvar o Cliente.");
			error.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			PessoaDAO pessoaDAO =  new PessoaDAO();
			listarPessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro Selecionar um Cliente.");
			error.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);
			listarClientes = clienteDAO.listar("dataCadastro");
			Messages.addGlobalInfo("Cliente removido com sucesso");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro Remover o Cliente.");
			error.printStackTrace();
		}
	}
}
