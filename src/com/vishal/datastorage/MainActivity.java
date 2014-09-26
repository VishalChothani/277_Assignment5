package com.vishal.datastorage;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	DataHandler handler;
	public static final String MyPREFERENCES = "MyPrefs" ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ArrayList<String> sqlList = new ArrayList<String>();
        String outputSql = "";
        
        final Intent intentSql = new Intent(this, SqlView.class);
        
        final Intent intentPreference = new Intent(this, PreferencesStorage.class);
        
        
        SharedPreferences sharedpreferences;
        
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        
        
        Button sqliteButton = (Button) findViewById(R.id.sqliteButton);
        Button preferencesButton = (Button) findViewById(R.id.preferencesButton);
        
        TextView outputTextView = (TextView) findViewById(R.id.outputTextView);
                
        outputTextView.setMovementMethod(new ScrollingMovementMethod());
        
        handler = new DataHandler(getBaseContext());
		handler.open();
		Cursor cursor = handler.getData();
		if(cursor.moveToFirst())
		{
			do
			{
				String message = cursor.getString(0);
				String date = cursor.getString(1);
				String storage = cursor.getString(2);
				//Toast.makeText(getBaseContext(), "Message: "+message, Toast.LENGTH_LONG).show();
				outputSql = "\n\nMessage: "+message + "\nDate: " + date +
						"\nStorage name: " + storage;
						
				sqlList.add(outputSql);
				//outputTextView.setText(output);
				
			}while(cursor.moveToNext());
		}
		
		String book_name = sharedpreferences.getString("book_name", "");
		String book_author = sharedpreferences.getString("book_author", "");
		String description = sharedpreferences.getString("description", "");
		String datePref = sharedpreferences.getString("date", "");
		String storage = sharedpreferences.getString("storage", "");
		
		String outputPrefences = "\n\nBook Name: "+book_name+ "\nBook Author: " + book_author + 
				"\nDescription: "+description+ "\nDate: " + datePref +"\nStorage name: " + storage;
		
		
		outputTextView.setText(sqlList+outputPrefences);
        
        sqliteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(intentSql);
				
			}
		});
        
        preferencesButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(intentPreference);
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
