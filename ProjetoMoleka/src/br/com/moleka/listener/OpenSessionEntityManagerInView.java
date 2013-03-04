package br.com.moleka.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.moleka.util.FacesContextUtil;
import br.com.moleka.util.JPAUtil;

public class OpenSessionEntityManagerInView implements PhaseListener{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void beforePhase(PhaseEvent fase) {

		if (fase.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {

			System.out.println("Obtendo Entity Manager da f�brica...");
			EntityManager em = JPAUtil.getEntityManager();
			System.out.println("Inianado transa��o........");
			em.getTransaction().begin();
			System.out.println("Inserindo o entity manager para requisi��o...");
			FacesContextUtil.setRequestEntityManager(em);
		}

	}

	@Override
	public void afterPhase(PhaseEvent fase) {

		if (fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
			EntityManager em = FacesContextUtil.getRequestEntityManager();
			try {

				System.out.println("Realizando commit na transa��o...");
				em.getTransaction().commit();

			} catch (PersistenceException pe) {

				System.out.println("Ocorreu Erro ou Falha na transa��o...");
				System.out.println("Verificando se transa��o est� ativa...");
				if (em.getTransaction().isActive()) {
					System.out.println("Executando rollback na transa��o...");
					em.getTransaction().rollback();

				}

			} finally {
				System.out.println("Fechando Entity manager...");
				em.close();
			}

		}

	}

	@Override
	public PhaseId getPhaseId() {

		return PhaseId.ANY_PHASE;
	}

}
