package br.com.eventos.adapter;

import java.util.List;

import br.com.eventos.R;
import br.com.eventos.bean.LocalEvento;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LocalEventoAdapter extends ArrayAdapter<LocalEvento> {
	
	private LayoutInflater inflater;

	public LocalEventoAdapter(Activity activity, List<LocalEvento> objects) {
		super(activity, R.layout.item_lista_contato, objects);
		this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_lista_contato, null);
			holder = new ViewHolder();
			holder.txtNome = (TextView) convertView.findViewById(R.id.txtNome);
			holder.txtLotacao = (TextView) convertView.findViewById(R.id.txtLotacao);
			holder.txtSigla = (TextView) convertView.findViewById(R.id.txtSigla);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		LocalEvento localEvento = getItem(position);
		holder.txtNome.setText(localEvento.getNome());
		holder.txtLotacao.setText("Lotação: " + localEvento.getLotacao());
		holder.txtSigla.setText("Sigla: " + localEvento.getSigla());

		return convertView;
	}

	static class ViewHolder {
		public TextView txtNome;
		public TextView txtLotacao;
		public TextView txtSigla;
	}
}
