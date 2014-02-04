package com.lsv.esupplements;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;

// Универсальная вьюшка для каждого Таба главного экрана
public class TabView extends View implements View.OnClickListener {

	private Context context;
	private String[] list;
	private TableLayout container;
	private TableRow tempTableRow;
	private TableRow.LayoutParams buttonsParams;
	private ArrayList<SimpleButton> buttons;
	private WindowManager wm;
	private ScrollView scroll;

	public TabView(Context context, String[] list) {
		super(context);
		
		scroll = new ScrollView(context);
		scroll.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

		this.context = context;
		this.list = list;
		
		container = new TableLayout(context);
		container.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		// Добавляем кнопки на контейнер
		buttons = new ArrayList<SimpleButton>();
		SimpleButton button;
		for (int i = 0; i < list.length; i++) {
			if (i % 2 == 0) {
				tempTableRow = new TableRow(context);
				tempTableRow.setId(i);
				tempTableRow.setMinimumHeight((int) (wm.getDefaultDisplay().getHeight() * 0.15));
				container.addView(tempTableRow);
			}
			button = createButton(i);
			buttons.add(button);
			changeButtonParams(button);
			tempTableRow.addView(buttons.get(i));
		}
		ColorKeeper.setI(0);
		for (Button item : buttons) {
			if (item.getText().equals("Invisible")) {
				item.setVisibility(INVISIBLE);
				item.setEnabled(false);
			}
		}
		Log.d("MAXIM LAZARENKO", "FINISH");
		scroll.addView(container);
	}
	
	private void changeButtonParams(SimpleButton button) {
		buttonsParams = 
				new TableRow.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT, 
						LinearLayout.LayoutParams.MATCH_PARENT, 
						1.0f);

		buttonsParams.height = (int) (wm.getDefaultDisplay().getHeight() * 0.15);
		buttonsParams.setMargins(5, 5, 5, 5);
		button.setLayoutParams(buttonsParams);
		button.setPadding(0, 0, 0, 0);
		button.setSingleLine();
		button.setMinHeight((int) (wm.getDefaultDisplay().getHeight() * 0.15));
		button.setMaxWidth(0);
	}
	
	private SimpleButton createButton(int i) {
		SimpleButton tempButton = new SimpleButton(context);
		tempButton.setText(list[i].toString());
		tempButton.setTextColor(Color.WHITE);
		if (i % 9 == 0 && i > 0) {
			tempButton.setTextColor(Color.BLACK);
		}
		tempButton.setKeepedColor(Color.parseColor(ColorKeeper.getColor()));
		tempButton.setBackgroundColor(tempButton.getKeepedColor());
		tempButton.setOnClickListener(this);
		return tempButton;
	}

	public TableLayout getContainer() {
		return container;
	}

	public ScrollView getScroll() {
		return scroll;
	}

	public void setScroll(ScrollView scroll) {
		this.scroll = scroll;
	}

	@Override
	public void onClick(View v) {
		Button clickedButton = (Button) v;
		Log.d("TEST", clickedButton.getText().toString());
		
		Intent intent = new Intent(context, EButtonsActivity.class);
		intent.putExtra("value", clickedButton.getText().toString());
		context.startActivity(intent);
	}
}
