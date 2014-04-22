package br.com.moleka.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import br.com.moleka.model.dominio.TipoProduto;

public class TipoProdutoDAO {
	
	private EntityManager entityManager;
	
	DAO<TipoProduto> dao;
	
	public TipoProdutoDAO(EntityManager entityManager){
		this.entityManager = entityManager;
		dao = new DAOImpl<TipoProduto>(TipoProduto.class,entityManager);
	}
	
	public List<TipoProduto>listarTodos(){
	
		return dao.listarTodos();
	}
	
	public TipoProduto obterTipoProdutoPorCodigo(Long codigo){
		return dao.getBean(codigo);
	}

}
