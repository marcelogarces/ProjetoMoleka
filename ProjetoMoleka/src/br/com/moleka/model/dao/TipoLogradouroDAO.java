package br.com.moleka.model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.moleka.model.dominio.TipoLogradouro;

public class TipoLogradouroDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	DAO<TipoLogradouro> dao;
	
	public TipoLogradouroDAO(EntityManager entityManager){
		this.entityManager = entityManager;
		dao = new DAOImpl<TipoLogradouro>(TipoLogradouro.class,entityManager);
	}
	
	public List<TipoLogradouro>listarTodos(){
		return dao.listarTodos();
	}
	
	public TipoLogradouro obterTipoLogradouroPorCodigo(Long codigo){
		return dao.getBean(codigo);
	}

}
