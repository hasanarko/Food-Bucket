package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ExploreCuisines extends AppCompatActivity {



    ListView selectedCuisineView;
    DatabaseHelper databaseHelper;
    List<MenuItem> menuItem;
    int res_id;
    //int cuisineId;
    int cuisineId;
    TextView cuisine;
    Button btnSeletedCuisineDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_cuisines);

        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();


        selectedCuisineView = findViewById(R.id.explore_cuisines);

        res_id = getIntent().getExtras().getInt("rest_id");
        menuItem = databaseHelper.cuisineList(res_id);
        selectedCuisineView.setAdapter(new CustomMenuAdapter(this, menuItem));

        selectedCuisineView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View dialougeView, int position, long id) {

                Intent intent = new Intent(ExploreCuisines.this,EditFoodItemActivity.class);
                cuisineId = menuItem.get(position).getCuisineId();
                intent.putExtra("cuisine_id", cuisineId);

                startActivity(intent);


            }
        });

        selectedCuisineView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View dialougeView, int position, long id) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(ExploreCuisines.this);
                dialougeView = getLayoutInflater().inflate(R.layout.seleted_cuisine_dialouge,null);


                cuisine = dialougeView.findViewById(R.id.c_id);
                cuisine.setText(String.valueOf(menuItem.get(position).getCuisineId()));
                cuisine.setVisibility(View.INVISIBLE);


                btnSeletedCuisineDelete = dialougeView.findViewById(R.id.delete_selected_cuisine_id);
                btnSeletedCuisineDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        int deleteRow = databaseHelper.deleteCuisine(cuisine.getText().toString());
                        int deleteFood = databaseHelper.deleteFoodItemWithSelectedCuisine(String.valueOf(cuisineId));
                        if(deleteRow<0 && deleteFood<0){
                            Toast.makeText(ExploreCuisines.this, "value not deleted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ExploreCuisines.this, "value deleted", Toast.LENGTH_SHORT).show();

                            // Intent intent=new Intent(ExploreCuisines.this,ExploreCuisines.class);
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }

                    }
                });

                builder.setView(dialougeView);
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
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
