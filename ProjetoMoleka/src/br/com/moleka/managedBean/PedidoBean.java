package br.com.moleka.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;
import br.com.moleka.model.dao.PedidoDAO;
import br.com.moleka.model.dao.PessoaDAO;
import br.com.moleka.model.dao.ProdutoDAO;
import br.com.moleka.model.dominio.Item;
import br.com.moleka.model.dominio.Pedido;
import br.com.moleka.model.dominio.Pessoa;
import br.com.moleka.model.dominio.Produto;
import br.com.moleka.util.FacesContextUtil;
import br.com.moleka.util.RelatorioUtil;

@ManagedBean
@ViewScoped
public class PedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(PedidoBean.class);

	PessoaDAO pessoaDAO = new PessoaDAO(
			FacesContextUtil.getRequestEntityManager());

	private String nome;
	private String telefone;
	private Pessoa cliente = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private Boolean existePessoas;
	private Double valorTotal = 0.0;
	List<Item> itens;
	Pedido pedido;
	private Boolean pedidoComFrete;

	@PostConstruct
	public void init() {

	}

	public void pesquisar() {
		PessoaDAO pessoaDAO = new PessoaDAO(
				FacesContextUtil.getRequestEntityManager());
		pessoas = pessoaDAO.listarPessoaPorNomeETelefoneLike(nome, telefone);
		if (!pessoas.isEmpty()) {
			setExistePessoas(true);
		} else {
			FacesContext.getCurrentInstance().addMessage("sdsdsd",
					new FacesMessage("Nenhum registro encontrado"));
		}
	}

	public void iniciarPedido(Pessoa cliente) throws Exception {

		this.cliente = cliente;
		pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setData(Calendar.getInstance());

		gerarItens(pedido);

		FacesContextUtil.setMensagemInfo("Pedido iniciado...");
	}

	public void calcularTotal() {
		valorTotal = 0.0;
		for (Item item : itens) {
			valorTotal += (item.getPrecoUnitario().doubleValue() * item
					.getQuantidade());
		}

	}

	public void calcularFrete() {

		if (pedidoComFrete) {

			valorTotal += 3.00;
		} else {

			valorTotal += -3.00;
		}
	}

	public void finalizarPedido() throws Exception {

		PedidoDAO pedidoDAO = new PedidoDAO(FacesContextUtil.getRequestEntityManager());		
		Boolean balcao = false;
		List<Item> itens = new ArrayList<Item>();
		for (Item item : this.itens) {
			if (item.getQuantidade() > 0) {
				itens.add(item);
			}
		}	
		if(pedidoComFrete){
			ProdutoDAO produtoDAO = new ProdutoDAO(FacesContextUtil.getRequestEntityManager());
			Item item = new Item();
			Produto frete = produtoDAO.obterFrete();
			item.setPedido(pedido);
			item.setPrecoUnitario(frete.getPreco());
			item.setProduto(frete);
			item.setQuantidade(1);
			itens.add(item);
		}
		pedido.setItens(itens);
		pedido.setValorTotal(new BigDecimal(valorTotal));
		pedidoDAO.salvar(pedido);
		
		if(cliente.getNome().equalsIgnoreCase("Balcão")){
			balcao = true;
		}
		this.pedido = null;
		this.cliente = null;
		FacesContextUtil.setMensagemInfo("Pedido finalizado com sucesso.");
		gerarPdfPedido(itens,balcao);
	}

	private List<Item> gerarItens(Pedido pedido) {

		ProdutoDAO produtoDAO = new ProdutoDAO(
				FacesContextUtil.getRequestEntityManager());
		List<Produto> todosProdutos = produtoDAO.findAll();

		itens = new ArrayList<Item>();

		for (Produto produto : todosProdutos) {

			Item item = new Item();
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());

			itens.add(item);
		}

		return itens;
	}

	private void gerarPdfPedido(List<Item> itensPedido, Boolean balcao) throws IOException,
			JRException {

		String localRelatorio;	
		if(balcao){
			localRelatorio = "/relatorios/pedido.jasper";
		}else{
			localRelatorio = "/relatorios/pedido_cliente.jasper";
		}		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("p1", "parametro1");
		RelatorioUtil.gerarRelatorio(localRelatorio, parametros, itensPedido);

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

	public Boolean getPedidoComFrete() {
		return pedidoComFrete;
	}

	public void setPedidoComFrete(Boolean pedidoComFrete) {
		this.pedidoComFrete = pedidoComFrete;
	}

}
