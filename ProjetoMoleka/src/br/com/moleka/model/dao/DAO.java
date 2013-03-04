package br.com.moleka.model.dao;

import java.util.List;

public interface DAO<T>{

	public abstract void salvar(T bean);
	public abstract void atualizar(T bean);
	public abstract void excluir(T bean);
	public abstract T getBean(Long codigo);
	public abstract List<T>listarTodos();
	public abstract List<T>getBeansByExample(T bean);

}
