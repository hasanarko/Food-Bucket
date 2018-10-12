package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ShowAllCuisines extends AppCompatActivity {


    private ListView showAllCuisines;
    DatabaseHelper databaseHelper;
    List<MenuItem> cuisines;
    List<String> count;
    List<Cusine> cursorLoadCusineNames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_cuisines);

        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();


        showAllCuisines = findViewById(R.id.show_all_cusines);

        cursorLoadCusineNames = databaseHelper.loadCuisine();
        cuisines = databaseHelper.loadAllCuisine();



//        count = databaseHelper.totalRestUnderSingleCuisine();

        showAllCuisines.setAdapter(new CustomCuisineAdapter(this,cuisines));

        showAllCuisines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowAllCuisines.this,TotalRestListActivity.class);
                intent.putExtra("cuisine_name",cuisines.get(position).getCuisineName());

                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);


    }
}
