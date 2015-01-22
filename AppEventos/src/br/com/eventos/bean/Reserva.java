package br.com.eventos.bean;

import java.util.Date;

public class Reserva {

	private int codigo;
	private String tituloEvento;
	private String contato;
	private Date dataInicial;
	private Date dataFinal;
	
	public Reserva(){}
	
	public int getCodigo(){return codigo;}
	public String getTituloEvento(){return tituloEvento;}
	public String getContato(){return contato;}
	public Date getDataInicial(){return dataInicial;}
	public Date getDataFinal(){return dataFinal;}
	
	public void setCodigo(int codigo){this.codigo = codigo;}
	public void setTituloEvento(String tituloEvento){this.tituloEvento = tituloEvento;}
	public void setContato(String contato){this.contato = contato;}
	public void setDataInicial(Date dataInicial){this.dataInicial = dataInicial;}
	public void setDataFinal(Date dataFinal){this.dataFinal = dataFinal;}
	
}
