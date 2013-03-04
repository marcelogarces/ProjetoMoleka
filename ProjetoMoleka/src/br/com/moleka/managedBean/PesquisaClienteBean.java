package br.com.moleka.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
			System.out.println("teledfone....." + telefonePesq);
			PessoaDAO pessoaDAO = new PessoaDAO(FacesContextUtil.getRequestEntityManager());
			System.out.println("acao pesquisar.....");
			pessoas = pessoaDAO.listarPessoaPorNomeETelefoneLike(nomePesq,telefonePesq);
			if(!pessoas.isEmpty()){
			existePessoas = true;
			}else{
				System.out.println("nao existe registro");
				FacesContext.getCurrentInstance().addMessage("sdsdsd",new FacesMessage("Nenhum registro encontrado"));
			}
		}
		public String selecionar(Pessoa pessoa){
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
