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

public class NewlyOpenedActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView newlyOpeneList;
    List<Restaurants> listNewlyOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newly_opened);


        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /// database connection
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        listNewlyOpened= databaseHelper.getNewlyOpenedListData();


        newlyOpeneList = findViewById(R.id.newly_opened_list_id);
        newlyOpeneList.setAdapter(new CustomNewlyOpenedAdapter(listNewlyOpened,this));
        newlyOpeneList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewlyOpenedActivity.this,BookActivity.class);
                // Intent intent = new Intent(mContext,BookActivity.class);
                intent.putExtra("res_id",listNewlyOpened.get(position).getRestId());
                intent.putExtra("Title",listNewlyOpened.get(position).getRestName());
                intent.putExtra("Email",listNewlyOpened.get(position).getRestEmail());
                intent.putExtra("Details",listNewlyOpened.get(position).getRestAddress());
                intent.putExtra("thumbnail",listNewlyOpened.get(position).getRestImage());
                intent.putExtra("phoneNumber",listNewlyOpened.get(position).getRestPhone());
                intent.putExtra("restStatus",listNewlyOpened.get(position).getRestStatus());
                intent.putExtra("openingTime",listNewlyOpened.get(position).getRestOpeningTime());
                intent.putExtra("closingTime",listNewlyOpened.get(position).getRestClosingTime());
                intent.putExtra("Badge",String.valueOf(listNewlyOpened.get(position).getRestRating()));
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
