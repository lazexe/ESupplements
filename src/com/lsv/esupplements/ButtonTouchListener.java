package com.lsv.esupplements;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

// Слушатель, меняет цвет кнопки во время ее нажатия и отпускания
public class ButtonTouchListener implements View.OnTouchListener {

	private final int buttonSourceColor;
	private final SimpleButton sourceButton;

	public ButtonTouchListener(SimpleButton button) {
		sourceButton = button;
		buttonSourceColor = sourceButton.getKeepedColor();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		/*
		int action = event.getAction();

		if (action == MotionEvent.ACTION_DOWN) {
			sourceButton.setBackgroundColor(Color.MAGENTA);
		}

		if (action == MotionEvent.ACTION_UP) {
			sourceButton.setBackgroundColor(buttonSourceColor);
		}
		*/
		return false;
	}
	
}
