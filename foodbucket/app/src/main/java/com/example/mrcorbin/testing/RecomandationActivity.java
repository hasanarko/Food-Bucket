package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecomandationActivity extends AppCompatActivity {


    private ListView recomandList;
    List<Restaurants> listRecomand;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomandation);



        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /// database connection
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        recomandList = findViewById(R.id.recomandation_list_id);
        listRecomand = databaseHelper.getRestListData();
        recomandList.setAdapter(new CustomRecomandationAdapter(listRecomand,this));

        recomandList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(RecomandationActivity.this,BookActivity.class);
                // Intent intent = new Intent(mContext,BookActivity.class);
                intent.putExtra("res_id",listRecomand.get(position).getRestId());
                intent.putExtra("Title",listRecomand.get(position).getRestName());
                intent.putExtra("Email",listRecomand.get(position).getRestEmail());
                intent.putExtra("Details",listRecomand.get(position).getRestAddress());
                intent.putExtra("thumbnail",listRecomand.get(position).getRestImage());
                intent.putExtra("phoneNumber",listRecomand.get(position).getRestPhone());
                intent.putExtra("restStatus",listRecomand.get(position).getRestStatus());
                intent.putExtra("openingTime",listRecomand.get(position).getRestOpeningTime());
                intent.putExtra("closingTime",listRecomand.get(position).getRestClosingTime());
                intent.putExtra("Badge",listRecomand.get(position).getRestRating());
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
