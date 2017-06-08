package br.com.horus.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.horus.dao.FuncionarioDAO;
import br.com.horus.dao.PessoaDAO;
import br.com.horus.model.Funcionario;
import br.com.horus.model.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean  implements Serializable{
	
	private Funcionario funcionario;
	private List<Funcionario> listarFuncionarios;
	private List<Pessoa> listarPessoas;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getListarFuncionarios() {
		return listarFuncionarios;
	}
	public void setListarFuncionarios(List<Funcionario> listarFuncionarios) {
		this.listarFuncionarios = listarFuncionarios;
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
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			listarFuncionarios = funcionarioDAO.listar("data_admissao");
			
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar os funcionários.");
			error.printStackTrace(); 
		}
	}
	
	public void novo() {
		try {
			funcionario = new Funcionario();
			
			PessoaDAO pessoaDAO =  new PessoaDAO();
			listarPessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Gerar um funcionário.");
			error.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);
			
			novo();
			
			listarFuncionarios = funcionarioDAO.listar("data_admissao");
			
			Messages.addGlobalInfo("Funcionário Salvo com Sucesso.");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Salvar o funcionário.");
			error.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			PessoaDAO pessoaDAO =  new PessoaDAO();
			listarPessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro Selecionar um funcionario.");
			error.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);
			listarFuncionarios = funcionarioDAO.listar("data_admissao");
			Messages.addGlobalInfo("Funcionário Removido com Sucesso.");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Remover o funcionário.");
			error.printStackTrace();
		}
	}
}
