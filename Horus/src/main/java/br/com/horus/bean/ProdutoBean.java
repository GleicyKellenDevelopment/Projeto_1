package br.com.horus.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

import br.com.horus.dao.MarcaDAO;
import br.com.horus.dao.ProdutoDAO;
import br.com.horus.model.Marca;
import br.com.horus.model.Produto;
import br.com.horus.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

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
			listarProdutos = produtoDAO.listar("nome_produto");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Listar os produtos.");
			error.printStackTrace(); 
		}
	}
	
	public void novo() {
		try {
			produto = new Produto();
			
			MarcaDAO marcaDAO = new MarcaDAO();
			listarMarcas = marcaDAO.listar();
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Gerar um produto.");
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
			listarProdutos = produtoDAO.listar("nome_produto");
			
			Messages.addGlobalInfo("Produto Salvo com Sucesso.");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Salvar o produto.");
			error.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			MarcaDAO marcaDAO = new MarcaDAO();
			listarMarcas = marcaDAO.listar();

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro Selecionar um produto.");
			error.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			listarProdutos = produtoDAO.listar("nome_produto");
			Messages.addGlobalInfo("Produto Removido com Sucesso.");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Erro ao Remover o produto.");
			error.printStackTrace();
		}
	}

	public void imprimir() {
		
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("listagemProdutos:tabelaProdutos");
			Map<String, Object> filtros = tabela.getFilters();

			String proDescricao = (String) filtros.get("nome_produto");
			String fabDescricao = (String) filtros.get("marca.nome_marca");

			String caminho = Faces.getRealPath("/reports/produtos.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (proDescricao == null) {
				parametros.put("PRODUTO_NOME", "%%");
			} else {
				parametros.put("PRODUTO_NOME", "%" + proDescricao + "%");
			}
			if (fabDescricao == null) {
				parametros.put("MARCA_NOME", "%%");
			} else {
				parametros.put("MARCA_NOME", "%" + fabDescricao + "%");
			}

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
}
