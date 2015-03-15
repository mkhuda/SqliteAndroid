package com.mk.mkedit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "data_pacar";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAMA = "nama_anda";
	public static final String COLUMN_PACAR = "pacar_anda";
	public static final String COLUMN_ALAMAT = "alamat_pacat";
	private static final String db_name = "pacar.db";
	private static final int db_version = 1;

	private static final String db_create = "create table " + TABLE_NAME + "("
			+ COLUMN_ID + " integer primary key autoincrement, " + COLUMN_NAMA
			+ " varchar(50) not null, " + COLUMN_PACAR
			+ " varchar(50) not null, " + COLUMN_ALAMAT
			+ " varchar(50) not null);";

	public DatabaseHelper(Context context) {
		super(context, db_name, null, db_version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(db_create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		// TODO Auto-generated method stub
		Log.w(DatabaseHelper.class.getName(),
				"Mengupgrade database dari versi " + oldVer + " Ke versi "
						+ newVer);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}
