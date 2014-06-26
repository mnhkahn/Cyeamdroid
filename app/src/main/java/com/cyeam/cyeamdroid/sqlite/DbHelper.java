package com.cyeam.cyeamdroid.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.TableLayout;

public class DbHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "cyeam.db";
	public static final int DB_VERSION = 1;
	public static final String BLOG_TABLE = "blog";
	
	public static final String C_ID = BaseColumns._ID;
	public static final String C_TITLE = "title";
	public static final String C_INFO = "info";
	public static final String C_DESCRIPTION = "description";
    public static final String C_LINK = "link";
    public static final String C_PUBDATE = "pubDate";
	
	Context context;

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
//		String create_process_sql = "create table " + BLOG_TABLE + "(" + C_ID + " INTEGER primary key AUTOINCREMENT, " + C_TITLE + " text, " + C_INFO + " text, " + C_DESCRIPTION + " text, " + C_LINK + " text, " + C_PUBDATE + " text)";
//
//		db.execSQL(create_process_sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
