package br.com.eventos;

import java.util.List;

import br.com.eventos.adapter.LocalEventoAdapter;
import br.com.eventos.adapter.ReservaAdapter;
import br.com.eventos.bean.LocalEvento;
import br.com.eventos.bean.Reserva;
import br.com.eventos.tarefas.LocalEventoTarefa;
import br.com.eventos.tarefas.ReservaTarefa;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DetalheLocalEvento extends Activity {

	private ReservaAdapter adapter;
	private ProgressDialog loadingDialog;
	private AlertDialog errorAlertDialog;
	private TextView teatro;
	private TextView lotacao;
	private ListView eventos;
	private int codigoLocalEvento;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe_local_evento);
		
		//Recuperação dos parâmetros
		Intent intencao = getIntent();
		Bundle parametros = intencao.getExtras();		
		
		if(parametros != null){
			
			teatro = (TextView)findViewById(R.id.teatro);
			lotacao = (TextView)findViewById(R.id.lotacao);
			eventos = (ListView)findViewById(R.id.eventos);
			
			codigoLocalEvento = parametros.getInt("codigo");
			teatro.setText(parametros.getString("nome"));
			lotacao.setText("Lotação: " + parametros.getInt("lotacao"));
			
			loadingDialog = new ProgressDialog(DetalheLocalEvento.this);
			loadingDialog.setMessage("Buscando Eventos...");
			loadingDialog.setCancelable(true);
			createErrorAlertDialog();
			callFetchTask();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalhe_local_evento, menu);
		return true;
	}
	
	private void callFetchTask() {
		loadingDialog.show();
		eventos.setVisibility(View.GONE);
		new ReservaTarefa(this, codigoLocalEvento).execute();
	}
	
	public void botaoVoltar(View v){
		Intent intencao = new Intent(DetalheLocalEvento.this, Principal.class);
		startActivity(intencao);
	}
	
	public void executeAfterAsyncTask(List<Reserva> result, Exception exception) {
		adapter = new ReservaAdapter(this, result);
		eventos.setAdapter(adapter);
		eventos.setVisibility(View.VISIBLE);
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
