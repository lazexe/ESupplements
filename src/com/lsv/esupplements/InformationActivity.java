package com.lsv.esupplements;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class InformationActivity extends Activity {

	private String query;
	private String value;
	
	private TextView eName;
	private TextView eFullName;
	private TextView eCategory;
	private TextView eDescription;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.information_activity);
		
		eName = (TextView) findViewById(R.id.e_name);
		eFullName = (TextView) findViewById(R.id.e_full_name);
		eCategory = (TextView) findViewById(R.id.e_category);
		eDescription = (TextView) findViewById(R.id.e_description);
		
		value = getIntent().getExtras().getString("query_value");
		query = "SELECT * FROM esupplements_table WHERE name = '" + value + "'";
		
		DataBaseHelper dbHelper = new DataBaseHelper(this);
		try {
			dbHelper.createDatabase();
			dbHelper.openDataBase();
		} catch(SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		Cursor cursor;
		cursor = dbHelper.getMyDataBase().rawQuery(query, null);
		if (cursor.getCount() == 0) {
			Log.d("COLUMN COUNT IS 0", "COLUMN COUNT IS 0");
			cursor.close();
			dbHelper.close();
			return;
		}
		
		cursor.moveToFirst();
		
		eName.setText(cursor.getString(cursor.getColumnIndex("name")));
		eFullName.setText("Full name: " + cursor.getString(cursor.getColumnIndex("full_name")));;
		eCategory.setText("Category: " + cursor.getString(cursor.getColumnIndex("category")));
		eDescription.setText("Possible usage: \n" + cursor.getString(cursor.getColumnIndex("description")));
		
		cursor.close();
		dbHelper.close();
	}
}
