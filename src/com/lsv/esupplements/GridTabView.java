package com.lsv.esupplements;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

public class GridTabView extends View implements View.OnClickListener{

	private Context context;
	private String[] list;
	private GridView container;
	private ArrayList<SimpleButton> buttons; 
	
	public GridTabView(Context context, String[] list) {
		super(context);
		
		this.context = context;
		this.list = list;
		container = new GridView(context);
		container.setNumColumns(2);
		container.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		buttons = new ArrayList<SimpleButton>();
		
		for (int i = 0; i < list.length; i++) {
			buttons.add(createButton(i));
		}
		ColorKeeper.setI(0);
		for (int i = 0; i < buttons.size(); i++) {
			changeButtonParams(buttons.get(i));
			container.addView(buttons.get(i));
		}
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
		tempButton.setOnTouchListener(new ButtonTouchListener(tempButton));
		tempButton.setOnClickListener(this);
		return tempButton;
	}
	
	private void changeButtonParams(Button button) {
		LinearLayout.LayoutParams buttonsParams = 
				new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT, 
						LinearLayout.LayoutParams.MATCH_PARENT, 
						1.0f);
		
		// TODO font

		//button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		buttonsParams.weight = 1f;
		buttonsParams.setMargins(5, 5, 5, 5);
		button.setLayoutParams(buttonsParams);
		
		button.setPadding(0, 0, 0, 0);
		button.setMaxHeight(0);
		button.setMaxWidth(0);
	}
	
	@Override
	public void onClick(View v) {
		Button clickedButton = (Button) v;
		Log.d("TEST", clickedButton.getText().toString());
		
		Intent intent = new Intent(context, EButtonsActivity.class);
		intent.putExtra("value", clickedButton.getText().toString());

		context.startActivity(intent);
	}

	
	public GridView getContainer() {
		return this.container;
	} 
}
