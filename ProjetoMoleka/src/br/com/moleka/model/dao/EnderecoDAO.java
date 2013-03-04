package br.com.moleka.model.dao;

import javax.persistence.EntityManager;
import br.com.moleka.model.dominio.Endereco;


public class EnderecoDAO {
	
	private EntityManager entityManager;
	
	private DAO<Endereco> dao; 
	
	public EnderecoDAO(EntityManager entityManager){
		this.entityManager = entityManager;
		dao = new DAOImpl<Endereco>(Endereco.class,entityManager);
	}
	
	public void salvar(Endereco endereco){
		dao.salvar(endereco);	
	}

}
