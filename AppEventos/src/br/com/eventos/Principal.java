package br.com.eventos;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Principal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }
    
    public void cliqueBotaoTelaPrincipal(View v){
    	switch(v.getId()){
    	case R.id.btnEventos:
    		acaoAbrirTelaLocaisEvento();
    		break;
    	case R.id.btnMenuSobre:
    		acaoBotaoSobre();
    		break;
    		default:
    			break;
    	}
    }


    private void acaoBotaoSobre() {
		// TODO Botão Sobre
    	Intent intencao = new Intent(Principal.this, TelaSobre.class);
    	startActivity(intencao);
		
	}

	private void acaoAbrirTelaLocaisEvento() {
		// TODO Botão Abrir tela locais eventos
    	Intent intencao = new Intent(Principal.this, TelaLocaisEventos.class);
    	startActivity(intencao);		
	}

	private void acaoBotaoSair() {
		// TODO Botão sair tela principal
    	finish();    	
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }
    
}
