package com.quki.gongmo.bary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

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

public class FromBookmarkGyuo extends ActionBarActivity implements
		OnMarkerClickListener, OnInfoWindowClickListener, OnMarkerDragListener {

	TextView location, mainTitle, telephone, adress1, adress2, wido, kyungdo;
	String strWi, strKy;
	MapFragment mf;
	GoogleMap googleMap;
	Double doubleWi, doubleKy;
	String strMain, strLoc;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.from_bookmark_gyuo);
		setTitle("직업훈련기관");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		location = (TextView) findViewById(R.id.location);
		mainTitle = (TextView) findViewById(R.id.mainTitle);
		telephone = (TextView) findViewById(R.id.telephone);
		adress1 = (TextView) findViewById(R.id.adress1);
		adress2 = (TextView) findViewById(R.id.adress2);
		wido = (TextView) findViewById(R.id.wido);
		kyungdo = (TextView) findViewById(R.id.kyungdo);
		mf = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

		// DB에서 가져온거
		Intent d = getIntent();
		String locaDB = d.getStringExtra("loca");
		String mainTitleDB = d.getStringExtra("mainTitle");
		String phoneDB = d.getStringExtra("phone");
		String adoneDB = d.getStringExtra("adone");
		String adtwoDB = d.getStringExtra("adtwo");
		String widoDB = d.getStringExtra("widodb");
		String kydoDB = d.getStringExtra("kydodb");

		location.setText(locaDB);
		mainTitle.setText(mainTitleDB);
		telephone.setText(phoneDB);
		wido.setText(widoDB);
		kyungdo.setText(kydoDB);

		this.strLoc = location.getText().toString();
		this.strMain = mainTitle.getText().toString();
		strWi = wido.getText().toString();
		strKy = kyungdo.getText().toString();

		this.doubleWi = Double.parseDouble(strWi);
		this.doubleKy = Double.parseDouble(strKy);

		// null 값처리
		try {
			if (adoneDB.equals("null")) {
				adress1.setText("");
			} else {
				adress1.setText(adoneDB);
			}

			if (adtwoDB.equals("null")) {
				adress2.setText("");
			} else {
				adress2.setText(adtwoDB);
			}
		} catch (Exception e) {
		}
		googleMap = mf.getMap();
		googleMap.setOnMarkerClickListener(this);
		googleMap.setOnInfoWindowClickListener(this);
		googleMap.setOnMarkerDragListener(this);
		// map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		googleMap.setMyLocationEnabled(true); // by default, false
		UiSettings setting = googleMap.getUiSettings();
		setting.setMyLocationButtonEnabled(false); // by default, true
		setting.setCompassEnabled(true); // by default, true
		setting.setZoomControlsEnabled(true); // by default, true
		googleMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng lat) {
				// map.clear();
				googleMap.animateCamera(CameraUpdateFactory.newLatLng(lat));
				// 애니메이션 없이

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
		LatLng latlng = new LatLng(doubleWi, doubleKy);
		CameraPosition cp = new CameraPosition.Builder().target((latlng))
				.zoom(15).build();
		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
	}

	private void addMarker() {
		googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(doubleWi, doubleKy)).title(strMain)
				.snippet(strLoc).draggable(true));

	}

	// 메뉴만들기
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater menuIn = getMenuInflater();
		menuIn.inflate(R.menu.from_bookmark_gyuo, menu);
		return true;

	}

	// 메뉴 event
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			finish();
			return true;

		case R.id.actShare1:// Share
			Intent it = new Intent(android.content.Intent.ACTION_SEND);
			it.setType("text/plain");
			String titleTxt = mainTitle.getText().toString();
			String teleTxt = telephone.getText().toString();
			String adTxt = adress2.getText().toString();
			it.putExtra(Intent.EXTRA_TEXT, titleTxt + "\n\n" + teleTxt + "\n\n"
					+ adTxt + "\n\n [참조]'배려'");
			startActivity(Intent.createChooser(it, "공유하기"));
			return true;

		case R.id.actHome1:// home
			Intent homeIntent = new Intent(getApplicationContext(), Home.class);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(homeIntent);
			finish();
			return true;

		case R.id.actBook1:// 즐겨찾기로 이동
			Intent bookIntent = new Intent(getApplicationContext(),
					BookmarkHome.class);
			bookIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			bookIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(bookIntent);
			finish();
			return true;

		}

		return false;
	}

}
