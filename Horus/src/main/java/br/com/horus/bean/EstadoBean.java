package br.com.horus.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.horus.dao.EstadoDAO;
import br.com.horus.model.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	private Estado estado;
	private List<Estado> listarEstados;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getListarEstados() {
		return listarEstados;
	}

	public void setListarEstados(List<Estado> listarEstados) {
		this.listarEstados = listarEstados;
	}

	public void novo() {
		estado = new Estado();
	}

	public void salvar() {

		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
			// LIMPAR CAMPOS
			estado = new Estado();
			// RECARREGAR A LISTA
			listarEstados = estadoDAO.listar("nome");
			Messages.addGlobalInfo("Estado Cadastrado com Sucesso.");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Cadastrar o Estado.");
			error.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			listarEstados = estadoDAO.listar("nome");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar o Estado.");
			error.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
	}

	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			listarEstados = estadoDAO.listar("nome");
			Messages.addGlobalInfo("Estado removido com sucesso");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Remover o Estado.");
			error.printStackTrace();
		}
	}


}
