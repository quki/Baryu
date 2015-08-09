package com.quki.gongmo.bary.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quki.gongmo.bary.R;
import com.quki.gongmo.bary.data.GyuoData;

public class GyuoAdapter extends BaseAdapter{
	
	private Activity activity;
	private LayoutInflater inflater;
	private List<GyuoData> gyuoData; 

	public GyuoAdapter(Activity activity, List<GyuoData> gyuoData) {
		this.activity = activity;
		this.gyuoData = gyuoData;
	}
	@Override
	public int getCount() {
		return gyuoData.size();
	}

	@Override
	public Object getItem(int local) {
		return gyuoData.get(local);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null)
			
			
			convertView = inflater.inflate(R.layout.list_item_gyuo_second, null);

		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView phone = (TextView) convertView.findViewById(R.id.phone);
		TextView location = (TextView) convertView.findViewById(R.id.location);
		TextView adress1 = (TextView) convertView.findViewById(R.id.adress1);
		TextView adress2 = (TextView) convertView.findViewById(R.id.adress2);
		TextView releaseWido = (TextView) convertView
				.findViewById(R.id.releaseWido);
		TextView releaseKyungdo = (TextView) convertView
				.findViewById(R.id.releaseKyungdo);
		// getting data for the row
		GyuoData m = gyuoData.get(position);
		// 훈련기관명
		title.setText(m.getTitle());
		// phone
		phone.setText(m.getPhone());
		// 관할지사
		location.setText(m.getLocation());
		// 번지주소
		adress1.setText(m.getAdressOne());
		// 도로명주소
		adress2.setText(m.getAdressTwo());
		// 위도
		releaseWido.setText(String.valueOf(m.getWido()));
		// 경도
		releaseKyungdo.setText(String.valueOf(m.getKyungdo()));
		return convertView;
	}
}
