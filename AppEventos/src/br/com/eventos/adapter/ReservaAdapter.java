package br.com.eventos.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.eventos.R;
import br.com.eventos.bean.Reserva;
import br.com.eventos.util.Util;

public class ReservaAdapter extends ArrayAdapter<Reserva>  {

	private LayoutInflater inflater;
	
	public ReservaAdapter(Activity activity, List<Reserva> objects) {
		super(activity, R.layout.item_lista_reserva, objects);
		this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_lista_reserva, null);
			holder = new ViewHolder();
			holder.txtTituloEvento = (TextView) convertView.findViewById(R.id.txtTituloEvento);
			holder.txtContato = (TextView) convertView.findViewById(R.id.txtContato);
			holder.txtDataInicial = (TextView) convertView.findViewById(R.id.txtDataInicial);
			holder.txtDataFinal = (TextView) convertView.findViewById(R.id.txtDataFinal);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Reserva reserva = getItem(position);
		holder.txtTituloEvento.setText(reserva.getTituloEvento());
		holder.txtContato.setText("Contato: " + reserva.getContato());
		holder.txtDataInicial.setText("Início: " + Util.converterData(reserva.getDataInicial()));
		holder.txtDataFinal.setText("Fim: " + Util.converterData(reserva.getDataFinal()));

		return convertView;
	}
	
	static class ViewHolder {
		public TextView txtTituloEvento;
		public TextView txtContato;
		public TextView txtDataInicial;
		public TextView txtDataFinal;
	}
}
