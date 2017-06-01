package br.com.horus.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.horus.dao.CidadeDAO;
import br.com.horus.dao.EstadoDAO;
import br.com.horus.model.Cidade;
import br.com.horus.model.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable{
	
	private Cidade cidade;
	private List<Cidade> listarCidades;
	private List<Estado> listarEstados;
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public List<Cidade> getListarCidades() {
		return listarCidades;
	}
	
	public void setListarCidades(List<Cidade> listarCidades) {
		this.listarCidades = listarCidades;
	}
	
	public List<Estado> getListarEstados() {
		return listarEstados;
	}
	
	public void setListarEstados(List<Estado> listarEstados) {
		this.listarEstados = listarEstados;
	}
	
	
	@PostConstruct
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			listarCidades = cidadeDAO.listar();
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar as Cidades.");
			error.printStackTrace(); 
		}
	}
	
	public void novo() {
		try {
			cidade = new Cidade();
			
			EstadoDAO estadoDAO = new EstadoDAO();
			listarEstados = estadoDAO.listar();
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Gerar uma Nova Cidade.");
			error.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);
			
			cidade = new Cidade();
			EstadoDAO estadoDAO = new EstadoDAO();
			listarEstados = estadoDAO.listar();
			listarCidades = cidadeDAO.listar();
			
			Messages.addGlobalInfo("Cidade Salva com Sucesso.");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Salvar a Cidade.");
			error.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionado");
			EstadoDAO estadoDAO = new EstadoDAO();
			listarEstados = estadoDAO.listar();

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro Selecionar uma Cidade.");
			error.printStackTrace();
		}
	}
	
	
	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionado");
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.excluir(cidade);
			listarCidades = cidadeDAO.listar();
			Messages.addGlobalInfo("Cidade removida com sucesso");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro Remover a Cidade.");
			error.printStackTrace();
		}
	}
}
