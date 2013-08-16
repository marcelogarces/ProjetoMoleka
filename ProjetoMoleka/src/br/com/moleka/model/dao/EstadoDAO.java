package br.com.moleka.model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.moleka.model.dominio.Estado;

public class EstadoDAO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;
	
	private DAO<Estado> dao; 
	
	public EstadoDAO(EntityManager entityManager){
		this.entityManager = entityManager;
		dao = new DAOImpl<Estado>(Estado.class,entityManager);
	}
	

	public List<Estado>listarEstados(){
		return dao.listarTodos();
	}
	
	public Estado obterEstadoPorId(Long id){
		return dao.getBean(id);
	}
	
	

}
