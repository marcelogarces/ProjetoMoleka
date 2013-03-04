package br.com.moleka.managedBean;

import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import br.com.moleka.model.dao.EnderecoDAO;
import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dominio.Endereco;
import br.com.moleka.model.dominio.Pessoa;
import br.com.moleka.util.FacesContextUtil;

@RequestScoped
@ManagedBean
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	EntityManager entityManager;
	private List<Pessoa>pessoas;
	private PessoaDAO pessoaDAO;
	

	public PessoaBean(){	
		entityManager = FacesContextUtil.getRequestEntityManager();
		pessoaDAO = new PessoaDAO(entityManager);
		pessoa.setEndereco(new Endereco());
		pessoas = pessoaDAO.listarTodasPessoas();
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void salvar(){
		 			
		EnderecoDAO enderecoDAO = new EnderecoDAO(entityManager);	
		enderecoDAO.salvar(pessoa.getEndereco());
		pessoaDAO.salvar(pessoa);	
		FacesContextUtil.setMensagemInfo("Registro salvo com sucesso");
		pessoa = new Pessoa();
		pessoas = pessoaDAO.listarTodasPessoas();
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
}
