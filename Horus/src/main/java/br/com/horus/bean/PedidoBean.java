package br.com.horus.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.horus.dao.PedidoDAO;
import br.com.horus.model.Pedido;
import br.com.horus.model.Produto;
import br.com.horus.model.Venda;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class PedidoBean implements Serializable{
	
	private Pedido pedido;
	private List<Pedido> listarPedidos;
	private List<Produto> listarProdutos;
	private List<Venda> listarVendas;
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public List<Pedido> getListarPedidos() {
		return listarPedidos;
	}
	public void setListarPedidos(List<Pedido> listarPedidos) {
		this.listarPedidos = listarPedidos;
	}
	public List<Produto> getListarProdutos() {
		return listarProdutos;
	}
	public void setListarProdutos(List<Produto> listarProdutos) {
		this.listarProdutos = listarProdutos;
	}
	public List<Venda> getListarVendas() {
		return listarVendas;
	}
	public void setListarVendas(List<Venda> listarVendas) {
		this.listarVendas = listarVendas;
	}
	
	@PostConstruct
	public void listar() {
		try {
			PedidoDAO pedidoDAO = new PedidoDAO();
			listarPedidos = pedidoDAO.listar();
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar os pedidos.");
			error.printStackTrace(); 
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			pedido = (Pedido) evento.getComponent().getAttributes().get("pedidoSelecionado");
			PedidoDAO pedidoDAO = new PedidoDAO();
			pedidoDAO.excluir(pedido);
			listarPedidos = pedidoDAO.listar();
			Messages.addGlobalInfo("Pedido Removido com Sucesso.");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Remover o pedido.");
			error.printStackTrace();
		}
	}
	
}
