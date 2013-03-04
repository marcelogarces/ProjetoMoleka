package br.com.moleka.util;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

public class FacesContextUtil {
	
	private static final String ENTITY_MANAGER = "entityManager";

	public static void setRequestEntityManager(EntityManager em)
	{
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(ENTITY_MANAGER,em);
	}
	
	public static EntityManager getRequestEntityManager(){
		
		return (EntityManager) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(ENTITY_MANAGER);
	}
	
    
	
	public static void setMensagemErro(String mensagem) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, mensagem);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	public static void setMensagemInfo(String mensagem) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	/*

	
	public static Object getSessionAttribute(String nomeAtributo) {
		return ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute(nomeAtributo);
	}
   */
	public static void setNavegacao(String outcome) {
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, outcome);
	}

  
}
