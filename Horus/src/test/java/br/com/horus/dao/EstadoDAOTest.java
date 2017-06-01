package br.com.horus.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.horus.dao.EstadoDAO;
import br.com.horus.model.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();

		estado.setNome("Distrito Federal");
		estado.setSigla("DF");
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();

		List<Estado> listarEstados = estadoDAO.listar();
		System.out.println("TOTAL DE RESULTADOS ENCONTRADOS:" + listarEstados.size());
		// EXIBIR
		for (Estado estado : listarEstados) {
			System.out.println(estado.getNome() + " - " + estado.getSigla());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long id = 3L;
		EstadoDAO estadoDAO = new EstadoDAO();

		Estado estado = estadoDAO.buscar(id);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado:");
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long id = 6L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(id);
		// FORMA MANUAL
		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			estado.setSigla("CE");
			estadoDAO.editar(estado);
			System.out.println("Registro editado:");
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long id = 4L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(id);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			estadoDAO.excluir(estado);
			System.out.println("Registro encontrado:");
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
}
