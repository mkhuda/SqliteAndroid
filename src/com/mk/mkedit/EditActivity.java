package com.mk.mkedit;

import com.mk.mkedit.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends ActionBarActivity implements OnClickListener {

	Context context;
	private DBDataSource dataSource;
	long idEdit, idku;
	EditText nama, pacar, alamat;
	Button save;
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_activity);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		context = this;
		nama = (EditText) findViewById(R.id.namaEdit);
		pacar = (EditText) findViewById(R.id.pacarEdit);
		alamat = (EditText) findViewById(R.id.alamatEdit);
		save = (Button) findViewById(R.id.save);

		Bundle bun = this.getIntent().getExtras();
		idEdit = bun.getLong("idEditKu");

		dataSource = new DBDataSource(this);
		dataSource.open();
		Pacar gete = dataSource.getPacar(idEdit);
		idku = gete.getId();
		nama.setText(gete.get_namaAnda().toString());
		pacar.setText(gete.get_pacarAnda().toString());
		alamat.setText(gete.get_alamatPacar().toString());

		save.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		v.setEnabled(false);
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			@Override
			protected void onPreExecute() {
				pd = new ProgressDialog(context);
				pd.setTitle("Processing...");
				pd.setMessage("Please wait.");
				pd.setCancelable(false);
				pd.setIndeterminate(true);
				pd.show();
			}

			@Override
			protected Void doInBackground(Void... arg0) {
				try {
					// Do something...
					Pacar pacarku = new Pacar();
					pacarku.set_namaAnda(nama.getText().toString());
					pacarku.set_pacarAnda(pacar.getText().toString());
					pacarku.set_alamatPacar(alamat.getText().toString());
					pacarku.setId(idku);
					dataSource.updatePacar(pacarku);

					// Thread.sleep(5000);
				} catch (SQLiteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				if (pd != null) {
					pd.dismiss();
					save.setEnabled(true);
					Toast.makeText(context, "Update Berhasil",
							Toast.LENGTH_LONG).show();
					Intent i = new Intent(EditActivity.this, MainActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
					finish();
				}
			}

		};
		task.execute((Void[]) null);
	}

	@Override
	protected void onDestroy() {
		if (pd != null) {
			pd.dismiss();
			save.setEnabled(true);
		}
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
