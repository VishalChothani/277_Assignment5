package com.vishal.datastorage;

import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PreferencesStorage extends ActionBarActivity {

	public static final String MyPREFERENCES = "MyPrefs" ;
	
	SharedPreferences sharedpreferences;
	
	private Date CURRENT_DATE;
	private String DATE = "date";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferences_storage);

		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		
		final EditText bookNameEditText = (EditText) findViewById(R.id.bookNameEditText);
		final EditText bookAuthorEditText = (EditText) findViewById(R.id.bookAuthorEditText);
		final EditText descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);

		Button save = (Button) findViewById(R.id.SaveButton);
		Button cancel = (Button) findViewById(R.id.CancelButton);

		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String book_name = bookNameEditText.getText().toString();
				String book_author = bookAuthorEditText.getText().toString();
				String description = descriptionEditText.getText().toString();
				
				if(book_name.length() != 0 && book_author.length() != 0 && description.length() != 0)
				{
					CURRENT_DATE = new Date();
					DATE = CURRENT_DATE + "";
					String storage = "sharedPreference";
					
					Editor editor = sharedpreferences.edit();
					
					editor.putString("book_name", book_name);
					editor.putString("book_author", book_author);
					editor.putString("description", description);
					editor.putString("date", DATE);
					editor.putString("storage", storage);
					editor.commit();	
					
					Toast.makeText(getBaseContext(), "DATA Stored in Preferences Successfully", Toast.LENGTH_LONG).show();					
				}
				else
				{
					Toast.makeText(getBaseContext(), "Please fill all the information", Toast.LENGTH_LONG).show();
				}
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				PreferencesStorage.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preferences_storage, menu);
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
