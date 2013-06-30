package br.com.moleka.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dominio.Pessoa;

public class Teste {
	
	public List<String> nomes = new ArrayList<String>();
	
	public Set<String> teste = new TreeSet<String>();
	
	
	public static void main(String[] args) {
		
		Teste t = new Teste();
		
		t.nomes.add("teste 1");
		t.nomes.add("teste 2");
		t.nomes.add("teste 3");
		t.nomes.add("teste 5");
		t.nomes.add("teste 5");
		
		for(String nome : t.nomes){
			System.out.println("nome: " + nome);
		}
		
		t.teste.addAll(t.nomes);
		
		for(String nome : t.teste){
			System.out.println("nome.....: " + nome);
		}
		
	}

}
