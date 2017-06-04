package br.com.horus.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.horus.model.Funcionario;
import br.com.horus.model.Pessoa;

public class FuncionarioDAOTest {
	@Test
	@Ignore
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setData_admisssao(new Date());
		funcionario.setData_nascimento(new SimpleDateFormat("dd/MM/yyyy").parse("22/05/1989"));
		funcionario.setPessoa(pessoa);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);
	}
	
	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> listarFuncionarios = funcionarioDAO.listar();
		
		if (listarFuncionarios == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {
			
			for (Funcionario funcionario : listarFuncionarios) {
				System.out.println(funcionario.getPessoa().getNome() + " - " 
					+ funcionario.getData_nascimento() + " - "
					+ funcionario.getData_admisssao()
				);
			}
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		
		if (funcionario == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {
			System.out.println(funcionario.getPessoa().getNome() + " - " 
					+ funcionario.getData_nascimento() + " - "
					+ funcionario.getData_admisssao()
				);
		}
	}
	
	@Test
	@Ignore
	public void editar() throws ParseException {
		Long idPessoa = 7L;
		Long idFuncionario = 1L;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(idPessoa);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(idFuncionario);
		
		if (pessoa == null || funcionario == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		}
		
		funcionario.setPessoa(pessoa);
		funcionario.setData_admisssao(new SimpleDateFormat("dd/MM/yyyy").parse("20/05/2017"));
		funcionarioDAO.editar(funcionario);
	}
	
	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(2L);
		
		if (funcionario == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		}
		
		funcionarioDAO.excluir(funcionario);
	}

}
