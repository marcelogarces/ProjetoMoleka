package br.com.moleka.managedBean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EntityManager;
import br.com.moleka.model.dao.CidadeDAO;
import br.com.moleka.model.dao.EnderecoDAO;
import br.com.moleka.model.dao.EstadoDAO;
import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dominio.Cidade;
import br.com.moleka.model.dominio.Endereco;
import br.com.moleka.model.dominio.Estado;
import br.com.moleka.model.dominio.Pessoa;
import br.com.moleka.util.FacesContextUtil;


@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	EntityManager entityManager;
	
	private Pessoa pessoa = new Pessoa();
	
	private  PessoaDAO pessoaDAO;
	private EstadoDAO estadoDAO;
	private EnderecoDAO enderecoDAO;
	
	private List<Pessoa> pessoas;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	private Estado estadoSelecionado;
	private Cidade cidadeSelecionada;
	

	public PessoaBean(){	
		entityManager = FacesContextUtil.getRequestEntityManager();
		pessoaDAO = new PessoaDAO(entityManager);
		pessoa.setEndereco(new Endereco());
		pessoas = pessoaDAO.listarTodasPessoas();
	}
	
	@PostConstruct
	public void init() {
		estadoDAO = new EstadoDAO(FacesContextUtil.getRequestEntityManager());
		estados = estadoDAO.listarEstados();
	}
	

	public void listarCidades(AjaxBehaviorEvent event) {	
		CidadeDAO cidadeDAO = new CidadeDAO(FacesContextUtil.getRequestEntityManager());
		estadoDAO = new EstadoDAO(FacesContextUtil.getRequestEntityManager());	
		System.out.println("estado selecionado e........" + estadoSelecionado);
		cidades = cidadeDAO.obterCidadePorEstado(estadoSelecionado);
	}
	
	public void salvar(){
		
		EntityManager entityManagerRequisicao = FacesContextUtil.getRequestEntityManager();
		enderecoDAO = new EnderecoDAO(entityManagerRequisicao);
		pessoaDAO = new PessoaDAO(entityManagerRequisicao);
		pessoa.getEndereco().setCidade(cidadeSelecionada);
		
		if(pessoa.getId() == null){
			enderecoDAO.salvar(pessoa.getEndereco());
			pessoaDAO.salvar(pessoa);	
			FacesContextUtil.setMensagemInfo("Registro salvo com sucesso");
		}else{
			pessoaDAO.atualizar(pessoa);
			enderecoDAO.atualizar(pessoa.getEndereco());
			FacesContextUtil.setMensagemInfo("Registro atualizado com sucesso.");
		}
		pessoa = new Pessoa();
		estadoSelecionado = new Estado();
		cidadeSelecionada = new Cidade();
		pessoas = pessoaDAO.listarTodasPessoas();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		if(pessoa.getId() != null){
			CidadeDAO cidadeDAO = new CidadeDAO(FacesContextUtil.getRequestEntityManager());
			cidadeSelecionada = pessoa.getEndereco().getCidade();
			estadoSelecionado = pessoa.getEndereco().getCidade().getEstado();
			cidades = cidadeDAO.obterCidadePorEstado(estadoSelecionado);
		}
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}
	
	
}
