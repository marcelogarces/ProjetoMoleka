package br.com.moleka.model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.moleka.model.dominio.Produto;
import br.com.moleka.util.FacesContextUtil;

public class ProdutoDAO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;
	
	DAO<Produto> dao;
	
	public ProdutoDAO(EntityManager entityManager){
		this.entityManager = entityManager;
		dao = new DAOImpl<Produto>(Produto.class,entityManager);
	}
	
	public List<Produto>listarTodos(){
	
		return dao.listarTodos();
	}
	
	public void salvar(Produto produto){
		dao.salvar(produto);
	}
	
	public void excluir(Produto produto){
		dao.excluir(produto);
	}
	
	public Produto obterProdutoPorCodigo(Long id){
		return dao.getBean(id);
	}

}
