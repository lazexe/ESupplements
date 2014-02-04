package com.lsv.esupplements;

import java.util.ArrayList;

import android.graphics.Color;
import android.widget.GridLayout;

// Цвета которые используются при прорисовке кнопок
public class ColorKeeper {

	private static int I = 0;

	private static final String[] colors = { "#C10116", "#1A8CA7", "#633784",
			"#3B932D", "#C1547E", "#1672BB", "#ED7A1F", "#009581", "#373936",
			"#FFF301", };

	public static String[] getColors() {
		return colors;
	}

	public static int getI() {
		return I;
	}

	public static void setI(int i) {
		I = i;
	}

	public static String getColor() {
		if (I == colors.length)
			I = 0;
		return colors[I++];
	}

}
