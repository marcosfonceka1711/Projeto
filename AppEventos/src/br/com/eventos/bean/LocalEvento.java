package br.com.eventos.bean;

public class LocalEvento {

	private String nome;
	private int lotacao;
	private String sigla;
	private int codigo;
	
	public LocalEvento(){}
	
	public String getNome(){return nome;}
	public int getLotacao(){return lotacao;}
	public String getSigla(){return sigla;}
	public int getCodigo(){return codigo;}
	
	public void setNome(String nome){this.nome = nome;}
	public void setLotacao(int lotacao){this.lotacao = lotacao;}
	public void setSigla(String sigla){this.sigla = sigla;}
	public void setCodigo(int codigo){this.codigo = codigo;}
	
}
