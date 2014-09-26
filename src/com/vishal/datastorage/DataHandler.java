package com.vishal.datastorage;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHandler {

	public static final String MESSAGE = "message";
	public static final String FROM_STROAGE = "stroage";
	//public static Date CURRENT_DATE = new Date();
	public Date CURRENT_DATE;
	public static String CURR_DATE = "current_date";
	
	public static final String TABLE_NAME = "blog_message";
	public static final String DATABASE_NAME = "storage";
	
	public static final int DATABASE_VERSION = 2;
	
	public static final String TABLE_CREATE = "create table " +TABLE_NAME+"("+MESSAGE+ " text not null," + CURR_DATE + " text not null, "  + FROM_STROAGE + " text not null );";

	DataBaseHelper dbhelper;	
	Context context;
	
	SQLiteDatabase sqlDb;
	
	public DataHandler(Context context)
	{
		this.context = context;
		dbhelper = new DataBaseHelper(context);
	}
	
	private static class DataBaseHelper extends SQLiteOpenHelper
	{

		public DataBaseHelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try
			{
				db.execSQL(TABLE_CREATE);
			}
			catch(Exception e)
			{
				
			}
			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.i("SQL", "Inside");
			db.execSQL("DROP TABLE IF EXISTS user_information");
			onCreate(db);
		}
		
	}
	
	public DataHandler open()
	{
		sqlDb = dbhelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		dbhelper.close();
	}
	
	public long insertData(String message)
	{
		ContentValues contentValues = new ContentValues();	
		
		CURRENT_DATE = new Date();
		
		contentValues.put(MESSAGE, message);
		contentValues.put(CURR_DATE, CURRENT_DATE + "");
		contentValues.put(FROM_STROAGE, "SQLite"); 
		return sqlDb.insert(TABLE_NAME,null,contentValues);
	}
	
	public Cursor getData()
	{
		return sqlDb.query(TABLE_NAME, new String[] {MESSAGE,CURR_DATE,FROM_STROAGE}, null, null, null, null, null);
	}
	
}
