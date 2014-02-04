package com.lsv.esupplements;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

/* Своя кнопка, добавлен хранитель цвета, 
так как не нашел в гугле как получить бекграунд нажатой кнопки
*/ 
public class SimpleButton extends Button {
	
	private int keepedColor;
	
	public SimpleButton(Context context) {
		super(context);
		keepedColor = Color.BLACK;
	}

	public int getKeepedColor() {
		return keepedColor;
	}

	public void setKeepedColor(int keepedColor) {
		this.keepedColor = keepedColor;
	}

}
