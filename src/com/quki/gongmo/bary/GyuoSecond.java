package com.quki.gongmo.bary;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.quki.gongmo.bary.adapter.GyuoAdapter;
import com.quki.gongmo.bary.app.AppController;
import com.quki.gongmo.bary.data.GyuoData;

public class GyuoSecond extends AppCompatActivity {

	// 검색관련
	TextView inputSearch;

	// 파싱관련
	// Log tag
	private static final String TAG = GyuoSecond.class.getSimpleName();

	private ProgressDialog pDialog;

	private List<GyuoData> gyuoList = new ArrayList<GyuoData>();

	private ListView lists;
	private GyuoAdapter adapter;

	String myIds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gyuo_second);
		setTitle("직업훈련기관");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// Intent
		Intent i = getIntent();
		String myIds = i.getStringExtra("myIds");
		String parent = i.getStringExtra("parent");
		String child = i.getStringExtra("child");
		this.myIds = myIds; // 받아온 String으로 전역변수 만들기
		final String locMade = parent + " " + child;

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("조금만 기다려주세요...");
		pDialog.setCancelable(true);

		inputSearch = (TextView) findViewById(R.id.inputSearch);
		inputSearch.setText(locMade);

		lists = (ListView) findViewById(R.id.lists);

		adapter = new GyuoAdapter(this, gyuoList);
		lists.setAdapter(adapter);

		lists.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {

				String title = ((TextView) view.findViewById(R.id.title))
						.getText().toString();
				String adress1 = ((TextView) view.findViewById(R.id.adress1))
						.getText().toString();
				String adress2 = ((TextView) view.findViewById(R.id.adress2))
						.getText().toString();
				String phone = ((TextView) view.findViewById(R.id.phone))
						.getText().toString();
				String releaseWido = ((TextView) view
						.findViewById(R.id.releaseWido)).getText().toString();
				String releaseKyungdo = ((TextView) view
						.findViewById(R.id.releaseKyungdo)).getText()
						.toString();

				Intent i = new Intent(getApplicationContext(), GyuoThird.class);
				i.putExtra("title", title);
				i.putExtra("location", locMade);
				i.putExtra("adress1", adress1);
				i.putExtra("adress2", adress2);
				i.putExtra("phone", phone);
				i.putExtra("releaseWido", releaseWido);
				i.putExtra("releaseKyungdo", releaseKyungdo);
				startActivity(i);

			}
		});

		String url = "http://api.data.go.kr/openapi/34045461-17c2-46e5-bd52-eb45d08c1643?"
				+ "serviceKey=GkimPv8gVnacwA4yeuTnolLjvI0VRVQqmXN56dAuSFEB54AIiANqZDRa9aWe0qX8GSK2MJYdz%2FtkdwZP2WpXVw%3D%3D&type=json"
				+ myIds;

		showpDialog();
		// volley request (Oncreate 내부)

		JsonArrayRequest myReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						// Parsing json

						for (int i = 0; i < response.length(); i++) {
							try {
								JSONObject obj = response.getJSONObject(i);
								GyuoData gyuoData = new GyuoData();
								gyuoData.setPhone(obj.getString("연락처"));
								gyuoData.setTitle(obj.getString("훈련기관명"));
								gyuoData.setLocation(obj.getString("관할지사"));
								gyuoData.setAdressOne(obj.getString("소재지지번주소"));
								gyuoData.setAdressTwo(obj.getString("소재지도로명주소"));
								gyuoData.setPhone(obj.getString("연락처"));
								gyuoData.setWido(obj.getDouble("위도"));
								gyuoData.setKyungdo(obj.getDouble("경도"));
								gyuoList.add(gyuoData);

							} catch (JSONException e) {
								e.printStackTrace();
							}
							
						}
						hidePDialog();
						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		AppController.getInstance().addToRequestQueue(myReq);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void showpDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
		}
	}

	// 메뉴만들기
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater menuIn = getMenuInflater();
		menuIn.inflate(R.menu.gyuo_second, menu);
		return true;

	}

	// 메뉴 event
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			finish();
			return true;

		}

		return false;
	}

}
