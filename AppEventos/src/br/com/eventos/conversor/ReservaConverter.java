package br.com.eventos.conversor;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.eventos.bean.Reserva;
import br.com.eventos.util.Util;

public class ReservaConverter {

	public static Reserva converter(JSONObject objetoJson) throws JSONException{
		Reserva reserva = new Reserva();
		
		reserva.setCodigo(objetoJson.getInt("codigo"));
		reserva.setContato(objetoJson.getString("contato"));
		reserva.setDataFinal(Util.converData(objetoJson.getString("dataFinal")));
		reserva.setDataInicial(Util.converData(objetoJson.getString("dataInicial")));
		reserva.setTituloEvento(objetoJson.getString("titulo"));
		return reserva;
	}
	
}
