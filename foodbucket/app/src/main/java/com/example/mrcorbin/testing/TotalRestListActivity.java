package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class TotalRestListActivity extends AppCompatActivity {


    DatabaseHelper databaseHelper;
    List<Restaurants> restList;
    private ListView totalRestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_rest_list);


        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String cuisineName = getIntent().getExtras().getString("cuisine_name");

        //database connection
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        restList = databaseHelper.findRestUnderCuisine(cuisineName);
        totalRestList = findViewById(R.id.total_rest_list);
        totalRestList.setAdapter(new CustomTotalListAdapter(this,restList));

        totalRestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Intent intent = new Intent(TotalRestListActivity.this,BookActivity.class);

               intent.putExtra("res_id",restList.get(position).getRestId());
               intent.putExtra("Title",restList.get(position).getRestName());
               intent.putExtra("Email",restList.get(position).getRestEmail());
               intent.putExtra("Details",restList.get(position).getRestAddress());
               intent.putExtra("thumbnail",restList.get(position).getRestImage());
               intent.putExtra("phoneNumber",restList.get(position).getRestPhone());
               intent.putExtra("restStatus",restList.get(position).getRestStatus());
               intent.putExtra("openingTime",restList.get(position).getRestOpeningTime());
               intent.putExtra("closingTime",restList.get(position).getRestClosingTime());
               intent.putExtra("Badge",restList.get(position).getRestRating());


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
