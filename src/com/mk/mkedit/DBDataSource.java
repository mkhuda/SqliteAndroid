package com.mk.mkedit;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBDataSource {

	private SQLiteDatabase database;

	private DatabaseHelper dbHelper;

	private String[] allColumns = { DatabaseHelper.COLUMN_ID,
			DatabaseHelper.COLUMN_NAMA, DatabaseHelper.COLUMN_PACAR,
			DatabaseHelper.COLUMN_ALAMAT };

	public DBDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		database.close();
	}

	public Pacar createPacar(String nama, String pacar, String alamat) {

		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_NAMA, nama);
		values.put(DatabaseHelper.COLUMN_PACAR, pacar);
		values.put(DatabaseHelper.COLUMN_ALAMAT, alamat);

		long insertId = database
				.insert(DatabaseHelper.TABLE_NAME, null, values);

		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, allColumns,
				DatabaseHelper.COLUMN_ID + " = " + insertId, null, null, null,
				null);

		cursor.moveToFirst();

		Pacar newPacar = cursorToPacar(cursor);

		cursor.close();
		return newPacar;
	}

	private Pacar cursorToPacar(Cursor cursor) {
		// TODO Auto-generated method stub
		Pacar pacaran = new Pacar();
		Log.v("info", "The getLong " + cursor.getInt(0));
		Log.v("info",
				"The getIis " + cursor.getString(1) + ", "
						+ cursor.getString(2));

		pacaran.setId(cursor.getLong(0));
		pacaran.set_namaAnda(cursor.getString(1));
		pacaran.set_pacarAnda(cursor.getString(2));
		pacaran.set_alamatPacar(cursor.getString(3));

		return pacaran;
	}

	public List<Pacar> getAllPacar() {
		List<Pacar> daftarPacar = new ArrayList<Pacar>();

		// select all SQL query
		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, allColumns,
				null, null, null, null, null);

		// pindah ke data paling pertama
		cursor.moveToFirst();
		// jika masih ada data, masukkan data barang ke
		// daftar barang
		while (!cursor.isAfterLast()) {
			Pacar pacaran = cursorToPacar(cursor);
			daftarPacar.add(pacaran);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return daftarPacar;
	}

	public Pacar getPacar(long id) {
		Pacar pacarr = new Pacar(); // inisialisasi barang
		// select query
		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, allColumns,
				DatabaseHelper.COLUMN_ID + " = " + id, null, null, null, null);
		// ambil data yang pertama
		cursor.moveToFirst();
		// masukkan data cursor ke objek barang
		pacarr = cursorToPacar(cursor);
		// tutup sambungan
		cursor.close();
		// return barang
		return pacarr;
	}

	public void updatePacar(Pacar p) {
		// ambil id barang
		String strFilter = "_id=" + p.getId();
		// memasukkan ke content values
		ContentValues args = new ContentValues();
		// masukkan data sesuai dengan kolom pada database
		args.put(DatabaseHelper.COLUMN_NAMA, p.get_namaAnda());
		args.put(DatabaseHelper.COLUMN_PACAR, p.get_pacarAnda());
		args.put(DatabaseHelper.COLUMN_ALAMAT, p.get_alamatPacar());
		// update query
		database.update(DatabaseHelper.TABLE_NAME, args, strFilter, null);
	}

	public void deletePacar(long id) {
		String stringId = "_id=" + id;
		database.delete(DatabaseHelper.TABLE_NAME, stringId, null);
	}

}
