package br.com.horus.util;

import org.hibernate.Session;
import org.junit.Test;

import br.com.horus.util.HibernateUtil;

public class HibernateUtilTest {
	@Test
	public void conectarBd() {
		// CAPTURAR A SESSÃO ABERTA
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.close();
		// DESTROI A SESSÃO
		HibernateUtil.getSessionFactory().close();
	}
}
