package com.lsv.esupplements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

	private static final String DB_PATH = "/data/data/com.lsv.esupplements/databases/";

	private static final String DB_NAME = "esupplements_db";

	private SQLiteDatabase myDataBase;

	private Context myContext;

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			copyDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void createDatabase() throws IOException {
		boolean dbExist = checkDataBase();

		if (dbExist) {
			Log.d("DATABASE", "DataBase exist");
		} else {
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				Log.d("ERROR DATABASE", e.getMessage());
				throw new Error("Error copying database");
			}
		}
	}

	private boolean checkDataBase() {
		File dbFile = new File(DB_PATH + DB_NAME);
		return dbFile.exists();
	}

	private void copyDataBase() throws IOException {
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		String outFileName = DB_PATH + DB_NAME;

		OutputStream myOutput = new FileOutputStream(outFileName);

		byte[] buffer = new byte[1024];
		int length;

		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public void openDataBase() throws SQLException {
		String myPath = DB_PATH + DB_NAME;
		setMyDataBase(SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY));
	}

	public SQLiteDatabase getMyDataBase() {
		return myDataBase;
	}

	public void setMyDataBase(SQLiteDatabase myDataBase) {
		this.myDataBase = myDataBase;
	}

	public DataBaseHelper getDataBaseHelper() {
		return this;
	}

	@Override
	public synchronized void close() {
		if (getMyDataBase() != null) {
			getMyDataBase().close();
		}
		super.close();
	}
}
