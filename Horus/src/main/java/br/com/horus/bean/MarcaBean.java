package br.com.horus.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.horus.dao.MarcaDAO;
import br.com.horus.model.Marca;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MarcaBean implements Serializable{
	
	private Marca marca;
	private List<Marca> listarMarcas;
	
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public List<Marca> getListarMarcas() {
		return listarMarcas;
	}
	public void setListarMarcas(List<Marca> listarMarcas) {
		this.listarMarcas = listarMarcas;
	}
	
	public void novo() {
		marca = new Marca();
	}
	
	
	@PostConstruct
	public void listar() {
		try {
			MarcaDAO marcaDAO = new MarcaDAO();
			listarMarcas = marcaDAO.listar("nome_marca");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar as Marcas.");
			error.printStackTrace();
		}
	}
	
	public void salvar() {

		try {
			MarcaDAO marcaDAO = new MarcaDAO();
			marcaDAO.merge(marca);

			marca = new Marca();

			listarMarcas = marcaDAO.listar("nome_marca");
			Messages.addGlobalInfo("Marca Cadastrada com Sucesso.");
			
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Cadastrar a Marca.");
			error.printStackTrace();
		}

	}
	
	public void editar(ActionEvent evento) {
		marca = (Marca) evento.getComponent().getAttributes().get("marcaSelecionado");
	}
	
	public void excluir(ActionEvent evento) {
		try {
			marca = (Marca) evento.getComponent().getAttributes().get("marcaSelecionado");
			MarcaDAO marcaDAO = new MarcaDAO();
			marcaDAO.excluir(marca);
			listarMarcas = marcaDAO.listar("nome_marca");
			Messages.addGlobalInfo("Marca removida com sucesso");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Remover a Marca.");
			error.printStackTrace();
		}
	}
	
}
