package br.com.horus.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.horus.dao.MarcaDAO;
import br.com.horus.model.Marca;


public class MarcaDAOTest {
	
	@Test
//	@Ignore
	public void salvar() {
		
		Marca marca = new Marca();
		marca.setNome_marca("Samsung");
		MarcaDAO marcaDao = new MarcaDAO();
		marcaDao.salvar(marca);
	}
	
	@Test
	@Ignore
	public void listar() {
		
		MarcaDAO marcaDAO = new MarcaDAO();
		List<Marca> listarMarcas = marcaDAO.listar();
		System.out.println("TOTAL DE RESULTADOS ENCONTRADOS:" + listarMarcas.size());
		//EXIBIR
		for(Marca marca: listarMarcas){
			System.out.println(marca.getNome_marca());
		}
	}
	
	@Test
	@Ignore
	public void buscar() {

		Long id = 1L;

		MarcaDAO marcaDAO = new MarcaDAO();
		Marca marca = marcaDAO.buscar(id);

		if(marca == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado:");
			System.out.println(marca.getId() + " - " + marca.getNome_marca());
		}

	}
	
	@Test
	@Ignore
	public void editar() {
		Long id = 1L;
		MarcaDAO marcaDAO = new MarcaDAO();
		Marca marca = marcaDAO.buscar(id);

		if (marca == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			marca.setNome_marca("Motorola");
			marcaDAO.editar(marca);
			System.out.println("Registro editado para:");
			System.out.println(marca.getNome_marca());
		}
	}
	
	
	@Test
	@Ignore
	public void excluir() {
		Long id = 4L;
		MarcaDAO marcaDAO = new MarcaDAO();
		Marca marca = marcaDAO.buscar(id);

		if (marca == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			marcaDAO.excluir(marca);
			System.out.println("Registro Excluido:");
			System.out.println(marca.getNome_marca());
		}
	}
	
	@Test
	@Ignore
	public void mergeIncluir() {
		
		Marca marca = new Marca();
		marca.setNome_marca("Xaomi");
		MarcaDAO marcaDao = new MarcaDAO();
		marcaDao.merge(marca);
	}
	
	@Test
	@Ignore
	public void mergeEditar() {
		
		MarcaDAO marcaDAO = new MarcaDAO();
		Marca marca = marcaDAO.buscar(2L);
		marca.setNome_marca("MultiLaser");
		marcaDAO.merge(marca);
		
	}
}
