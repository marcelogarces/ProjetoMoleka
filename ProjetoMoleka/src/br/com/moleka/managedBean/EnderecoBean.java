package br.com.moleka.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.com.moleka.model.dao.EstadoDAO;
import br.com.moleka.model.dominio.Estado;
import br.com.moleka.util.FacesContextUtil;

@ManagedBean
public class EnderecoBean  {

	private List<Estado> estados;
	private EntityManager entityManager;
	private EstadoDAO estadoDAO;
	
	
	public EnderecoBean(){
		entityManager = FacesContextUtil.getRequestEntityManager();
		estadoDAO = new EstadoDAO(entityManager);
		estados = estadoDAO.listarEstados();
	}
	
	
	public List<Estado> getEstados() {
		return estados;
	}


	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}	
	public List<String> complete(String query){
		
		List<String> listCidades = new ArrayList<String>();
		
		listCidades.add("Nil�polis");
		listCidades.add("Nova Igua�u");
		listCidades.add("Japeri");
		listCidades.add("Mesquita");
		listCidades.add("Cabo Frio");
		listCidades.add("Niteroi");
		listCidades.add("Rio de Janeiro");
		listCidades.add("Arraial do Cabo");
		listCidades.add("Natividade");
		listCidades.add("Caxias");
		
		return listCidades;
		
	}

}
