package br.com.moleka.managedBean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.moleka.model.dao.ProdutoDAO;
import br.com.moleka.model.dominio.Produto;
import br.com.moleka.util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class PedidoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	ProdutoDAO produtoDAO = new ProdutoDAO(FacesContextUtil.getRequestEntityManager());
	private List<Produto> produtos;
	
	public PedidoBean(){
		
		setProdutos(produtoDAO.listarTodos());
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}

