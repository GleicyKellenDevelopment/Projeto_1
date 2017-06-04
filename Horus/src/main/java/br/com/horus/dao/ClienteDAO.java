package br.com.horus.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.horus.model.Cliente;
import br.com.horus.util.HibernateUtil;

public class ClienteDAO extends GenericDAO<Cliente> {
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listarOrdenado() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cliente.class);
			// primeiro o nome do objeto que vc vai buscar e depois apelida
			consulta.createAlias("pessoa", "p");
			consulta.addOrder(Order.asc("p.nome"));
			List<Cliente> lista = consulta.list();
			return lista;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
