package br.com.eventos.tarefas;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import br.com.eventos.DetalheLocalEvento;
import br.com.eventos.TelaLocaisEventos;
import br.com.eventos.bean.LocalEvento;
import br.com.eventos.bean.Reserva;
import br.com.eventos.config.Configuracao;
import br.com.eventos.conversor.LocalEventoConverter;
import br.com.eventos.conversor.ReservaConverter;
import br.com.eventos.util.HTTPUtil;

public class ReservaTarefa extends AsyncTask<String, String, List<Reserva>> {
	private DetalheLocalEvento activity;
	private Exception exception;
	private int codigoLocalEvento = 0;

	public ReservaTarefa(DetalheLocalEvento activity, int codigoLocalEvento) {
		this.activity = activity;
		this.codigoLocalEvento = codigoLocalEvento;
	}

	@Override
	protected List<Reserva> doInBackground(String... args) {
		try {
			return getReserva();
		} catch (Exception e) {
			e.printStackTrace();
			exception = e;
		}
		return new ArrayList<Reserva>();
	}

	@Override
	protected void onPostExecute(List<Reserva> result) {
		activity.executeAfterAsyncTask(result, exception);
	}

	private List<Reserva> getReserva() throws Exception {
		List<Reserva> listaReserva = new ArrayList<Reserva>();
		String url = Configuracao.URL_DE_BASE + Configuracao.SERVICO_LISTA_EVENTO_LOCAL + codigoLocalEvento;
		String jsonResponse = HTTPUtil.doGet(url);
		JSONArray array = new JSONArray(jsonResponse);
			for (int i = 0; i < array.length(); i++) {
				Reserva c = ReservaConverter.converter(new JSONObject(array.getString(i)));
				listaReserva.add(c);
			}
		
		
		return listaReserva;
	}
}
