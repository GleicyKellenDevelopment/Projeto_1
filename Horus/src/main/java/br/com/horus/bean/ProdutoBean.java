package br.com.horus.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.horus.dao.MarcaDAO;
import br.com.horus.dao.ProdutoDAO;
import br.com.horus.model.Marca;
import br.com.horus.model.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable{
	
	private Produto produto;
	private List<Produto> listarProdutos;
	private List<Marca> listarMarcas;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getListarProdutos() {
		return listarProdutos;
	}
	public void setListarProdutos(List<Produto> listarProdutos) {
		this.listarProdutos = listarProdutos;
	}
	public List<Marca> getListarMarcas() {
		return listarMarcas;
	}
	public void setListarMarcas(List<Marca> listarMarcas) {
		this.listarMarcas = listarMarcas;
	}
	
	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listarProdutos = produtoDAO.listar();
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar os Produtos.");
			error.printStackTrace(); 
		}
	}
	
	public void novo() {
		try {
			produto = new Produto();
			
			MarcaDAO marcaDAO = new MarcaDAO();
			listarMarcas = marcaDAO.listar();
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Gerar um Produto.");
			error.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);
			
			produto = new Produto();
			MarcaDAO marcaDAO = new MarcaDAO();
			listarMarcas = marcaDAO.listar();
			listarProdutos = produtoDAO.listar();
			
			Messages.addGlobalInfo("Produto Salvo com Sucesso.");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Salvar o Produto.");
			error.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			MarcaDAO marcaDAO = new MarcaDAO();
			listarMarcas = marcaDAO.listar();

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro Selecionar um Produto.");
			error.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			listarProdutos = produtoDAO.listar();
			Messages.addGlobalInfo("Produto removido com sucesso.");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Remover o Produto.");
			error.printStackTrace();
		}
	}


}
