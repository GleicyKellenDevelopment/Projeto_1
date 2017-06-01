package br.com.horus.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.horus.model.Cidade;
import br.com.horus.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Long idEstado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			//WHERE PARA PESQUISAR ESTADO			
			consulta.add(Restrictions.eq("estado.id", idEstado));
			consulta.addOrder(Order.asc("nome"));
			
			List<Cidade> lista = consulta.list();
			return lista;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}