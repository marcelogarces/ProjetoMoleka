package br.com.moleka.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class EnderecoBean implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String txtCidade;

	public String getTxtCidade() {
		return txtCidade;
	}

	public void setTxtCidade(String txtCidade) {
		this.txtCidade = txtCidade;
	}

	public List<String> getUfList(){
		
		List<String> ufs = new ArrayList<String>();
			
			ufs.add("RJ");
			ufs.add("MG");
			
		
		return ufs;
	}
	
	public List<String> complete(String query){
		
		List<String> listCidades = new ArrayList<String>();
		
		listCidades.add("Nil�polis");
		listCidades.add("Nova Igua�u");
		listCidades.add("Japeri");
		listCidades.add("Mesquita");
		listCidades.add("Cabo Frio");
		listCidades.add("Niteroi");
		listCidades.add("Rio de Janeiro");
		listCidades.add("Arraial do Cabo");
		listCidades.add("Natividade");
		listCidades.add("Caxias");
		
		return listCidades;
		
	}

}
