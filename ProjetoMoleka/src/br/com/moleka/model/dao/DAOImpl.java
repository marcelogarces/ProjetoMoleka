package br.com.moleka.model.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Selection;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;


class DAOImpl<T> implements DAO<T>,Serializable {

	private static final long serialVersionUID = 1L;

	private Class<?> classe;
	
	private EntityManager entityManager;
	
	public DAOImpl(Class<?> classe,EntityManager entityManager) {
		this.classe = classe;
		this.entityManager = entityManager;	
	}

	@Override
	public void salvar(T bean) {
		entityManager.persist(bean);
	}

	@Override
	public void atualizar(T bean) {
		entityManager.merge(bean);
	}

	@Override
	public void excluir(T bean) {
		entityManager.remove(bean);
	}

	@Override
	public T getBean(Long codigo) {
		T instancia = (T) entityManager.find(classe,codigo);
		return instancia;
	}

	@Override
	public List<T> listarTodos() {

		CriteriaQuery<T> query = (CriteriaQuery<T>) entityManager.getCriteriaBuilder().createQuery(classe);
		query.select((Selection<? extends T>) query.from(classe));

		List<T> lista = entityManager.createQuery(query).getResultList();
	
		return lista;
	}

	@Override
	public List<T> getBeansByExample(T bean) {
		Example example  = Example.create(bean);
		example.enableLike(MatchMode.START);
		example.ignoreCase();
		example.excludeZeroes();
		
		return null;
	}
	


}
