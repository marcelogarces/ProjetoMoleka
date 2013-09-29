package br.com.moleka.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.moleka.model.dao.PedidoDAO;
import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dao.ProdutoDAO;
import br.com.moleka.model.dominio.Item;
import br.com.moleka.model.dominio.Pedido;
import br.com.moleka.model.dominio.Pessoa;
import br.com.moleka.model.dominio.Produto;
import br.com.moleka.util.FacesContextUtil;


@ManagedBean
@ViewScoped
public class PedidoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	PessoaDAO pessoaDAO = new PessoaDAO(FacesContextUtil.getRequestEntityManager());
	
	
	private String nome;
	private String telefone;
	private Pessoa cliente = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>(); 
	private Boolean existePessoas;
	private Double valorTotal = 0.0;
	List<Item> itens;
	Pedido pedido;
	
	@PostConstruct
	public void init(){
		
	}
	
	public void pesquisar(){
		PessoaDAO pessoaDAO = new PessoaDAO(FacesContextUtil.getRequestEntityManager());
		pessoas = pessoaDAO.listarPessoaPorNomeETelefoneLike(nome,telefone);
		if(!pessoas.isEmpty()){
		setExistePessoas(true);
		}else{
			FacesContext.getCurrentInstance().addMessage("sdsdsd",new FacesMessage("Nenhum registro encontrado"));
		}
	}
	
	public void iniciarPedido(Pessoa cliente) throws Exception{
		
		this.cliente = cliente;
		pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setData(Calendar.getInstance());
		
		gerarItens(pedido);
		
		FacesContextUtil.setMensagemInfo("Pedido iniciado...");
	}
	
	public void calcularTotal(){
		valorTotal = 0.0;
		for(Item item : itens){
			valorTotal += (item.getPrecoUnitario().doubleValue() * item.getQuantidade());
		}
	}
	
	public void finalizarPedido() throws Exception {
		
		PedidoDAO pedidoDAO = new PedidoDAO(FacesContextUtil.getRequestEntityManager());
		
		List<Item> itens = new ArrayList<Item>();
		for(Item item : this.itens){
			if(item.getQuantidade() > 0){
				itens.add(item);
			}
		}
		
		pedido.setItens(itens);
		pedido.setValorTotal(new BigDecimal(valorTotal));
		pedidoDAO.salvar(pedido);

		this.pedido = null;
		this.cliente = null;
		
		FacesContextUtil.setMensagemInfo("Pedido finalizado com sucesso.");
		
	}
	
	
	private List<Item> gerarItens(Pedido pedido){
		
		ProdutoDAO produtoDAO = new ProdutoDAO(FacesContextUtil.getRequestEntityManager());
		
		itens = new ArrayList<Item>();
		
		Item picoleCocoItem = new Item();
		
		picoleCocoItem.setPedido(pedido);
		Produto p1 = produtoDAO.obterProdutoPorCodigo(1L);
		picoleCocoItem.setProduto(p1);
		picoleCocoItem.setPrecoUnitario(p1.getPreco());
		
		itens.add(picoleCocoItem);
		
		System.out.println(itens.get(0).getQuantidade());
		
		Item picoleMilhoItem = new Item();
		picoleMilhoItem.setPedido(pedido);
		Produto p2 = produtoDAO.obterProdutoPorCodigo(2L);
		picoleMilhoItem.setProduto(p2);
		picoleMilhoItem.setPrecoUnitario(p2.getPreco());

		itens.add(picoleMilhoItem);
		
		return itens;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Boolean getExistePessoas() {
		return existePessoas;
	}

	public void setExistePessoas(Boolean existePessoas) {
		this.existePessoas = existePessoas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}

