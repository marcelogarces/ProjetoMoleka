package br.com.moleka.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.moleka.model.dominio.Cidade;
import br.com.moleka.model.dominio.Estado;

public class CidadeDAO {
	
	private EntityManager entityManager;
	private DAO<Cidade> dao; 
	
	public CidadeDAO(EntityManager entityManager){
		this.entityManager = entityManager;
		dao = new DAOImpl<Cidade>(Cidade.class,entityManager);
	}
	
	public List<Cidade>obterCidadePorEstado(Estado estado){
		Query query = entityManager.createQuery("from Cidade  c where c.estado = :estado ");
		query.setParameter("estado",estado);
		return query.getResultList();
	}
	
	public List<Cidade>listarCidades(){
	   return dao.listarTodos();	
	}
}
