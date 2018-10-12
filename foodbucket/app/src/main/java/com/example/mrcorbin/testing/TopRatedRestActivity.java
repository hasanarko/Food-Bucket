package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class TopRatedRestActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView topRatedList;
    List<Restaurants> listTopRated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated_rest);


        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /// database connection
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();



        listTopRated = databaseHelper.getTopRatedListData();

        topRatedList = findViewById(R.id.top_rated_list_id);
        topRatedList.setAdapter(new CustomTopRatedAdapter(listTopRated,this));

        topRatedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopRatedRestActivity.this,BookActivity.class);
                // Intent intent = new Intent(mContext,BookActivity.class);
                intent.putExtra("res_id",listTopRated.get(position).getRestId());
                intent.putExtra("Title",listTopRated.get(position).getRestName());
                intent.putExtra("Email",listTopRated.get(position).getRestEmail());
                intent.putExtra("Details",listTopRated.get(position).getRestAddress());
                intent.putExtra("thumbnail",listTopRated.get(position).getRestImage());
                intent.putExtra("phoneNumber",listTopRated.get(position).getRestPhone());
                intent.putExtra("restStatus",listTopRated.get(position).getRestStatus());
                intent.putExtra("openingTime",listTopRated.get(position).getRestOpeningTime());
                intent.putExtra("closingTime",listTopRated.get(position).getRestClosingTime());
                intent.putExtra("Badge",listTopRated.get(position).getRestRating());
                //intent.putExtra("thumbnail",image);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);


    }
}
