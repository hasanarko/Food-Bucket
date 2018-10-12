package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView listView;
    List<MenuItem> menuItem;
    int res_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // find view id
        listView = findViewById(R.id.cuisineId);
        res_id = getIntent().getExtras().getInt("res_id");




        menuItem = databaseHelper.cuisineList(res_id);
        listView.setAdapter(new CustomMenuAdapter(this, menuItem));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   Toast.makeText(MenuActivity.this, res_id, Toast.LENGTH_SHORT).show();

                String name = menuItem.get(position).getCuisineName();
                int cuisineId = menuItem.get(position).getCuisineId();
                //int age = personList.get(position).getPerson_age();
                //String birth = personList.get(position).getPerson_birthDate();
                Intent intent = new Intent(MenuActivity.this, FoodItemActivity.class);
                intent.putExtra("cuisine_id", cuisineId);
                intent.putExtra("res_id",res_id);
                startActivity(intent);
                //Toast.makeText(MenuActivity.this, "Name : "+name, Toast.LENGTH_SHORT).show();
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
