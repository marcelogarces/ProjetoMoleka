package br.com.moleka.model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.moleka.model.dominio.Produto;
import br.com.moleka.model.dominio.TipoProduto;

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
	
	public void atualizar(Produto produto){
		dao.atualizar(produto);
	}
	
	public void excluir(Produto produto){
		dao.excluir(produto);
	}
	
	public Produto obterProdutoPorCodigo(Long id){
		return dao.getBean(id);
	}
	
	public List<Produto>obterTodosProdutosPorTipo(TipoProduto tipoProduto){
		TypedQuery<Produto> query = entityManager.createQuery("from Produto p where p.tipoProduto= :tipoProduto",Produto.class);
		query.setParameter("tipoProduto", tipoProduto);
		List<Produto> produtos = query.getResultList();
		return produtos;
		
	}

}
