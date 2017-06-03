package br.com.horus.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.horus.dao.ProdutoDAO;
import br.com.horus.model.Pedido;
import br.com.horus.model.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

	private List<Produto> listarProdutos;
	private List<Pedido> listaPedidos;

	public List<Produto> getListarProdutos() {
		return listarProdutos;
	}

	public void setListarProdutos(List<Produto> listarProdutos) {
		this.listarProdutos = listarProdutos;
	}

	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	@PostConstruct
	public void listarProduto() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listarProdutos = produtoDAO.listar("nome_produto");
			
			listaPedidos = new ArrayList<>();
			
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar as Vendas.");
			error.printStackTrace();
		}
	}
	
	public void adicionarProduto(ActionEvent evento) {
		
			Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			//Converter Produto para Pedido
			Pedido pedido = new Pedido();
			// Preencher
			pedido.setPreco_parcial(produto.getPreco());
			pedido.setProduto(produto);
			pedido.setQuantidade(new Short("1"));
			
			listaPedidos.add(pedido);

	}

}
