package br.com.moleka.model.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.com.moleka.model.dominio.Pedido;

public class PedidoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	
	DAO<Pedido> dao;
	
	
	public PedidoDAO(EntityManager entityManager){
		this.entityManager = entityManager;
		dao = new DAOImpl<Pedido>(Pedido.class,entityManager);
	}
	
	public void salvar(Pedido pedido) throws Exception{
		dao.salvar(pedido);				
	}
	
	public void atualizar(Pedido pedido){
		dao.atualizar(pedido);
	}
	

}
