package br.com.moleka.managedBean;


import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EntityManager;
import br.com.moleka.model.dao.CidadeDAO;
import br.com.moleka.model.dao.EstadoDAO;
import br.com.moleka.model.dominio.Cidade;
import br.com.moleka.model.dominio.Estado;
import br.com.moleka.util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class EnderecoBean  {

	private List<Estado> estados;
	private EstadoDAO estadoDAO = new EstadoDAO(FacesContextUtil.getRequestEntityManager());
	
	private List<Cidade>cidades = new ArrayList<Cidade>();
	private Estado estadoSelecionado;
	private List<String>nomesCidade;
	
	
	public EnderecoBean(){
		
		estados = estadoDAO.listarEstados();
	}
	
	public void teste(AjaxBehaviorEvent event){	
		
		//System.out.println("estado selecionado....." +estadoSelecionado);
		CidadeDAO cidadeDAO = new CidadeDAO(FacesContextUtil.getRequestEntityManager());
		cidades = cidadeDAO.listarCidades();
		List<String>testes = new ArrayList<String>();
		
		
		for(Cidade cidade : cidades){
			System.out.println("cidade..." + cidade.getNome());
			testes.add(cidade.getNome());
		}
		
		nomesCidade = testes;
		
		for(String string : nomesCidade){
			System.out.println("??????  " + string);
		}
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
