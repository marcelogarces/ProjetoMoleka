package br.com.moleka.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dominio.Pessoa;
import br.com.moleka.util.FacesContextUtil;


@ManagedBean
@ViewScoped
public class PesquisaClienteBean implements Serializable {
		

		private static final long serialVersionUID = 1L;
		private String nomePesq;
		private String telefonePesq;
		private List<Pessoa> pessoas = new ArrayList<Pessoa>(); 
		private Boolean existePessoas;


		public String getNomePesq() {
			return nomePesq;
		}

		public Boolean getExistePessoas() {
			return existePessoas;
		}

		public void setNomePesq(String nomePesq) {
			this.nomePesq = nomePesq;
		}
		
		public void pesquisar(){
			PessoaDAO pessoaDAO = new PessoaDAO(FacesContextUtil.getRequestEntityManager());
			pessoas = pessoaDAO.listarPessoaPorNomeETelefoneLike(nomePesq,telefonePesq);
			if(!pessoas.isEmpty()){
			existePessoas = true;
			}else{
				FacesContext.getCurrentInstance().addMessage("sdsdsd",new FacesMessage("Nenhum registro encontrado"));
			}
		}
		public String selecionar(Pessoa pessoa) throws IOException{
			System.out.println("pppppppppppppppppppp");
			FacesContext.getCurrentInstance().getExternalContext().redirect("menu.jsf");  
			return "menu.xhtml";
		}

		public List<Pessoa> getPessoas() {
			return pessoas;
		}

		public void setPessoas(List<Pessoa> pessoas) {
			this.pessoas = pessoas;
		}

		public String getTelefonePesq() {
			return telefonePesq;
		}

		public void setTelefonePesq(String telefonePesq) {
			this.telefonePesq = telefonePesq;
		}
}
