package br.com.moleka.util;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames= {"Faces Servlet"})
public class JPAFilter implements Filter {
	
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void destroy() {
		this.entityManagerFactory.close();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain fc) throws IOException, ServletException {
		
		System.out.println("Obtendo Entity Manager da f�brica...");
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		
		req.setAttribute("entityManager", entityManager);
		System.out.println("Iniciando transa��o........");
		entityManager.getTransaction().begin();
		System.out.println("Inserindo o entity manager para requisi��o...");
		fc.doFilter(req, res);
		
		try {
			System.out.println("Realizando commit na transa��o...");
			entityManager.getTransaction().commit();

		} catch (PersistenceException pe) {

			System.out.println("Ocorreu Erro ou Falha na transa��o...");
			System.out.println("Verificando se transa��o est� ativa...");
			if (entityManager.getTransaction().isActive()) {
				System.out.println("Executando rollback na transa��o...");
				entityManager.getTransaction().rollback();
				// throw new ServletException(pe.toString());
			}
		} finally {
			System.out.println("Fechando Entity manager...");
			entityManager.close();
		}
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("moleka");
		
	}

}
