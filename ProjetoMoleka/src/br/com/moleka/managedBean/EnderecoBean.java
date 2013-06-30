package br.com.moleka.managedBean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import br.com.moleka.model.dao.CidadeDAO;
import br.com.moleka.model.dao.EstadoDAO;
import br.com.moleka.model.dominio.Cidade;
import br.com.moleka.model.dominio.Estado;
import br.com.moleka.util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class EnderecoBean implements Serializable  {


	private static final long serialVersionUID = 1L;
	private EstadoDAO estadoDAO = new EstadoDAO(FacesContextUtil.getRequestEntityManager());
	private List<Estado> estados;
	

	private List<Cidade>cidades = new ArrayList<Cidade>();
	private Estado estadoSelecionado;
	private List<String>nomesCidade;
	
	
	@PostConstruct
	public void init() {
	estados = estadoDAO.listarEstados();
	}
	
	public void listaCidades(AjaxBehaviorEvent event) {
		CidadeDAO cidadeDAO = new CidadeDAO(FacesContextUtil.getRequestEntityManager());
		cidades = cidadeDAO.listarCidades();
	}


	public List<String> complete(String query){
		
		return nomesCidade;
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


	public List<Estado> getEstados() {
		return estados;
	}


	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
