package br.com.moleka.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dominio.Pessoa;

public class Teste {
	
	private EntityManager em = JPAUtil.getEntityManager();
	
	
	public List<Pessoa> listarPessoaPorNomeLike(String str){
		
		Query query = em.createQuery("select p from Pessoa p where p.nome like :str");
		query.setParameter("str", "%" + str + "%");
		return (List<Pessoa>)query.getResultList();

	}
	
	/*
	public static void main(String[] args) {
		
		Teste t = new Teste();
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		List<Pessoa> pessoas = pessoaDAO.listaTodosPaginada(1, 10);
 
		for(Pessoa p : pessoas){
				
			System.out.println("Nome:... " + p.getNome() + "    Cidade:  " + p.getEndereco().getCidade());
		
		}
		
	}
*/
}
