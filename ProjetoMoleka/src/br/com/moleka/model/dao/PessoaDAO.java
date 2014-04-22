package br.com.moleka.model.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.moleka.model.dominio.Pessoa;

public class PessoaDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	DAO<Pessoa> dao;
	
	public PessoaDAO(EntityManager entityManager){
		this.entityManager = entityManager;
		dao = new DAOImpl<Pessoa>(Pessoa.class,entityManager);
	}
	
	public void salvar(Pessoa pessoa) throws Exception{
		dao.salvar(pessoa);				
	}
	
	public void atualizar(Pessoa pessoa){
		dao.atualizar(pessoa);
	}

	public int contaTodos(Map<String,String> filtros) {

		long result = (Long) entityManager.createQuery("select count(p) from Pessoa p where p.nome like :nome").setParameter("nome", "%"+filtros.get("nome")+ "%").getSingleResult();
		return (int) result;		
		
	}
	
	public List<Pessoa>listarTodasPessoas(){
		//System.out.println("consultando pessoas...");
		List<Pessoa> pessoas = entityManager.createQuery("from Pessoa order by id desc").getResultList();	
		return pessoas;
	}

	public List<Pessoa> listarPessoaPorNomeLikePaginado(int firstResult, int maxResults,Map<String,String> filtros) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteriaQuery = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteriaQuery.from(Pessoa.class);
		if(filtros == null){
			filtros.put("nome", "");
		}
		criteriaQuery.where(builder.like(root.<String>get("nome"), "%"+filtros.get("nome")+"%")); 
		
		return entityManager.createQuery(criteriaQuery).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
		
	}
	
	public List<Pessoa> listarPessoaPorNomeETelefoneLike(String nome,String telefone) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteriaQuery = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteriaQuery.from(Pessoa.class);
		criteriaQuery.where(builder.like(root.<String>get("nome"), "%"+nome+"%"),builder.like(root.<String>get("telefone"), "%"+ telefone + "%"));
		return entityManager.createQuery(criteriaQuery).getResultList();
		
	}
	
	public List<Pessoa> listaTodosPaginada(int firstResult, int maxResults) {
		
		CriteriaQuery<Pessoa> query = entityManager.getCriteriaBuilder().createQuery(Pessoa.class);
		query.select(query.from(Pessoa.class));

		List<Pessoa> lista = entityManager.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
	
		return lista;
	}
	
	public Pessoa obterClienteBalcao(){
		return (Pessoa) entityManager.createQuery("from Pessoa p where p.nome=:nome").setParameter("nome","Balcão");
	}
	
	public Pessoa obterPessoaPorCodigo(Long codigo){
		return dao.getBean(codigo);
	}
	
}
