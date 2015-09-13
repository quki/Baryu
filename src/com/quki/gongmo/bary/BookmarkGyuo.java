package com.quki.gongmo.bary;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BookmarkGyuo extends ListActivity {
	
	MyDBHelper myDBHelper = new MyDBHelper(this);
	SQLiteDatabase sqlDB;
	HashMap<String, String> item;

	String strloc, strMain, strPho, strAdone, strAdtwo, strWido,strKyungdo;
	Button backBtn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookmark_gyuo);
		
		backBtn1 = (Button)findViewById(R.id.backBtn1);
		
		
		// 데이터 가져오기
				sqlDB = myDBHelper.getReadableDatabase();
				Cursor cursor;
				cursor = sqlDB.rawQuery("SELECT*from bookmark_SAUP;", null);
				final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				while (cursor.moveToNext()) {

					item = new HashMap<String, String>();
					strloc = cursor.getString(0);
					item.put("Tag01", strloc);
					strMain = cursor.getString(1);
					item.put("Tag02", strMain);
					strPho = cursor.getString(2);
					item.put("Tag03", strPho);
					strAdone = cursor.getString(3);
					item.put("Tag04", strAdone);
					strAdtwo = cursor.getString(4);
					item.put("Tag05", strAdtwo);
					strWido = cursor.getString(5);
					item.put("Tag06", strWido);
					strKyungdo = cursor.getString(6);
					item.put("Tag07", strKyungdo);
					list.add(item);

				}
				final SimpleAdapter notes = new SimpleAdapter(this, list,
						R.layout.list_book_gyuo, new String[] { "Tag01", "Tag02",
								"Tag03", "Tag04", "Tag05", "Tag06","Tag07" }, new int[] {
								R.id.loca, R.id.mainTitle, R.id.phone, R.id.adone,
								R.id.adtwo, R.id.wi,R.id.ky });
				setListAdapter(notes);
				cursor.close();
				sqlDB.close();

				ListView lv = getListView();
				lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						// TextView에 있는값을 변수로
						String loca = ((TextView) view.findViewById(R.id.loca))
								.getText().toString();
						String mainTitle = ((TextView) view
								.findViewById(R.id.mainTitle)).getText().toString();
						String phone = ((TextView) view.findViewById(R.id.phone))
								.getText().toString();
						String adone = ((TextView) view.findViewById(R.id.adone))
								.getText().toString();

						String adtwo = ((TextView) view.findViewById(R.id.adtwo))
								.getText().toString();
						String wi = ((TextView) view.findViewById(R.id.wi))
								.getText().toString();
						String ky = ((TextView) view.findViewById(R.id.ky))
								.getText().toString();

						Intent myIntent = new Intent(getApplicationContext(),
								FromBookmarkGyuo.class);
						myIntent.putExtra("loca", loca);
						myIntent.putExtra("mainTitle", mainTitle);
						myIntent.putExtra("phone", phone);
						myIntent.putExtra("adone", adone);
						myIntent.putExtra("adtwo", adtwo);
						myIntent.putExtra("widodb", wi);
						myIntent.putExtra("kydodb", ky);
						startActivity(myIntent);

					}

				});

				lv.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							final View view, final int position, long id) {
						android.support.v7.app.AlertDialog.Builder dlg = new android.support.v7.app.AlertDialog.Builder(
								BookmarkGyuo.this);
						dlg.setTitle("삭제");
						dlg.setMessage("정말로 삭제 하시겠습니까?");
						dlg.setIcon(R.drawable.ic_action_discard);
						dlg.setNegativeButton("취소", null);
						dlg.setPositiveButton("삭제", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {

								String phoneId = ((TextView) view
										.findViewById(R.id.phone)).getText().toString();
								sqlDB = myDBHelper.getWritableDatabase();
								sqlDB.execSQL("DELETE FROM bookmark_SAUP WHERE pho='"
										+ phoneId + "';");
								Toast.makeText(BookmarkGyuo.this,
										"즐겨찾기에서 삭제되었습니다", Toast.LENGTH_SHORT).show();

								list.remove(position);
								notes.notifyDataSetChanged();
							}
						});
						dlg.show();
						return true;
					}

				});
				
				//뒤로가기
				backBtn1.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {

						finish();
						
					}
				});
	}


}
