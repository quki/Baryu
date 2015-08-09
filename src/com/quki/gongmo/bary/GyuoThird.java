package com.quki.gongmo.bary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GyuoThird extends AppCompatActivity implements
		OnMarkerClickListener, OnInfoWindowClickListener, OnMarkerDragListener {

	// Google Map
	GoogleMap googleMap;
	MapFragment mf;
	Double doublewido, doublekyungdo;

	TextView location, mainTitle, telephone, adress1, adress2, wido, kyungdo;
	CheckBox checkBox;
	String phoneIdSearch;
	String phoneId, main, adtwo;

	// DB
	MyDBHelper myDBHelper = new MyDBHelper(this);
	SQLiteDatabase sqlDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gyuo_third);
		setTitle("직업훈련기관");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		location = (TextView) findViewById(R.id.location);
		mainTitle = (TextView) findViewById(R.id.mainTitle);
		telephone = (TextView) findViewById(R.id.telephone);
		adress1 = (TextView) findViewById(R.id.adress1);
		adress2 = (TextView) findViewById(R.id.adress2);
		wido = (TextView) findViewById(R.id.wido);
		kyungdo = (TextView) findViewById(R.id.kyungdo);
		checkBox = (CheckBox) findViewById(R.id.checkBox);
		mf = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

		// ListView에서 가져온거
		Intent i = getIntent();
		String title = i.getStringExtra("title");
		String locali = i.getStringExtra("location");
		String ad1 = i.getStringExtra("adress1");
		String ad2 = i.getStringExtra("adress2");
		String phone = i.getStringExtra("phone");
		String releaseWido = i.getStringExtra("releaseWido");
		String releaseKyungdo = i.getStringExtra("releaseKyungdo");
		this.phoneId = phone;

		// querry로 찾아내서 checkbox제어
		sqlDB = myDBHelper.getReadableDatabase();
		Cursor cursor;
		cursor = sqlDB.rawQuery("SELECT*from bookmark_SAUP where pho = '"
				+ phoneId + "';", null);

		while (cursor.moveToNext()) {
			phoneIdSearch = cursor.getString(2);

			if (phoneIdSearch.equals(phoneId)) {
				checkBox.setChecked(true);
			}
		}

		cursor.close();
		sqlDB.close();

		location.setText(locali);
		mainTitle.setText(title);
		telephone.setText(phone);
		wido.setText(releaseWido);
		kyungdo.setText(releaseKyungdo);

		// null 값처리
		try {
			if (ad1.equals("null")) {
				adress1.setText("");
			} else {
				adress1.setText(ad1);
			}

			if (ad2.equals("null")) {
				adress2.setText("");
			} else {
				adress2.setText(ad2);
			}
		} catch (Exception e) {
		}

		// TextView에 있는 text긁어모으기
		final String local = location.getText().toString();
		this.main = mainTitle.getText().toString();
		final String pho = telephone.getText().toString();
		final String adone = adress1.getText().toString();
		this.adtwo = adress2.getText().toString();
		final String strWido = wido.getText().toString();
		final String strKyungdo = kyungdo.getText().toString();
		this.doublewido = Double.parseDouble(strWido);
		this.doublekyungdo = Double.parseDouble(strKyungdo);

		// 구글맵

		googleMap = mf.getMap();
		googleMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng lat) {
				// map.clear();
				googleMap.animateCamera(CameraUpdateFactory.newLatLng(lat));
				// 애니메이션 없이

			}
		});

		googleMap.setOnMarkerClickListener(this);
		googleMap.setOnInfoWindowClickListener(this);
		googleMap.setOnMarkerDragListener(this);
		//googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		googleMap.setMyLocationEnabled(true); // by default, false
		UiSettings setting = googleMap.getUiSettings();
		setting.setMyLocationButtonEnabled(false); // by default, true
		setting.setCompassEnabled(true); // by default, true
		setting.setZoomControlsEnabled(true); // by default, true

		// 즐겨찾기
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (checkBox.isChecked() == true) {
					Toast.makeText(getApplicationContext(), "즐겨찾기에 등록되었습니다",
							Toast.LENGTH_SHORT).show();
					sqlDB = myDBHelper.getWritableDatabase();
					sqlDB.execSQL("insert into bookmark_SAUP values('" + local
							+ "','" + main + "','" + pho + "','" + adone
							+ "','" + adtwo + "','" + strWido + "','"
							+ strKyungdo + "');");
					sqlDB.close();

				} else {
					Toast.makeText(getApplicationContext(), "즐겨찾기에서 삭제되었습니다",
							Toast.LENGTH_SHORT).show();
					sqlDB = myDBHelper.getWritableDatabase();
					sqlDB.execSQL("DELETE FROM bookmark_SAUP where pho='" + pho
							+ "';");
					sqlDB.close();
				}
			}
		});
	}

	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerDragEnd(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected void onResume() {
		super.onResume();
		addMarker();
		setPosition();
	}

	private void setPosition() {
		LatLng latlng = new LatLng(doublewido, doublekyungdo);
		CameraPosition cp = new CameraPosition.Builder().target((latlng))
				.zoom(15).build();
		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
	}

	private void addMarker() {
		googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(doublewido,doublekyungdo)).title(main)
				.snippet(adtwo).draggable(true));

	}
	//메뉴만들기
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater menuIn = getMenuInflater();
		menuIn.inflate(R.menu.gyuo_third, menu);
		return true;
		
	}
	
	//메뉴 event
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		
		case android.R.id.home:
			finish();
			return true;
		
		
		case R.id.actShare://Share
			Intent it = new Intent(android.content.Intent.ACTION_SEND);
	        it.setType("text/plain");
	        String titleTxt = mainTitle.getText().toString();
	        String teleTxt = telephone.getText().toString();
	        String adTxt = adress2.getText().toString();
	        it.putExtra(Intent.EXTRA_TEXT, titleTxt+"\n\n"+teleTxt+"\n\n"+adTxt+"\n\n [참조]'배려'");
	        startActivity(Intent.createChooser(it, "공유하기"));
			return true;
			
		case R.id.actHome://home
			Intent homeIntent = new Intent(getApplicationContext(),Home.class);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(homeIntent);
			finish();
			return true;
			
			
		case R.id.actBook://즐겨찾기로 이동
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
