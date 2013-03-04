package br.com.moleka.model.dominio;

public enum TipoProdutoEnum {

	PICOLE(1,"Picolé"),
	SORVETE(2,"Sorvete"),
	SKIMO(3,"Skimo");
	
	private final int id;
	private final String descricao;
	
	TipoProdutoEnum(int id,String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public int getId(){
		return id;
	}
	
}
