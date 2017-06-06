package br.com.horus.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.horus.model.Pedido;
import br.com.horus.model.Produto;
import br.com.horus.model.Venda;
import br.com.horus.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda>{

	public void salvar(Venda venda, List<Pedido> listaPedidos) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(venda); // aqui pega chave primaria
			
			for (int posicao = 0; posicao < listaPedidos.size(); posicao++) {
				Pedido pedido = listaPedidos.get(posicao);
				pedido.setVenda(venda);
				sessao.save(pedido);
				
				Produto produto = pedido.getProduto();
				produto.setQtd_estoque(new Short( (produto.getQtd_estoque() - pedido.getQuantidade()) + "") );
				
				sessao.update(produto);
			}
			
			transacao.commit();
			
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
		}
}
