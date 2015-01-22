package br.com.eventos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	private static SimpleDateFormat formatadorDeData = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Date converData(String data){
		try {
			return formatadorDeData.parse(data);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	public static String converterData(Date data){
		return formatadorDeData.format(data);
	}
	
	
}
