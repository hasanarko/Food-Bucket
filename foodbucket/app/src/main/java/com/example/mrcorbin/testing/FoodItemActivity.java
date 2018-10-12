package com.example.mrcorbin.testing;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;

import java.util.List;

public class FoodItemActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView foodListView;
    List<Food> food;
    Button sennRating;
    int cuisine_id;
    int foodId;
    int restId;
    int foodieId;



    SharedPreferences sessionLogin;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);

        // session

        sessionLogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sessionLogin.edit();



        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // database
        databaseHelper = new DatabaseHelper(this);
        final SQLiteDatabase database = databaseHelper.getWritableDatabase();

        // find view
        foodListView = findViewById(R.id.foodListViewId);

        cuisine_id = getIntent().getExtras().getInt("cuisine_id");
        restId = getIntent().getExtras().getInt("res_id");
        foodieId = sessionLogin.getInt("foodies_id",0);

        food = databaseHelper.getFoodList(cuisine_id);
        foodListView.setAdapter(new CustomFoodAdapter(this, food));

        foodListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {



                return false;
            }
        });

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


                //alert dialouge
                final AlertDialog.Builder builder = new AlertDialog.Builder(FoodItemActivity.this);
                v = getLayoutInflater().inflate(R.layout.edit_food_rating,null);

                // write code
                foodId = food.get(position).getFoodId();


                SmileRating smileRating = (SmileRating) v.findViewById(R.id.food_smile_rating);

                smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                    @Override
                    public void onSmileySelected(int smiley, boolean reselected) {
                        // reselected is false when user selects different smiley that previously selected one
                        // true when the same smiley is selected.
                        // Except if it first time, then the value will be false.
                        switch (smiley) {
                            case SmileRating.BAD:
                               // Toast.makeText(FoodItemActivity.this, "Bad", Toast.LENGTH_SHORT).show();
                                break;
                            case SmileRating.GOOD:
                                //Toast.makeText(FoodItemActivity.this, "good", Toast.LENGTH_SHORT).show();
                                break;
                            case SmileRating.GREAT:
                               // Toast.makeText(FoodItemActivity.this, "great", Toast.LENGTH_SHORT).show();
                                break;
                            case SmileRating.OKAY:
                               // Toast.makeText(FoodItemActivity.this, "okay", Toast.LENGTH_SHORT).show();
                                break;
                            case SmileRating.TERRIBLE:
                                //Toast.makeText(FoodItemActivity.this, "Terrible", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });

                smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
                    @Override
                    public void onRatingSelected(int level, boolean reselected) {
                       // Toast.makeText(FoodItemActivity.this, "Selected Rating "+level, Toast.LENGTH_SHORT).show();
                        //updating rating for restaurant



                        if(foodieId != 0 ){



                            long insertFoodRating = databaseHelper.insertFoodRating(foodId,cuisine_id,restId,level);
                            if(insertFoodRating == -1){
                                Toast.makeText(FoodItemActivity.this, "food rating unsuccessfull", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(FoodItemActivity.this, "food rating successfull", Toast.LENGTH_SHORT).show();

                                boolean updateFoodRating = databaseHelper.updateRatingToFoodTable(restId,cuisine_id,foodId);
                                onRestart();
                            }
                        }
                        else{
                            Toast.makeText(FoodItemActivity.this, "foodies login required", Toast.LENGTH_SHORT).show();
                        }






                    }
                });






                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();




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

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
