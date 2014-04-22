package br.com.moleka.managedBean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import org.primefaces.component.tabview.TabView;
import br.com.moleka.model.dao.CidadeDAO;
import br.com.moleka.model.dao.EnderecoDAO;
import br.com.moleka.model.dao.EstadoDAO;
import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dao.TipoLogradouroDAO;
import br.com.moleka.model.dominio.Cidade;
import br.com.moleka.model.dominio.Endereco;
import br.com.moleka.model.dominio.Estado;
import br.com.moleka.model.dominio.Pessoa;
import br.com.moleka.model.dominio.TipoLogradouro;
import br.com.moleka.util.FacesContextUtil;


@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	EntityManager entityManager;
	
	private Pessoa pessoa = new Pessoa();
	
	private PessoaDAO pessoaDAO;
	private EstadoDAO estadoDAO;
	private EnderecoDAO enderecoDAO;
	private TipoLogradouroDAO tipoLogradouroDAO;
	
	private List<Pessoa> pessoas;
	private List<Estado> estados;
	private List<Cidade> cidades;
	private List<TipoLogradouro>tipoLogradouros;
	
	private Estado estadoSelecionado;
	private Cidade cidadeSelecionada;
	private TipoLogradouro tipoLogradouroSelecionado;
	
	private TabView tabView;
	
	private static final int ABA_CADASTRO = 0;
	

	public PessoaBean(){
		
		Logger logger = Logger.getLogger(PessoaBean.class);
		
		logger.info("pegando entity manager da requisicao...");
		entityManager = FacesContextUtil.getRequestEntityManager();
		logger.info("instanciando pessoaDAO...");
		pessoaDAO = new PessoaDAO(entityManager);
		logger.info("setando endereço na pessoa...");
		pessoa.setEndereco(new Endereco());
		logger.info("pegando todas as pessoas...");
		pessoas = pessoaDAO.listarTodasPessoas();
		
		tipoLogradouroDAO = new TipoLogradouroDAO(entityManager);
		logger.info("pegando todos os tipos de logradouros...");
		tipoLogradouros = tipoLogradouroDAO.listarTodos();
	}
	
	@PostConstruct
	public void init() {
		estadoDAO = new EstadoDAO(FacesContextUtil.getRequestEntityManager());
		estados = estadoDAO.listarEstados();
	}
	

	public void listarCidades(AjaxBehaviorEvent event) {
			
		CidadeDAO cidadeDAO = new CidadeDAO(FacesContextUtil.getRequestEntityManager());
		estadoDAO = new EstadoDAO(FacesContextUtil.getRequestEntityManager());	
		cidades = cidadeDAO.obterCidadePorEstado(estadoSelecionado);
	}
	
	public void salvar() throws Exception{
		
		EntityManager entityManagerRequisicao = FacesContextUtil.getRequestEntityManager();
		enderecoDAO = new EnderecoDAO(entityManagerRequisicao);
		pessoaDAO = new PessoaDAO(entityManagerRequisicao);
		pessoa.getEndereco().setCidade(cidadeSelecionada);
		pessoa.getEndereco().setTipoLogradouro(tipoLogradouroSelecionado);
		
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
		
		tabView.setActiveIndex(ABA_CADASTRO);
		tabView.setEffect("fade");
		
	
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
			tipoLogradouroSelecionado = pessoa.getEndereco().getTipoLogradouro();
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

	public TabView getTabView() {
		return tabView;
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}

	public List<TipoLogradouro> getTipoLogradouros() {
		return tipoLogradouros;
	}

	public void setTipoLogradouros(List<TipoLogradouro> tipoLogradouros) {
		this.tipoLogradouros = tipoLogradouros;
	}
	
	public TipoLogradouro getTipoLogradouroSelecionado() {
		return tipoLogradouroSelecionado;
	}

	public void setTipoLogradouroSelecionado(
			TipoLogradouro tipoLogradouroSelecionado) {
		this.tipoLogradouroSelecionado = tipoLogradouroSelecionado;
	}
	
}
