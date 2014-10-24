package com.interest.fajia.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fajia.R;
import com.interest.fajia.model.Client;

public class ClientAdapter extends ArrayAdapter<Client>{

	private LayoutInflater inflater;
	public ClientAdapter(Context context, List<Client> objects) {
		super(context, 0, objects);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Client client = getItem(position);
		ViewHolder holder = new ViewHolder();
		convertView = inflater.inflate(R.layout.client_item_layout,
				null);
		holder.head = (ImageView) convertView.findViewById(R.id.client_head);
		holder.name = (TextView) convertView.findViewById(R.id.client_name);
		holder.nearest_text_key = (TextView) convertView.findViewById(R.id.nearest_time);
		holder.name.setText(client.getName());
		holder.nearest_text_key.setText(client.getNearlest_time());
		return convertView;
	}
	@Override
	public Client getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}


	private static class ViewHolder {

		ImageView head;
		TextView name;
		TextView nearest_text;
		TextView nearest_text_key;

	}
}
