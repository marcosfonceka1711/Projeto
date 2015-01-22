package br.com.eventos.conversor;

import org.json.*;

import br.com.eventos.bean.LocalEvento;

public class LocalEventoConverter {

	public static LocalEvento converter(JSONObject objetoJson) throws JSONException{
		LocalEvento localEvento = new LocalEvento();
		
		localEvento.setCodigo(objetoJson.getInt("codigo"));
		localEvento.setLotacao(objetoJson.getInt("lotacao"));
		localEvento.setNome(objetoJson.getString("nome"));
		localEvento.setSigla(objetoJson.getString("sigla"));
		
		return localEvento;
	}
	
}
