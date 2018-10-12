package com.example.mrcorbin.testing;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {



    private List<SearchItem> searchingList;

    DatabaseHelper databaseHelper;
    Cursor cuisineResult;
    Cursor restResult;
    Cursor foodResult;
    String cuisineType;
    String foodType;
    int check=0;
    String value;
    AutoCompleteTextView editText;
    RadioGroup radioGroup;
    RadioButton checkBox1,checkBox2,checkBox3;
    String searchingTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        fillCountryList();

         editText = findViewById(R.id.actv);
         value = editText.getText().toString();

        AutoCompleteSearchingAdapter adapter = new AutoCompleteSearchingAdapter(this, searchingList);
        editText.setAdapter(adapter);

        // checking priority
        checkBox1 = findViewById(R.id.one);
        checkBox2 = findViewById(R.id.two);
        checkBox3 = findViewById(R.id.three);

        checkBox3.setChecked(true);
        searchingTypes =checkBox3.getText().toString();

        RadioGroup rb =  findViewById(R.id.filter);
        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.one){
                    //Toast.makeText(SearchActivity.this, checkBox1.getText().toString(), Toast.LENGTH_SHORT).show();
                    searchingTypes = checkBox1.getText().toString();
                }else if(checkedId == R.id.two){
                    searchingTypes = checkBox2.getText().toString();
                }else if(checkedId == R.id.three){
                    searchingTypes = checkBox3.getText().toString();
                }
            }

        });

        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SearchActivity.this,CuisineResultActivity.class);
                intent.putExtra("type",searchingList.get(position).getType());
                intent.putExtra("name",searchingList.get(position).getCountryName());
                intent.putExtra("search_type",searchingTypes);
                startActivity(intent);

            }
        });






    }

    private void fillCountryList() {
        searchingList = new ArrayList<>();


        cuisineResult = databaseHelper.showCuisine();
        restResult = databaseHelper.showRest();
        foodResult = databaseHelper.showFood(value);


        while(cuisineResult.moveToNext()){
            searchingList.add(new SearchItem(cuisineResult.getString(0),R.drawable.search,cuisineResult.getString(1)));

        }


        while(restResult.moveToNext()){
            searchingList.add(new SearchItem(restResult.getString(0),R.drawable.search,restResult.getString(1)));

        }


        while(foodResult.moveToNext()){
            searchingList.add(new SearchItem(foodResult.getString(0),R.drawable.search,foodResult.getString(1)));

        }

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
