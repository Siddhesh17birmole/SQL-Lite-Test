package com.example.sid.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class sqlite_DbActivity extends AppCompatActivity{

    private DB_Manager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[]
            { DB_Helper._ID,
                    DB_Helper.SUBJECT, DB_Helper.DESC };

    final int[] to = new int[]
            { R.id.idViewRecord,
                    R.id.titleViewRecord,
                    R.id.descViewRecord };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite__db);

        dbManager = new DB_Manager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        TextView txtVwEmpty = (TextView) findViewById(R.id.empty);
        listView.setEmptyView(txtVwEmpty);

        adapter = new SimpleCursorAdapter(this, R.layout.view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                TextView idTextView = (TextView) view.findViewById(R.id.idViewRecord);
                TextView titleTextView = (TextView) view.findViewById(R.id.titleViewRecord);
                TextView descTextView = (TextView) view.findViewById(R.id.descViewRecord);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), modify_countryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record_menu) {

            Intent add_mem = new Intent(this, Add_CountryActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }



    }

