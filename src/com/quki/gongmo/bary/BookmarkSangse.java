package com.quki.gongmo.bary;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
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

public class BookmarkSangse extends ListActivity {

	MyDBHelper myDBHelper = new MyDBHelper(this);
	SQLiteDatabase sqlDB;
	ListView listViewBook;
	String strMyId, strMyMain, strMySub;
	TextView txtView,txtViewNone; // �ٿ뵵...
	HashMap<String, String> item;
	String idSearch;
	
	Button backBtn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookmark_sangse);
		
		backBtn2 = (Button)findViewById(R.id.backBtn2);
		
		// ������ ��������
		sqlDB = myDBHelper.getReadableDatabase();
		Cursor cursor;
		cursor = sqlDB.rawQuery("SELECT*from bookmark_SANGI;", null);
		
		final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		while (cursor.moveToNext()) {

			item = new HashMap<String, String>();
			strMyMain = cursor.getString(1);
			item.put("Tag01", strMyMain);
			strMySub = cursor.getString(2);
			item.put("Tag02", strMySub);
			strMyId = cursor.getString(0);
			item.put("Tag03", strMyId);
			list.add(item);

		}

		final SimpleAdapter notes = new SimpleAdapter(this, list,
				R.layout.list_book_sangse, new String[] { "Tag01", "Tag02",
						"Tag03" }, new int[] { R.id.mainTitle, R.id.subTitle,
						R.id.myId });
		setListAdapter(notes);
		cursor.close();
		sqlDB.close();

		ListView lv = getListView();
		lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {

				// TextView�� �ִ°��� ������
				String myId = ((TextView) view.findViewById(R.id.myId))
						.getText().toString();
				String parentdb = ((TextView) view.findViewById(R.id.mainTitle))
						.getText().toString();
				String childdb = ((TextView) view.findViewById(R.id.subTitle))
						.getText().toString();


				// ����Ʈ
				Intent myIntent = new Intent(getApplicationContext(),
						SangseSecond.class);
				myIntent.putExtra("myIdb", myId);
				myIntent.putExtra("parentdb", parentdb);
				myIntent.putExtra("childdb", childdb);
				startActivity(myIntent);

			}

		});
		lv.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent,
					final View view, final int position, long id) {

				AlertDialog.Builder dlg = new AlertDialog.Builder(
						BookmarkSangse.this);
				dlg.setTitle("����");
				dlg.setMessage("������ ���� �Ͻðڽ��ϱ�?");
				dlg.setIcon(R.drawable.ic_action_discard);
				dlg.setNegativeButton("���", null);
				dlg.setPositiveButton("����", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						String myId = ((TextView) view.findViewById(R.id.myId))
								.getText().toString();
						sqlDB = myDBHelper.getWritableDatabase();
						sqlDB.execSQL("DELETE FROM bookmark_SANGI WHERE myid='"
								+ myId + "';");
						Toast.makeText(getApplicationContext(),
								"���ã�⿡�� �����Ǿ����ϴ�", Toast.LENGTH_SHORT).show();

						list.remove(position);
						notes.notifyDataSetChanged();
					}
				});
				dlg.show();
				return true;
			}
		});
		
		backBtn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				finish();
				
			}
		});
	}

}
