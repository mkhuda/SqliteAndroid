package com.mk.mkedit;

import com.mk.mkedit.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends ActionBarActivity implements OnClickListener {

	Context context;
	private DBDataSource dataSource;
	long idEdit, idku;
	EditText namaAdd, pacarAdd, alamatAdd;
	Button saveAdd;
	ProgressDialog pdAdd;
	String namaGet, pacarGet, alamatGet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_activity);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		context = this;

		dataSource = new DBDataSource(this);
		dataSource.open();

		namaAdd = (EditText) findViewById(R.id.namaAdd);
		pacarAdd = (EditText) findViewById(R.id.pacarAdd);
		alamatAdd = (EditText) findViewById(R.id.alamatAdd);
		saveAdd = (Button) findViewById(R.id.saveAdd);

		saveAdd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		v.setEnabled(false);
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			@Override
			protected void onPreExecute() {
				pdAdd = new ProgressDialog(context);
				pdAdd.setTitle("Processing...");
				pdAdd.setMessage("Please wait.");
				pdAdd.setCancelable(false);
				pdAdd.setIndeterminate(true);
				pdAdd.show();
			}

			@Override
			protected Void doInBackground(Void... arg0) {
				try {
					// Do something...
					// Pacar pacaradd = null;
					namaGet = namaAdd.getText().toString();
					pacarGet = pacarAdd.getText().toString();
					alamatGet = alamatAdd.getText().toString();
					if (namaGet != null && pacarGet != null
							&& alamatGet != null) {
						dataSource.createPacar(namaGet, pacarGet, alamatGet);
					} else {
						Toast.makeText(context, "Lengkapi Form",
								Toast.LENGTH_LONG).show();
					}
					// Thread.sleep(5000);
				} catch (SQLiteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				if (pdAdd != null) {
					pdAdd.dismiss();
					saveAdd.setEnabled(true);
					Toast.makeText(context, "Data Berhasil Ditambahkan",
							Toast.LENGTH_LONG).show();
					Intent i = new Intent(AddActivity.this, MainActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
					finish();
				}
			}

		};
		task.execute((Void[]) null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

}
