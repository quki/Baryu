package com.quki.gongmo.bary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends ActionBarActivity {

	Button btn1, btn2, btn3, btn4, btn5, txt1, txt2;
	private boolean isTwo = false;

	MyDBHelper myDBHelper = new MyDBHelper(this);
	SQLiteDatabase sqlDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		setTitle("Ȩ");

		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		txt1 = (Button) findViewById(R.id.txt1);
		txt2 = (Button) findViewById(R.id.txt2);

		sqlDB = myDBHelper.getReadableDatabase();
		Cursor cursor;
		cursor = sqlDB.rawQuery("SELECT*from bookmark_SANGI;", null);
		cursor.close();
		sqlDB.close();

		// '���'�ϴ¹�
		txt1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent a = new Intent(getApplicationContext(),
						BaryuHanenBub.class);
				startActivity(a);

			}
		});

		// �����ħ��
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent firstIn = new Intent(getApplicationContext(),
						SangseFirst.class);
				startActivity(firstIn);

			}
		});

		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent secIn = new Intent(getApplicationContext(),
						GyuoFirst.class);
				startActivity(secIn);

			}
		});

		// ��� ����
		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent faceIntent = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("https://m.facebook.com/profile.php?id=955884301123730"));
				startActivity(faceIntent);

			}
		});

		// �ϸ�ũ
		btn5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent secondIn = new Intent(getApplicationContext(),
						BookmarkHome.class);
				startActivity(secondIn);

			}
		});

		txt2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.kats.go.kr/mobile/main.do"));
				startActivity(webIntent);

			}
		});
	}

	@Override
	public void onBackPressed() {

		if (!isTwo) {
			Toast.makeText(this, "\'�ڷ�\'��ư�� �ѹ��� �����ø� ����˴ϴ�", Toast.LENGTH_SHORT)
					.show();
			myTimer timer = new myTimer(2000, 1); // 2�ʵ��� ����
			timer.start(); // Ÿ�̸Ӹ� �̿����ݽô�
		} else {
			Toast.makeText(getApplicationContext(), "������ �� '���'�ؿ�",
					Toast.LENGTH_SHORT).show();
			// android.os.Process.killProcess(android.os.Process.myPid());
			// //���μ��� ������
			finish();
		}

	}

	public class myTimer extends CountDownTimer {

		public myTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			isTwo = true;
		}

		@Override
		public void onFinish() {
			isTwo = false;
		}

		@Override
		public void onTick(long millisUntilFinished) {
			Log.i("Test", "isTwo" + isTwo);
		}

	}
}
