package com.mk.mkedit;

import com.mk.mkedit.R;

//
//import android.annotation.SuppressLint;
//import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.app.ActionBarActivity;

public class DetailActivity extends ActionBarActivity {

	private DBDataSource dataSource;
	TextView teksNama, teksPacar, teksAlamat;
	long idku;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_design);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		teksNama = (TextView) findViewById(R.id.setTeksNama);
		teksPacar = (TextView) findViewById(R.id.setTeks2);
		teksAlamat = (TextView) findViewById(R.id.setTeks3);

		Bundle bun = this.getIntent().getExtras();
		long id = bun.getLong("ID");

		dataSource = new DBDataSource(this);
		dataSource.open();

		Pacar getp = dataSource.getPacar(id);
		idku = getp.getId();
		String namaku = getp.get_namaAnda();
		String pacarku = getp.get_pacarAnda();
		String alamatku = getp.get_alamatPacar();

		getSupportActionBar().setTitle(namaku);
		teksNama.setText(namaku);
		teksPacar.setText(pacarku);
		teksAlamat.setText(alamatku);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.detail_bars, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_edit:
			Intent i = new Intent(DetailActivity.this, EditActivity.class);
			Bundle bun = new Bundle();

			bun.putLong("idEditKu", idku);

			i.putExtras(bun);
			startActivity(i);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
