package br.com.moleka.managedBean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.com.moleka.model.dao.ProdutoDAO;
import br.com.moleka.model.dao.TipoProdutoDAO;
import br.com.moleka.model.dominio.Produto;
import br.com.moleka.model.dominio.TipoProduto;
import br.com.moleka.util.FacesContextUtil;

@ManagedBean
@RequestScoped
public class ProdutoBean {
	
	private ProdutoDAO produtoDAO = new ProdutoDAO(FacesContextUtil.getRequestEntityManager());
	private TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO(FacesContextUtil.getRequestEntityManager());
	private Produto produto = new Produto();
	private List<Produto> produtos;
	private List<TipoProduto>tipoProdutos;
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public ProdutoBean() {
		produtos = produtoDAO.listarTodos();
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void salvar() {
		produtoDAO.salvar(produto);
		FacesContextUtil.setMensagemInfo("Produto gravado com sucesso.");
		this.produto = new Produto();
		this.produtos = produtoDAO.listarTodos();
	}
	
	public void excluir(Produto produto){
		produtoDAO.excluir(produto);
		this.produtos = produtoDAO.listarTodos();
		FacesContextUtil.setMensagemInfo("Produto removido com sucesso.");
	}

	public List<TipoProduto> getTipoProdutos() {
		if(tipoProdutos == null){
			this.tipoProdutos = tipoProdutoDAO.listarTodos();
		}
		return tipoProdutos;
	}

	public void setTipoProdutos(List<TipoProduto> tipoProdutos) {
		this.tipoProdutos = tipoProdutos;
	}
}
