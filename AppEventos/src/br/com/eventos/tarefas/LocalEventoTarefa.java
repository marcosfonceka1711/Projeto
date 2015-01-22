package br.com.eventos.tarefas;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.eventos.TelaLocaisEventos;
import br.com.eventos.bean.LocalEvento;
import br.com.eventos.config.Configuracao;
import br.com.eventos.conversor.LocalEventoConverter;
import br.com.eventos.util.HTTPUtil;
import android.os.AsyncTask;


public class LocalEventoTarefa extends AsyncTask<String, String, List<LocalEvento>> {

	private TelaLocaisEventos activity;
	private Exception exception;

	public LocalEventoTarefa(TelaLocaisEventos activity) {
		this.activity = activity;
	}

	@Override
	protected List<LocalEvento> doInBackground(String... args) {
		try {
			return getLocalEvento();
		} catch (Exception e) {
			e.printStackTrace();
			exception = e;
		}
		return new ArrayList<LocalEvento>();
	}

	@Override
	protected void onPostExecute(List<LocalEvento> result) {
		activity.executeAfterAsyncTask(result, exception);
	}

	private List<LocalEvento> getLocalEvento() throws Exception {
		List<LocalEvento> listaLocalEvento = new ArrayList<LocalEvento>();
		String url = Configuracao.URL_DE_BASE + Configuracao.SERVICO_LISTA_LOCAL_EVENTO;
		String jsonResponse = HTTPUtil.doGet(url);
		JSONArray array = new JSONArray(jsonResponse);
			for (int i = 0; i < array.length(); i++) {
				LocalEvento c = LocalEventoConverter.converter(new JSONObject(array.getString(i)));
				listaLocalEvento.add(c);
			}
		
		
		return listaLocalEvento;
	}
}