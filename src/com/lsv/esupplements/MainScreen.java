package com.lsv.esupplements;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.Toast;

// Главное окно входа в АПП
public class MainScreen extends Activity implements TabContentFactory {

	private static final int ID_ABOUT = 101;
	private static final int ID_EXIT = 102;
	private Display display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_screen_activity);

		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

		tabHost.setup();

		TabHost.TabSpec byNumberSpec = tabHost.newTabSpec("ByNumber");
		byNumberSpec.setContent(this);
		
		byNumberSpec.setIndicator(getResources().getString(
				R.string.byNumberIndicator), getResources().getDrawable(android.R.drawable.ic_menu_sort_by_size));
		tabHost.addTab(byNumberSpec);

		TabHost.TabSpec byCategorySpec = tabHost.newTabSpec("ByCategory");
		byCategorySpec.setContent(this);
		byCategorySpec.setIndicator(getResources().getString(
				R.string.ByCategoryIndicator), getResources().getDrawable(android.R.drawable.ic_menu_sort_alphabetically));
		tabHost.addTab(byCategorySpec);
		tabHost.setCurrentTab(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(Menu.NONE, ID_ABOUT, Menu.NONE, "About").setIcon(
				android.R.drawable.ic_menu_info_details);
		menu.add(Menu.NONE, ID_EXIT, Menu.NONE, "Exit").setIcon(
				android.R.drawable.ic_lock_power_off);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case ID_ABOUT:
			Intent aboutIntent = new Intent(this, AboutActivity.class);
			startActivity(aboutIntent);
			break;
		case ID_EXIT:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.exit_message);
			builder.setTitle(R.string.exit);
			builder.setIcon(android.R.drawable.ic_lock_power_off);
			builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
			builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			
			AlertDialog alert = builder.create();
			alert.show();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public View createTabContent(String tag) {
		if (tag.equalsIgnoreCase("ByNumber")) {
			TabView numberView = new TabView(this, getResources()
					.getStringArray(R.array.ByNumberArray));
			return numberView.getScroll();
		}

		else if (tag.equalsIgnoreCase("ByCategory")) {
			TabView categoryView = new TabView(this, getResources()
					.getStringArray(R.array.ByCategoryArray));
			return categoryView.getScroll();
		}

		return null;
	}

}
