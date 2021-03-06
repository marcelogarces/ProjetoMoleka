package br.com.moleka.managedBean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.moleka.model.dao.ProdutoDAO;
import br.com.moleka.model.dao.TipoProdutoDAO;
import br.com.moleka.model.dominio.Produto;
import br.com.moleka.model.dominio.TipoProduto;
import br.com.moleka.util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProdutoDAO produtoDAO = new ProdutoDAO(FacesContextUtil.getRequestEntityManager());
	private Produto produto = new Produto();
	private List<Produto> produtos;
	private List<TipoProduto> tipoProdutos;
	

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
		produtoDAO = new ProdutoDAO(FacesContextUtil.getRequestEntityManager());
		if(produto.getId() == null){
			produtoDAO.salvar(produto);
			FacesContextUtil.setMensagemInfo("Produto gravado com sucesso.");
		}else{
			produtoDAO.atualizar(produto);
			FacesContextUtil.setMensagemInfo("Produto atualizado com sucesso.");
		}	
		this.produto = new Produto();
		this.produtos = produtoDAO.listarTodos();
	}

	public void excluir() {
		produtoDAO = new ProdutoDAO(FacesContextUtil.getRequestEntityManager());
		Produto produto = produtoDAO
				.obterProdutoPorCodigo(this.produto.getId());
		if (produto != null) {
			produtoDAO.excluir(produto);
			FacesContextUtil.setMensagemInfo("Produto removido com sucesso.");
		} else {
			FacesContextUtil
					.setMensagemInfo("Produto excluido por outro usuário.");
		}

		this.produtos = produtoDAO.listarTodos();
		this.produto = new Produto();
	}

	public void cancelar() {
		produto = new Produto();
	}

	public List<TipoProduto> getTipoProdutos() {
		TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO(
				FacesContextUtil.getRequestEntityManager());
		if (tipoProdutos == null) {
			this.tipoProdutos = tipoProdutoDAO.listarTodos();
		}
		return tipoProdutos;
	}

	public void setTipoProdutos(List<TipoProduto> tipoProdutos) {
		this.tipoProdutos = tipoProdutos;
	}

}
