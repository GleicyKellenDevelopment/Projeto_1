package br.com.horus.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.horus.dao.ClienteDAO;
import br.com.horus.dao.FuncionarioDAO;
import br.com.horus.dao.ProdutoDAO;
import br.com.horus.dao.VendaDAO;
import br.com.horus.model.Cliente;
import br.com.horus.model.Funcionario;
import br.com.horus.model.Pedido;
import br.com.horus.model.Produto;
import br.com.horus.model.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

	private Venda venda;
	private List<Produto> listarProdutos;
	private List<Pedido> listaPedidos;
	private List<Cliente> listarClientes;
	private List<Funcionario> listarFuncionarios;
	
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
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
	
	public List<Cliente> getListarClientes() {
		return listarClientes;
	}

	public void setListarClientes(List<Cliente> listarClientes) {
		this.listarClientes = listarClientes;
	}

	public List<Funcionario> getListarFuncionarios() {
		return listarFuncionarios;
	}

	public void setListarFuncionarios(List<Funcionario> listarFuncionarios) {
		this.listarFuncionarios = listarFuncionarios;
	}

	
	@PostConstruct
	public void listarProduto() {
		try {
			
			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listarProdutos = produtoDAO.listar("nome_produto");

			listaPedidos = new ArrayList<>();

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar as vendas.");
			error.printStackTrace();
		}
	}

	public void adicionarProduto(ActionEvent evento) {

		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < listaPedidos.size(); posicao ++) {
			if (listaPedidos.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			Pedido pedido = new Pedido();
			pedido.setPreco_parcial(produto.getValor_unitario());
			pedido.setProduto(produto);
			pedido.setQuantidade(new Short("1"));

			listaPedidos.add(pedido);
		} else {
			Pedido pedido = listaPedidos.get(achou); // pega a posicao do produto
			pedido.setQuantidade(new Short(pedido.getQuantidade() + 1 + ""));
			// uso das aspas = quantidade vira para string e depois e adicionada para short
			pedido.setPreco_parcial(produto.getValor_unitario().multiply(new BigDecimal(pedido.getQuantidade())));
		}
		
		calcularTotal();
	}
	
	public void removerPedido(ActionEvent evento) {
		Pedido pedido = (Pedido) evento.getComponent().getAttributes().get("pedidoSelecionado");
		
		int achou = -1;
		
		for (int posicao = 0; posicao < listaPedidos.size(); posicao ++) {
			if (listaPedidos.get(posicao).getProduto().equals(pedido.getProduto())) {
				achou = posicao;
			}
		}
		
		if (achou > -1) {
			listaPedidos.remove(achou);
		}
		
		calcularTotal();
	}
	
	public void calcularTotal() {
		venda.setPrecoTotal(new BigDecimal("0.00"));
		
		for (int posicao = 0; posicao < listaPedidos.size(); posicao++) {
			
			Pedido pedido = listaPedidos.get(posicao);
			venda.setPrecoTotal( venda.getPrecoTotal().add(pedido.getPreco_parcial()) );
			
		}
	}
	
	public void finalizar() {
		try {
			
			venda.setHorario(new Date());
			venda.setCliente(null);
			venda.setFuncionario(null);
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			listarFuncionarios = funcionarioDAO.listarOrdenado();
			
			ClienteDAO clienteDAO = new ClienteDAO();
			listarClientes = clienteDAO.listarOrdenado();
			
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Finalizar a venda.");
			error.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			
			if (venda.getPrecoTotal().signum() == 0) { // Pega a parte inteira do big decimal
				Messages.addGlobalError("Informe pelo menos um item para a venda.");
				return;
			}
			
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda, listaPedidos);
			listarProduto();
			
			Messages.addGlobalInfo("Venda Salva com Sucesso.");
			
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Salvar a venda.");
			error.printStackTrace();
		}
	}
}
