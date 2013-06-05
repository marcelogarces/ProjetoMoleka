package br.com.moleka.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import br.com.moleka.model.dominio.Estado;

public class EstadoDAO {
	
	private EntityManager entityManager;
	
	private DAO<Estado> dao; 
	
	public EstadoDAO(EntityManager entityManager){
		this.entityManager = entityManager;
		dao = new DAOImpl<Estado>(Estado.class,entityManager);
	}
	

	public List<Estado>listarEstados(){
		return dao.listarTodos();
	}

}
