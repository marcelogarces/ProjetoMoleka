package br.com.moleka.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dao.ProdutoDAO;
import br.com.moleka.model.dominio.Item;
import br.com.moleka.model.dominio.Pedido;
import br.com.moleka.model.dominio.Produto;
import br.com.moleka.util.FacesContextUtil;


@ManagedBean
@ViewScoped
public class PedidoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	ProdutoDAO produtoDAO = new ProdutoDAO(FacesContextUtil.getRequestEntityManager());
	PessoaDAO pessoaDAO = new PessoaDAO(FacesContextUtil.getRequestEntityManager());
	
	
	public void abrirPedidoBalcao(){	
		Pedido pedidoBalcao = new Pedido();
		pedidoBalcao.setCliente(pessoaDAO.obterClienteBalcao());
		pedidoBalcao.setData(new GregorianCalendar());
		
	}
	
	private List<Item> gerarItens(Pedido pedido){
		
		List<Item> itens = new ArrayList<Item>();
		
		Item picoleCoco = new Item();
		picoleCoco.setPedido(pedido);
		
		Produto p1 = produtoDAO.obterProdutoPorCodigo(1L);
		picoleCoco.setProduto(p1);
		picoleCoco.setPrecoUnitario(p1.getPreco());
		
		
		
		Item picoleMilho = new Item();
		picoleMilho.setPedido(pedido);
		
		Produto p2 = produtoDAO.obterProdutoPorCodigo(2L);
		picoleMilho.setProduto(p2);
		picoleMilho.setPrecoUnitario(p2.getPreco());
		
		
		
		itens.add(picoleCoco);
		itens.add(picoleMilho);
		
		return itens;
	}
	

}

