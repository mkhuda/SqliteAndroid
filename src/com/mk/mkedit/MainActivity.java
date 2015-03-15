package com.mk.mkedit;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

	private DBDataSource dataSource;
	List<Pacar> nilai;

	ListView gv;
	ListPacarAdapter adapter;
	ArrayList<Pacar> pacarArr = new ArrayList<Pacar>();
	TextView teksNama;
	SearchView mSearchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		ListView gv = (ListView) findViewById(R.id.list);

		dataSource = new DBDataSource(this);
		dataSource.open();
		nilai = dataSource.getAllPacar();

		for (Pacar pcr : nilai) {
			pacarArr.add(pcr);
		}

		adapter = new ListPacarAdapter(this, R.layout.list_design, pacarArr);
		gv.setAdapter(adapter);

		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View myview,
					int position, long myId) {
				// TODO Auto-generated method stub
				// Mengumpulkan masing-masing teksView
				ListView lv = (ListView) findViewById(R.id.list);
				Pacar pcp = (Pacar) lv.getAdapter().getItem(position);

				long idKu = pcp.getId();

				TextView teksNama = (TextView) myview
						.findViewById(R.id.textnama);
				String namaku = teksNama.getText().toString();

				// Set posisi dimulai dari 1 bukan 0
				/*
				 * position = position + 1; long p =
				 * gv.getAdapter().getItemId(position);
				 * 
				 * // Set dataSource sesuai posisi Pacar c =
				 * dataSource.getPacar(position); String nama =
				 * c.get_namaAnda();
				 */

				Intent intentKu = new Intent(MainActivity.this,
						DetailActivity.class);
				Bundle bun = new Bundle();

				bun.putLong("ID", idKu);

				intentKu.putExtras(bun);
				startActivity(intentKu);
			}

		});

		gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ListView lv = (ListView) findViewById(R.id.list);
				Pacar Deletepcp = (Pacar) lv.getAdapter().getItem(arg2);

				final long DeleteidKu = Deletepcp.getId();
				final String DeletenamaKu = Deletepcp.get_namaAnda();
				final CharSequence[] items = { "View", "Delete" };
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						// Toast.makeText(getApplicationContext(), items[item],
						// Toast.LENGTH_SHORT).show();
						switch (item) {
						case 0:
							Toast.makeText(getApplicationContext(),
									"View " + DeleteidKu + " " + DeletenamaKu,
									Toast.LENGTH_SHORT).show();
							break;
						case 1:
							dataSource.deletePacar(DeleteidKu);
							Toast.makeText(getApplicationContext(),
									"Delete " + DeletenamaKu + " Sukses",
									Toast.LENGTH_LONG).show();
							finish();
							startActivity(getIntent());
							break;
						}
					}
				});
				AlertDialog alert = builder.create();
				alert.setTitle(DeletenamaKu);
				alert.show();
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bars, menu);
		
		MenuItem searchItem = menu.findItem(R.id.action_search);
		mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		mSearchView.setOnQueryTextListener(this);

		// return super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.action_add:
			Intent i = new Intent(MainActivity.this, AddActivity.class);
			startActivity(i);
			break;
		case R.id.action_search:
			mSearchView.setIconified(false);
			return true;
		}
		//return super.onOptionsItemSelected(item);
		return false;
	}
	
	@Override
    public boolean onQueryTextSubmit(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        return true;
    }
 
    @Override
    public boolean onQueryTextChange(String s) {
    	Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        return true;
    }

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		MainActivity.this.finish();
	}

}
