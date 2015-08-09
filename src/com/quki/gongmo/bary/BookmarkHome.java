package com.quki.gongmo.bary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BookmarkHome extends ActionBarActivity {

	Button btn1, btn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookmark_home);
		setTitle("즐겨찾기");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);

		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent firstIntent = new Intent(getApplicationContext(),
						BookmarkSangse.class);
				startActivity(firstIntent);

			}
		});

		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent secondIntent = new Intent(getApplicationContext(),
						BookmarkGyuo.class);
				startActivity(secondIntent);

			}
		});

	}

	// 메뉴만들기
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater menuIn = getMenuInflater();
		menuIn.inflate(R.menu.bookmark_home, menu);
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
