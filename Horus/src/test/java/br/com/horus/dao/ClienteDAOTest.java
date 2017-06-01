package br.com.horus.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.horus.dao.ClienteDAO;
import br.com.horus.dao.PessoaDAO;
import br.com.horus.model.Cliente;
import br.com.horus.model.Pessoa;

public class ClienteDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(4L);

		Cliente cliente = new Cliente();

		// cliente.setDataCadastro(new Date());
		// DATA PADRÃO BRASILEIRO //
		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("22/05/2017"));
		cliente.setPessoa(pessoa);
		cliente.setAtivo(true);

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);
	}

	@Test
	@Ignore
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> listarCLientes = clienteDAO.listar();

		if (listarCLientes == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {

			for (Cliente cliente : listarCLientes) {
				System.out.println(
						cliente.getPessoa().getNome() + " - " + cliente.getAtivo() + " - " + cliente.getDataCadastro());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long id = 2L;
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(id);

		if (cliente == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {
			System.out.println(
					cliente.getPessoa().getNome() + " - " + cliente.getAtivo() + " - " + cliente.getDataCadastro());
		}
	}

	@Test
	@Ignore
	public void editar() throws ParseException {
		Long idPessoa = 2L;
		Long idCliente = 1L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(idPessoa);

		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(idCliente);

		if (pessoa == null || cliente == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		}

		cliente.setAtivo(true);
		cliente.setPessoa(pessoa);
		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("22/05/2015"));
		clienteDAO.editar(cliente);

	}

	@Test
	@Ignore
	public void excluir() {
		Long id = 2L;

		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(id);

		if (cliente == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		}

		clienteDAO.excluir(cliente);
	}

}
