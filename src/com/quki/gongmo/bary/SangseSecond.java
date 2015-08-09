package com.quki.gongmo.bary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.quki.gongmo.bary.app.AppController;

public class SangseSecond extends AppCompatActivity {

	String myId, myIdb; // ��������
	String idSearch;
	private static String TAG = SangseSecond.class.getSimpleName();
	private ProgressDialog pDialog;
	
	
	// xml ��������
	NetworkImageView netImag, netImag1, netImag2, netImag3;
	TextView txtView1, txtView2, MainTitle, SubTitle, txtViewImagTitle,
			txtViewImagTitle1, txtViewImagTitle2, txtViewImagTitle3;
	ImageLoader imageLoader, iamgeLoaderdb;
	CheckBox checkBox1;

	// DB
	MyDBHelper myDBHelper = new MyDBHelper(this);
	SQLiteDatabase sqlDB;

	// temporary string to show the parsed response
	String jsonResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sangse_second);
		setTitle("�����ħ��");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);		
		// dialog
		pDialog = new ProgressDialog(this);
		pDialog.setMessage("���ݸ� ��ٷ��ּ���...");
		pDialog.setCancelable(false);

		// xml��üȭ
		MainTitle = (TextView) findViewById(R.id.MainTitle);
		SubTitle = (TextView) findViewById(R.id.SubTitle);
		txtView1 = (TextView) findViewById(R.id.txtView1);
		txtView2 = (TextView) findViewById(R.id.txtView2);
		txtViewImagTitle = (TextView) findViewById(R.id.txtViewImagTitle);
		txtViewImagTitle1 = (TextView) findViewById(R.id.txtViewImagTitle1);
		txtViewImagTitle2 = (TextView) findViewById(R.id.txtViewImagTitle2);
		txtViewImagTitle3 = (TextView) findViewById(R.id.txtViewImagTitle3);
		netImag = (NetworkImageView) findViewById(R.id.netImag);
		netImag1 = (NetworkImageView) findViewById(R.id.netImag1);
		netImag2 = (NetworkImageView) findViewById(R.id.netImag2);
		netImag3 = (NetworkImageView) findViewById(R.id.netImag3);
		checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

		// Intent
		Intent i = getIntent();
		final String parent = i.getStringExtra("parent");
		final String child = i.getStringExtra("child");
		final String myId = i.getStringExtra("myId");
		this.myId = myId; // �޾ƿ� String���� �������� �����

		// Intent DB
		Intent b = getIntent();
		final String myIdb = b.getStringExtra("myIdb");
		final String parentdb = b.getStringExtra("parentdb");
		final String childdb = b.getStringExtra("childdb");
		this.myIdb = myIdb;

		// �˻� 
		sqlDB = myDBHelper.getReadableDatabase();
		Cursor cursor;
		cursor = sqlDB.rawQuery("SELECT*from bookmark_SANGI where myid = '"
				+ myId + "';", null);

		while (cursor.moveToNext()) {
			idSearch = cursor.getString(0);
		}

		try {

			if (idSearch.equals(myId)) {

				checkBox1.setChecked(true);
			}

		} catch (Exception e) {
		}

		cursor.close();
		sqlDB.close();

		try {

			if (myIdb == null) {
				checkBox1.setVisibility(android.view.View.VISIBLE);
				// event
				MainTitle.setText(parent);
				SubTitle.setText(child);
				makeJsonArrayRequest(); 
				imageLoader = AppController.getInstance().getImageLoader();
			} else {

				MainTitle.setText(parentdb);
				SubTitle.setText(childdb);
				iamgeLoaderdb = AppController.getInstance().getImageLoader();
				makeJsonArrayRequestdb();
			}

		} catch (Exception e) {
		}

		// ���ã��
		checkBox1
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (checkBox1.isChecked() == true) {
							Toast.makeText(getApplicationContext(),
									"���ã�⿡ ��ϵǾ����ϴ�", Toast.LENGTH_SHORT).show();
							sqlDB = myDBHelper.getWritableDatabase();
							sqlDB.execSQL("insert into bookmark_SANGI values('"
									+ myId + "','" + parent + "','" + child
									+ "');");
							sqlDB.close();

						} else {
							Toast.makeText(getApplicationContext(),
									"���ã�⿡�� �����Ǿ����ϴ�", Toast.LENGTH_SHORT)
									.show();
							sqlDB = myDBHelper.getWritableDatabase();
							sqlDB.execSQL("DELETE FROM bookmark_SANGI where myid='"
									+ myId + "';");
							sqlDB.close();
						}
					}
				});

	}

	private void makeJsonArrayRequest() {
		
		String requestParam1 = "http://www.ibtk.kr/seniorGuideDetail/069c9c33cff52d5c60452a177f4f9f63?model_query={%20%22detailSeq%22:%22";
		String requestParam2 = "%22}&model_query_pageable={enable:false}";
		String url = requestParam1 + myId + requestParam2;

		showpDialog();

		JsonArrayRequest req = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());

						try {
							// Parsing json array response
							// loop through each json object
							jsonResponse = "";
							for (int i = 0; i < response.length(); i++) {

								JSONObject person = (JSONObject) response
										.get(i);
								String imgUrl = person.getString("imgUrl");
								String imgTitle = person.getString("imgTitle");
								String title = person.getString("title");
								String contents = person.getString("contents");

								if (myId.equals("07_01")) {
									txtView1.setText(title);
									txtView2.setText(contents);
									netImag.setImageUrl(
											"http://175.124.121.213/seniorguide/7_1.jpg",
											imageLoader);
									txtViewImagTitle.setText("<�ɹ��� ��>\n");
									netImag1.setImageUrl(
											"http://175.124.121.213/seniorguide/7_2.jpg",
											imageLoader);
									txtViewImagTitle1
											.setText("<�ɹ��� Ȱ���� �ǻ������ ��>");
								} else if (myId.equals("05_03")) {
									txtView1.setText(title);
									txtView2.setText(contents);
									netImag.setImageUrl(
											"http://175.124.121.213/seniorguide/5_2.jpg",
											imageLoader);
									txtViewImagTitle
											.setText("<�۲� ��Ÿ�Ͽ� ���� �ǵ����� ����>\n");
									netImag1.setImageUrl(
											"http://175.124.121.213/seniorguide/5_3.jpg",
											imageLoader);
									txtViewImagTitle1
											.setText("<��� ��� ������ ���� �۲� ����>");

								} else if (myId.equals("11_05")) {
									txtView1.setText(title);
									txtView2.setText(contents);
									netImag.setImageUrl(
											"http://175.124.121.213/seniorguide/11_1.jpg",
											imageLoader);
									txtViewImagTitle
											.setText("<���� ���� �� �� �ִ� �ö�ƽ ����� ��>\n");
									netImag1.setImageUrl(
											"http://175.124.121.213/seniorguide/11_2.jpg",
											imageLoader);
									txtViewImagTitle1
											.setText("<���� �� �ִ� ����ΰ� �޸� ����� ��>\n");
									netImag2.setImageUrl(
											"http://175.124.121.213/seniorguide/11_3.jpg",
											imageLoader);
									txtViewImagTitle2
											.setText("<���� ���� �ִ� ���� ���� ��� �Ѳ��� ��>\n");
									netImag3.setImageUrl(
											"http://175.124.121.213/seniorguide/11_4.jpg",
											imageLoader);
									txtViewImagTitle3
											.setText("<���� �� �� �ֵ��� ����� �� �Ѱ��� ��>");

								} else {

									netImag.setImageUrl(imgUrl, imageLoader);
									txtView1.setText(title);
									txtView2.setText(contents);
									txtViewImagTitle.setText(imgTitle);
								}
							}

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(getApplicationContext(),
									"���ͳ� ������ Ȯ���ϼ���", Toast.LENGTH_LONG).show();
						}
						hidepDialog();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						Toast.makeText(getApplicationContext(),
								"���ͳ� ������ Ȯ���ϼ���", Toast.LENGTH_SHORT).show();
						hidepDialog();
					}
				});
		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);

	}

	private void makeJsonArrayRequestdb() {

		String urlJsonArrydb = "http://www.ibtk.kr/seniorGuideDetail/069c9c33cff52d5c60452a177f4f9f63?model_query={%20%22detailSeq%22:%22"
				+ myIdb + "%22}&model_query_pageable={enable:false}";

		showpDialog();

		JsonArrayRequest reqdb = new JsonArrayRequest(urlJsonArrydb,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray responsedb) {
						Log.d(TAG, responsedb.toString());

						try {
							jsonResponse = "";
							for (int i = 0; i < responsedb.length(); i++) {

								JSONObject person = (JSONObject) responsedb
										.get(i);

								String imgUrl = person.getString("imgUrl");
								String imgTitle = person.getString("imgTitle");
								String title = person.getString("title");
								String contents = person.getString("contents");

								if (myIdb.equals("07_01")) {
									txtView1.setText(title);
									txtView2.setText(contents);
									netImag.setImageUrl(
											"http://175.124.121.213/seniorguide/7_1.jpg",
											iamgeLoaderdb);
									txtViewImagTitle.setText("<�ɹ��� ��>\n");
									netImag1.setImageUrl(
											"http://175.124.121.213/seniorguide/7_2.jpg",
											iamgeLoaderdb);
									txtViewImagTitle1
											.setText("<�ɹ��� Ȱ���� �ǻ������ ��>");
								} else if (myIdb.equals("05_03")) {
									txtView1.setText(title);
									txtView2.setText(contents);
									netImag.setImageUrl(
											"http://175.124.121.213/seniorguide/5_2.jpg",
											iamgeLoaderdb);
									txtViewImagTitle
											.setText("<�۲� ��Ÿ�Ͽ� ���� �ǵ����� ����>\n");
									netImag1.setImageUrl(
											"http://175.124.121.213/seniorguide/5_3.jpg",
											iamgeLoaderdb);
									txtViewImagTitle1
											.setText("<��� ��� ������ ���� �۲� ����>");

								} else if (myIdb.equals("11_05")) {
									txtView1.setText(title);
									txtView2.setText(contents);
									netImag.setImageUrl(
											"http://175.124.121.213/seniorguide/11_1.jpg",
											iamgeLoaderdb);
									txtViewImagTitle
											.setText("<���� ���� �� �� �ִ� �ö�ƽ ����� ��>\n");
									netImag1.setImageUrl(
											"http://175.124.121.213/seniorguide/11_2.jpg",
											iamgeLoaderdb);
									txtViewImagTitle1
											.setText("<���� �� �ִ� ����ΰ� �޸� ����� ��>\n");
									netImag2.setImageUrl(
											"http://175.124.121.213/seniorguide/11_3.jpg",
											iamgeLoaderdb);
									txtViewImagTitle2
											.setText("<���� ���� �ִ� ���� ���� ��� �Ѳ��� ��>\n");
									netImag3.setImageUrl(
											"http://175.124.121.213/seniorguide/11_4.jpg",
											iamgeLoaderdb);
									txtViewImagTitle3
											.setText("<���� �� �� �ֵ��� ����� �� �Ѱ��� ��>");

								} else {

									netImag.setImageUrl(imgUrl, iamgeLoaderdb);
									txtView1.setText(title);
									txtView2.setText(contents);
									txtViewImagTitle.setText(imgTitle);
								}
							}

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(getApplicationContext(),
									"���ͳ� ������ Ȯ���ϼ���", Toast.LENGTH_LONG).show();
						}
						hidepDialog();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						Toast.makeText(getApplicationContext(),
								"���ͳ� ������ Ȯ���ϼ���", Toast.LENGTH_SHORT).show();
						hidepDialog();
					}
				});
		AppController.getInstance().addToRequestQueue(reqdb);
	}

	private void showpDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hidepDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}
	
	//�޴������
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			
			MenuInflater menuIn = getMenuInflater();
			menuIn.inflate(R.menu.sangse_second, menu);
			return true;
			
		}

	//�޴� event
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		
		case R.id.actShare://Share
			Intent it = new Intent(android.content.Intent.ACTION_SEND);
	        it.setType("text/plain");
	        String maintitleTxt = MainTitle.getText().toString();
	        String subTxt = txtView1.getText().toString();
	        String conTxt = txtView2.getText().toString();
	        it.putExtra(Intent.EXTRA_TEXT, maintitleTxt+"\n\n"+subTxt+"\n\n"+conTxt+"\n\n [����]'���'");
	        startActivity(Intent.createChooser(it, "�����ϱ�"));
			return true;
			
		case R.id.actHome://home
			Intent homeIntents = new Intent(getApplicationContext(),Home.class);
			homeIntents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			homeIntents.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(homeIntents);
			finish();
			
			
			return true;
			
			
		case R.id.actBook://���ã��� �̵�
			Intent bookIntent = new Intent(getApplicationContext(),BookmarkHome.class);
			bookIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			bookIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(bookIntent);
			finish();
			
			
			return true;
		
		}
		
		return false;
	}
}
