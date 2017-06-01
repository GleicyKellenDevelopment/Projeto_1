package br.com.horus.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.horus.dao.PessoaDAO;
import br.com.horus.dao.UsuarioDAO;
import br.com.horus.model.Pessoa;
import br.com.horus.model.Usuario;

public class UsuarioDAOTest {
	@Test
	@Ignore
	public void salvar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(4L);

		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenha("123456");
		// CARACTER ASPAS SIMPLES
		usuario.setTipoUsuario('A');

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}

	@Test
	@Ignore
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> listarUsuarios = usuarioDAO.listar();

		if (listarUsuarios == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {

			for (Usuario usuario : listarUsuarios) {
				System.out.println(usuario.getPessoa().getNome() + " - " + usuario.getPessoa().getEmail() + " - "
						+ usuario.getTipoUsuario() + " - " + usuario.getAtivo());
			}
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long id = 2L;

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(id);

		if (usuario == null) {
			System.out.println("NENHUM REGISTRO ENCONTRADO");
		} else {
			System.out.println(usuario.getPessoa().getNome() + " - " + usuario.getPessoa().getEmail() + " - "
					+ usuario.getTipoUsuario() + " - " + usuario.getAtivo());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long idPessoa = 3L;
		Long idUsuario = 2L;

		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(idPessoa);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(idUsuario);

		if (usuario == null || pessoa == null)
			System.out.println("NENHUM REGISTRO ENCONTRADO");

		usuario.setAtivo(false);
		usuario.setSenha("102030");
		usuario.setTipoUsuario('A');
		usuario.setPessoa(pessoa);

		usuarioDAO.editar(usuario);

	}

	@Test
	@Ignore
	public void excluir() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(3L);

		if (usuario == null)
			System.out.println("NENHUM REGISTRO ENCONTRADO");

		usuarioDAO.excluir(usuario);
	}
}
