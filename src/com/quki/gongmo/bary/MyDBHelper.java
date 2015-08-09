package com.quki.gongmo.bary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

	public MyDBHelper(Context context) {
		super(context, "RealNewDB", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table bookmark_SANGI(myid varchar(7),maintitle varchar(50),subtitle varchar(50));");
		db.execSQL("create table bookmark_SAUP(local varchar(15),main varchar(20),pho varchar(15),adone varchar(50),adtwo varchar(50),widodb varchar(15),kyungdo varchar(15));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS bookmark_SANGI");
		db.execSQL("DROP TABLE IF EXISTS bookmark_SAUP");
		onCreate(db);

	}

}
