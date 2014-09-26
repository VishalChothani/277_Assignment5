package com.vishal.datastorage;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SqlView extends ActionBarActivity {

	DataHandler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sql_view);
		
		final EditText blogMessageEditText = (EditText) findViewById(R.id.blogMessageEditText);
		
		Button save = (Button) findViewById(R.id.SaveButton);
		Button cancel = (Button) findViewById(R.id.CancelButton);
		
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String message = blogMessageEditText.getText().toString();
				
				handler = new DataHandler(getBaseContext());
				handler.open();
				long id = handler.insertData(message);								
				Toast.makeText(getBaseContext(), "DATA Inserted Successfully", Toast.LENGTH_LONG).show();
				handler.close();
				
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SqlView.this.finish();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sql_view, menu);
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
