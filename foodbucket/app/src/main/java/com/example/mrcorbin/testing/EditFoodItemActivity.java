package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static java.security.AccessController.getContext;

public class EditFoodItemActivity extends AppCompatActivity {


    DatabaseHelper databaseHelper;
    ListView foodListView;
    List<Food> food;

   // private int foodid;
    TextView foodId;

    private String name,details;
    private String price;

    int cuisine_id;

    TextInputLayout editFoodName,editFoodPrice,editFoodDetails;
    TextInputLayout editNewFoodName,editNewFoodPrice,editNewFoodDetails;



    TextInputEditText foodNameText;
    TextInputEditText foodPriceText;
    TextInputEditText foodDetailsText;

    Button btnUpdateFoodItem,btnDeleteFoodItem;
    Button btnAddFoodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food_item);

        // database
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // find view
        foodListView = findViewById(R.id.editFood_id);

        cuisine_id = getIntent().getExtras().getInt("cuisine_id");


        food = databaseHelper.getFoodList(cuisine_id);
        foodListView.setAdapter(new CustomFoodAdapter(this, food));



        //-------------------------add information about all food items --------------------

        editFoodName = findViewById(R.id.edit_foodname_id);
        editFoodPrice = findViewById(R.id.edit_food_price_id);
        editFoodDetails = findViewById(R.id.edit_food_details_id);

        btnAddFoodItem = findViewById(R.id.add_fooditem_id);

        btnAddFoodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String foodName = editFoodName.getEditText().getText().toString().trim();
                String foodPrice = editFoodPrice.getEditText().getText().toString().trim();
                String foodDetails = editFoodDetails.getEditText().getText().toString().trim();
                String type = " food";

                if(!foodName.isEmpty() && !foodPrice.isEmpty() && !foodDetails.isEmpty()){
                    double price = Double.parseDouble(foodPrice);

                    long rowFoodItem = databaseHelper.insertFoodItem(foodName,price,0.0,foodDetails,cuisine_id,type);

                    if(rowFoodItem == -1){
                        Toast.makeText(EditFoodItemActivity.this, "food item added unsuccessfull", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(EditFoodItemActivity.this, " food item added successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }

                }else{
                    Toast.makeText(EditFoodItemActivity.this, "all fields required", Toast.LENGTH_SHORT).show();
                }




            }
        });

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {



                //foodid = food.get(position).getFoodId();
                name=food.get(position).getFoodName();
                price= String.valueOf(food.get(position).getFoodPrice());
                details = food.get(position).getFoodDescription();





                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(EditFoodItemActivity.this);
                v = getLayoutInflater().inflate(R.layout.fooditem_dialouge,null);


                foodId = v.findViewById(R.id.food_id);
                foodId.setText(String.valueOf(food.get(position).getFoodId()));
                foodId.setVisibility(View.INVISIBLE);




                editNewFoodName = v.findViewById(R.id.update_foodname_id);
                editNewFoodPrice = v.findViewById(R.id.update_food_price_id);
                editNewFoodDetails = v.findViewById(R.id.update_food_details_id);


                foodNameText = v.findViewById(R.id.text_foodname);
                foodPriceText = v.findViewById(R.id.text_price);
                foodDetailsText = v.findViewById(R.id.text_details);

                foodNameText.setText(name);
                foodPriceText.setText(price);
                foodDetailsText.setText(details);


                //update food item
                btnUpdateFoodItem = v.findViewById(R.id.update_fooitem);
                btnUpdateFoodItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(EditFoodItemActivity.this, "hi", Toast.LENGTH_SHORT).show();


                        if(!foodNameText.getText().toString().trim().isEmpty() && !foodPriceText.getText().toString().trim().isEmpty() && !foodDetailsText.getText().toString().trim().isEmpty()){
                            boolean updateFoodItem = databaseHelper.updateFoodItem(foodId.getText().toString(),foodNameText.getText().toString(),
                                    foodPriceText.getText().toString(),
                                    foodDetailsText.getText().toString());

                            if(updateFoodItem == true){
                                Toast.makeText(EditFoodItemActivity.this, "food item updated", Toast.LENGTH_SHORT).show();
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);

                            }else{
                                Toast.makeText(EditFoodItemActivity.this, "food item not updated", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(EditFoodItemActivity.this, "all fields required", Toast.LENGTH_SHORT).show();
                        }


                    }
                });




                //delete food item
                btnDeleteFoodItem = v.findViewById(R.id.delete_fooditem);

                btnDeleteFoodItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        int deleteRow = databaseHelper.deleteFoodItem(foodId.getText().toString());
                        if(deleteRow<0){
                            Toast.makeText(EditFoodItemActivity.this, "fooditem not deleted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(EditFoodItemActivity.this, "fooditem deleted", Toast.LENGTH_SHORT).show();

                            //Intent intent=new Intent(EditCusineActivity.this,EditCusineActivity.class);
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    }
                });


                builder.setView(v);
                android.app.AlertDialog dialog = builder.create();
                dialog.show();


               // Toast.makeText(EditFoodItemActivity.this, "clicked", Toast.LENGTH_SHORT).show();
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
