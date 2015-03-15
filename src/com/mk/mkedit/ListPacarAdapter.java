package com.mk.mkedit;

import java.util.List;

import com.mk.mkedit.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListPacarAdapter extends ArrayAdapter<Pacar> {

	private static final String AndDev = "AndDev";
	Context context;
	int resourceId;

	public ListPacarAdapter(Context context, int resourceId, List<Pacar> objects) {
		super(context, resourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.resourceId = resourceId;
	}

	public class ViewHolder {
		TextView teksnama;
		TextView tekspacar;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Pacar rowItem = getItem(position);
		ViewHolder holder = null;
		LayoutInflater mInflater = ((Activity) context).getLayoutInflater();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_design, null);
			holder = new ViewHolder();

			holder.teksnama = (TextView) convertView
					.findViewById(R.id.textnama);
			holder.tekspacar = (TextView) convertView
					.findViewById(R.id.textpacar);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.teksnama.setText(rowItem.get_namaAnda());
		holder.tekspacar.setText(rowItem.get_pacarAnda());
		if (AndDev.equals(rowItem.get_pacarAnda())) {
			// holder.tekspacar.setBackgroundColor(Color.parseColor("#37abc8"));
		}
		if ("".equals(rowItem.get_pacarAnda())) {
			// holder.tekspacar.setBackgroundColor(Color.RED);
		}
		return convertView;
	}

}
