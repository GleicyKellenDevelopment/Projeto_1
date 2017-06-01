package br.com.horus.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.horus.dao.MarcaDAO;
import br.com.horus.dao.ProdutoDAO;
import br.com.horus.model.Marca;
import br.com.horus.model.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		MarcaDAO marcaDAO = new MarcaDAO();
		Marca marca = marcaDAO.buscar(1L);

		Produto produto = new Produto();
		produto.setNome_produto("Carregador Televisor");
		produto.setMarca(marca);
		produto.setQtd_estoque(new Short("5"));
		produto.setPreco(new BigDecimal("35.00"));

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);

	}

	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> listarProdutos = produtoDAO.listar();

		if (listarProdutos == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {

			for (Produto produto : listarProdutos) {
				System.out.println(produto.getNome_produto() + " - " + produto.getMarca().getNome_marca() + " - "
						+ produto.getQtd_estoque() + " - " + produto.getPreco());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long id = 2L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(id);

		if (produto == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {
			System.out.println(produto.getNome_produto() + " - " + produto.getMarca().getNome_marca() + " - "
					+ produto.getQtd_estoque() + " - " + produto.getPreco());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long idMarca = 1L;
		Long idProduto = 2L;

		// BUSCAR MARCA
		MarcaDAO marcaDAO = new MarcaDAO();
		Marca marca = marcaDAO.buscar(idMarca);
		// BUSCAR PRODUTO
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(idProduto);
		
		if (marca == null || produto == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		}
		
		produto.setNome_produto("Carregador Celular");
		produto.setMarca(marca);
		produtoDAO.editar(produto);
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long id = 4L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(id);
		
		if (produto == null)
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		
		produtoDAO.excluir(produto);
		
	}

}
