package br.com.horus.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.horus.model.Funcionario;
import br.com.horus.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario>{

	@SuppressWarnings("unchecked")
	public List<Funcionario> listarOrdenado() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			// primeiro o nome do objeto que vc vai buscar e depois apelida
			consulta.createAlias("pessoa", "p");
			consulta.addOrder(Order.asc("p.nome"));
			List<Funcionario> lista = consulta.list();
			return lista;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
