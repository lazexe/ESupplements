package com.lsv.esupplements;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.Toast;

public class EButtonsActivity extends Activity implements OnClickListener {

	ArrayList<String> buttonsTextArrayList;
	ArrayList<SimpleButton> eButtons;

	private String extraValue;
	private String query;
	private Cursor cursor;
	private WindowManager wm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ebuttons_activity);
		wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		buttonsTextArrayList = new ArrayList<String>();
		extraValue = getIntent().getExtras().getString("value");
		query = getQuery();
		createButtonsCaptions();
		eButtons = new ArrayList<SimpleButton>();
		putButtons();
	}

	private String getQuery() {
		String query = null;
		
		if (extraValue.equalsIgnoreCase("e100-e200")) {
			query = "SELECT * FROM esupplements_table WHERE num >=100 and num <= 200";
		} else if (extraValue.equalsIgnoreCase("e201-e300")) {
			query = "SELECT * FROM esupplements_table WHERE num >=201 and num <= 300";
		} else if (extraValue.equalsIgnoreCase("e301-e400")) {
			query = "SELECT * FROM esupplements_table WHERE num >=301 and num <= 400";
		} else if (extraValue.equalsIgnoreCase("e401-e500")) {
			query = "SELECT * FROM esupplements_table WHERE num >=401 and num <= 500";
		} else if (extraValue.equalsIgnoreCase("e501-e600")) {
			query = "SELECT * FROM esupplements_table WHERE num >=501 and num <= 600";
		} else if (extraValue.equalsIgnoreCase("e601-e700")) {
			query = "SELECT * FROM esupplements_table WHERE num >=601 and num <= 700";
		} else if (extraValue.equalsIgnoreCase("e701-e800")) {
			query = "SELECT * FROM esupplements_table WHERE num >=701 and num <= 800";
		} else if (extraValue.equalsIgnoreCase("e801-e900")) {
			query = "SELECT * FROM esupplements_table WHERE num >=801 and num <= 900";
		} else if (extraValue.equalsIgnoreCase("e901-e1000")) {
			query = "SELECT * FROM esupplements_table WHERE num >=901 and num <= 1000";
		} else if (extraValue.equalsIgnoreCase("Dangerous")) {
			query = "SELECT * FROM esupplements_table WHERE category = 'DANGEROUS'";
		} else if (extraValue.equalsIgnoreCase("Very dangerous")) {
			query = "SELECT * FROM esupplements_table WHERE category = 'VERY DANGEROUS'";
		} else if (extraValue.equalsIgnoreCase("Carcinogenic")) {
			query = "SELECT * FROM esupplements_table WHERE category = 'CARCINOGENIC'";
		} else if (extraValue.equalsIgnoreCase("Stomach upset")) {
			query = "SELECT * FROM esupplements_table WHERE category = 'STOMACH UPSET'";
		} else if (extraValue.equalsIgnoreCase("Skin deseases")) {
			query = "SELECT * FROM esupplements_table WHERE category = 'SKIN DESEASES'";
		} else if (extraValue.equalsIgnoreCase("Arterial tension disturbance")) {
			query = "SELECT * FROM esupplements_table WHERE category = 'ARTERIAL TENSION DISTURBANCE'";
		} else if (extraValue.equalsIgnoreCase("Dangerous for children")) {
			query = "SELECT * FROM esupplements_table WHERE category = 'DANGEROUS FOR CHILDREN'";
		} else if (extraValue.equalsIgnoreCase("Prohibited")) {
			query = "SELECT * FROM esupplements_table WHERE category = 'PROHIBITED'";
		} else if (extraValue.equalsIgnoreCase("Suspicious")) {
			query = "SELECT * FROM esupplements_table WHERE category = 'SUSPICIOUS'";
		} else {
			Toast.makeText(this, "Some error in getQuery()", Toast.LENGTH_LONG).show();
		}

		Log.d("VALUE", String.valueOf(query));

		return query;
	}

	private void createButtonsCaptions() {
		DataBaseHelper dbHelper = new DataBaseHelper(this);
		try {
			dbHelper.createDatabase();
			dbHelper.openDataBase();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		String name = null;
		Log.d("QUERY", query);
		cursor = dbHelper.getMyDataBase().rawQuery(query, null);
		if (cursor.getCount() == 0) {
			Log.d("COLUMN COUNT IS 0", "COLUMN COUNT IS 0");
			cursor.close();
			dbHelper.close();
			return;
		}
		do {
			cursor.moveToNext();
			name = cursor.getString(cursor.getColumnIndex("name"));
			buttonsTextArrayList.add(name);
			Log.d("TEST", name);
		} while (!cursor.isLast());
		cursor.close();
		dbHelper.close();
	}

	private void putButtons() {
		ColorKeeper.setI(0);
		TableRow tempTableRow = null;
		TableLayout container = (TableLayout) findViewById(R.id.ebutton_container);

		for (int i = 0; i < buttonsTextArrayList.size(); i++) {
			if (i % 2 == 0) {
				tempTableRow = new TableRow(this);
				// tempTableRow.setLayoutParams(new
				// TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
				// TableRow.LayoutParams.MATCH_PARENT));
				LayoutParams test = new LayoutParams();
				test.height = (int) (wm.getDefaultDisplay().getHeight() * 0.15);
				test.weight = 1f;
				// test.height = TableRow.LayoutParams.WRAP_CONTENT;
				tempTableRow.setLayoutParams(test);
				container.addView(tempTableRow);
			}
			eButtons.add(createButton(i));
			tempTableRow.addView(eButtons.get(i));
		}
		
		if (buttonsTextArrayList.size() % 2 == 1) {
			Button bt = new Button(this);
			bt.setText("Invisible");
			TableRow.LayoutParams buttonsParams = 
					new TableRow.LayoutParams(
							LinearLayout.LayoutParams.MATCH_PARENT, 
							LinearLayout.LayoutParams.MATCH_PARENT, 
							1.0f);
			
			// TODO font

			//button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
			buttonsParams.weight = 1f;
			buttonsParams.setMargins(5, 5, 5, 5);
			bt.setLayoutParams(buttonsParams);
			bt.setBackgroundColor(Color.BLACK);
			bt.setPadding(0, 0, 0, 0);
			bt.setMaxHeight(0);
			bt.setMaxWidth(0);
			bt.setEnabled(false);
			bt.setVisibility(Button.INVISIBLE);
			tempTableRow.addView(bt);
		}
		
		ColorKeeper.setI(0);
		for (Button item : eButtons) {
			changeButtonParams(item);
			/*
			 * if (item.getText().equals("Invisible")) {
			 * item.setVisibility(INVISIBLE); item.setEnabled(false); }
			 */
		}
	}

	private void changeButtonParams(Button button) {
		
		TableRow.LayoutParams buttonsParams = null;
		buttonsParams = new TableRow.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
		// TODO font

		// button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		buttonsParams.height = (int) (wm.getDefaultDisplay().getHeight() * 0.15);
		buttonsParams.weight = 1f;
		buttonsParams.setMargins(5, 5, 5, 5);
		button.setLayoutParams(buttonsParams);

		button.setPadding(0, 0, 0, 0);
		button.setMaxHeight(0);
		button.setMaxWidth(0);
	}

	private SimpleButton createButton(int i) {
		SimpleButton tempButton = new SimpleButton(this);
		tempButton.setText(buttonsTextArrayList.get(i).toString());
		tempButton.setTextColor(Color.WHITE);
		if (i % 9 == 0 && i > 0) {
			tempButton.setTextColor(Color.BLACK);
		}
		tempButton.setKeepedColor(Color.parseColor(ColorKeeper.getColor()));
		tempButton.setBackgroundColor(tempButton.getKeepedColor());
		//tempButton.setOnTouchListener(new ButtonTouchListener(tempButton));
		tempButton.setOnClickListener(this);
		return tempButton;
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("EBUTTON", "On resume");
	}

	@Override
	public void onClick(View v) {
		Button clickedButton = (Button) v;
		Log.d("Button caption", clickedButton.getText().toString());

		Intent intent = new Intent(this, InformationActivity.class);
		intent.putExtra("query_value", clickedButton.getText().toString());
		this.startActivity(intent);

	}

}
