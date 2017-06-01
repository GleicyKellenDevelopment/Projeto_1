package br.com.horus.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.horus.model.Cliente;
import br.com.horus.model.Funcionario;
import br.com.horus.model.Venda;

public class VendaDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(3L);

		Venda venda = new Venda();

		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setPrecoTotal(new BigDecimal("5.00"));

		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
	}

	@Test
	@Ignore
	public void listar() {
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> listarVendas = vendaDAO.listar();

		if (listarVendas == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {

			for (Venda venda : listarVendas) {
				System.out.println(
						venda.getCliente().getPessoa().getNome() + " - " + venda.getFuncionario().getPessoa().getNome()
								+ " - " + venda.getHorario() + " - " + venda.getPrecoTotal());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(1L);

		if (venda == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {
			System.out.println(
					venda.getCliente().getPessoa().getNome() + " - " + venda.getFuncionario().getPessoa().getNome()
							+ " - " + venda.getHorario() + " - " + venda.getPrecoTotal());
		}
	}

	@Test
	@Ignore
	public void editar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(1L);

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);

		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(4L);

		venda.setHorario(new Date());
		venda.setFuncionario(funcionario);
		venda.setCliente(cliente);
		venda.setPrecoTotal(new BigDecimal("10.00"));
		vendaDAO.editar(venda);
	}

	@Test
	@Ignore
	public void excluir() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(4L);

		if (venda == null)
			System.out.println("NENHUM REGISTRO ENCONTRADO");

		vendaDAO.excluir(venda);
	}

}
