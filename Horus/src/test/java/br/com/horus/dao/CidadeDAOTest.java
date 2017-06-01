package br.com.horus.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.horus.dao.CidadeDAO;
import br.com.horus.dao.EstadoDAO;
import br.com.horus.model.Cidade;
import br.com.horus.model.Estado;

public class CidadeDAOTest {
	@Test
	@Ignore
	public void salvar() {
		// PESQUISAR CHAVE ESTRANGEIRA ANTES
		Long id = 3L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(id);

		if (estado == null)
			System.out.println("NENHUM REGISTRO ENCONTRADO");

		Cidade cidade = new Cidade();
		cidade.setNome("Brasilia");
		cidade.setEstado(estado);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}

	@Test
	//@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> listarCidades = cidadeDAO.listar();

		if (listarCidades == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {

			for (Cidade cidade : listarCidades) {
				System.out.println(cidade.getNome() + " - " + cidade.getEstado().getNome() + " - "
						+ cidade.getEstado().getSigla());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long id = 9L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(id);

		if (cidade == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {
			System.out.println(
					cidade.getNome() + " - " + cidade.getEstado().getNome() + " - " + cidade.getEstado().getSigla());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long idEstado = 7L;
		Long idCidade = 1L;
		// BUSCAR ESTADO
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(idEstado);
		// BUSCAR CIDADE
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(idCidade);

		if (cidade == null || estado == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		}

		cidade.setNome("Brasília");
		cidade.setEstado(estado);
		cidadeDAO.editar(cidade);

	}

	@Test
	@Ignore
	public void excluir() {
		Long id = 5L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(id);

		if (cidade == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		}

		cidadeDAO.excluir(cidade);

	}

}
