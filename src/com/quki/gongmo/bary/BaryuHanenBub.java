package com.quki.gongmo.bary;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class BaryuHanenBub extends ActionBarActivity {

	TextView txtViewFirst, txtViewSecond;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baryu_hanen_bub);
		setTitle("'배려'하는 법");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		txtViewFirst = (TextView) findViewById(R.id.txtViewFirst);
		txtViewSecond = (TextView) findViewById(R.id.txtViewSecond);

	}

	// 메뉴만들기
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater menuIn = getMenuInflater();
		menuIn.inflate(R.menu.baryu_hanen_bub, menu);
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
