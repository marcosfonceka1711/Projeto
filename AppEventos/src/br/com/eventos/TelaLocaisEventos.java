package br.com.eventos;

import java.util.List;

import br.com.eventos.adapter.LocalEventoAdapter;
import br.com.eventos.bean.LocalEvento;
import br.com.eventos.tarefas.LocalEventoTarefa;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


public class TelaLocaisEventos extends ListActivity {

	private LocalEventoAdapter adapter;
	private ProgressDialog loadingDialog;
	private AlertDialog errorAlertDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_locais_eventos);
		loadingDialog = new ProgressDialog(TelaLocaisEventos.this);
		loadingDialog.setMessage("Buscando Locais de Eventos...");
		loadingDialog.setCancelable(true);
		createErrorAlertDialog();
		callFetchTask();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.tela_locais_eventos, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		LocalEvento localEvento = adapter.getItem(position);
		Intent i = new Intent(this, DetalheLocalEvento.class);
		Bundle parametros = new Bundle();
		parametros.putInt("codigo", localEvento.getCodigo());
		parametros.putInt("lotacao", localEvento.getLotacao());
		parametros.putString("nome", localEvento.getNome());
		parametros.putString("sigla", localEvento.getSigla());
		i.putExtras(parametros);
		startActivity(i);
		Toast.makeText(getApplicationContext(), "Selecionado local: " + localEvento.getNome(), Toast.LENGTH_SHORT).show();
	}

	private void callFetchTask() {
		loadingDialog.show();
		getListView().setVisibility(View.GONE);
		new LocalEventoTarefa(this).execute();
	}

	public void executeAfterAsyncTask(List<LocalEvento> result, Exception exception) {
		adapter = new LocalEventoAdapter(this, result);
		setListAdapter(adapter);
		getListView().setVisibility(View.VISIBLE);
		loadingDialog.dismiss();

		if (exception != null) {
			try {
				showErrorMessage(exception.getMessage());
			} catch (Exception e) {
			}
		}
	}

	private void createErrorAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Error").setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		errorAlertDialog = builder.create();
	}

	private void showErrorMessage(String msg) {
		errorAlertDialog.setMessage(msg);
		errorAlertDialog.show();
	}

}