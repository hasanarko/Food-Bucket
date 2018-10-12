package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CuisineResultActivity extends AppCompatActivity {


    /*
    *

        <TextView
        android:id="@+id/id"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />

    *
    * */

    String name;
    String type;
    ListView searchList;
    List<Restaurants> listForCuisine;
    List<Restaurants> listForFood;
    List<Restaurants> listForRestaurants;
    DatabaseHelper databaseHelper;
    Cursor cursorFood;

    //cuisines
    String crestId="";
    String crestName="";
    String crestEmail="";
    String crestPassword="";
    String crestOwnerType="";
    int crestPhoneNumber=0;
    String crestAddress="";
    String crestAccStatus="";
    byte [] crestRestImage={};
    double crestRating=0.0;
    String crestStatus="";
    String crestOpeningTime="";
    String crestClosingTime="";
    String ctype="";

    //food
    String frestId="";
    String frestName="";
    String frestEmail="";
    String frestPassword="";
    String frestOwnerType="";
    int frestPhoneNumber=0;
    String frestAddress="";
    String frestAccStatus="";
    byte [] frestRestImage={};
    double frestRating=0.0;
    String frestStatus="";
    String frestOpeningTime="";
    String frestClosingTime="";
    String ftype="";
    String foodPrice = "";

    //restaurants
    String restId="";
    String restName="";
    String restEmail="";
    String restPassword="";
    String restOwnerType="";
    int restPhoneNumber=0;
    String restAddress="";
    String restAccStatus="";
    byte [] restRestImage={};
    double restRating=0.0;
    String restStatus="";
    String restOpeningTime="";
    String restClosingTime="";
    String rtype="";




    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine_result);

        searchList = findViewById(R.id.search_id);
        // database
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        //tv =findViewById(R.id.id);


        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






//        Bundle bundle = getIntent().getExtras();
        name = getIntent().getExtras().getString("name");
        type = getIntent().getExtras().getString("type");
        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

//        if(bundle != null){
//            name = bundle.getString("name");
//            type = bundle.getString("type");
//        }

//         listForCuisine = new ArrayList<>();
//         listForFood = new ArrayList<>();
//         listForRestaurants = new ArrayList<>();

        if(type.equals("cuisine")){

           // Toast.makeText(this, "cuisine", Toast.LENGTH_SHORT).show();
            //tv.setText(name);
            //listForCuisine = new ArrayList<>();
            listForCuisine = databaseHelper.cuisineListForSearching(name);
            //Toast.makeText(this, listForCuisine.toString(), Toast.LENGTH_SHORT).show();
            searchList.setAdapter(new SearchViewAdapter(this,listForCuisine,type));
            searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {



                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int rId=listForCuisine.get(position).getRestId();
                    Cursor cursorRest = databaseHelper.searchRest(rId);


                    while(cursorRest.moveToNext()){
                        crestId=cursorRest.getString(0);
                        crestName=cursorRest.getString(1);
                        crestEmail=cursorRest.getString(2);
                        crestPassword=cursorRest.getString(3);
                        crestOwnerType=cursorRest.getString(4);
                        crestPhoneNumber=cursorRest.getInt(5);
                        crestAddress=cursorRest.getString(6);
                        crestAccStatus=cursorRest.getString(7);
                        crestRestImage=cursorRest.getBlob(8);
                        crestRating=cursorRest.getDouble(9);
                        crestStatus=cursorRest.getString(10);
                        crestOpeningTime=cursorRest.getString(11);
                        crestClosingTime=cursorRest.getString(12);
                        ctype=cursorRest.getString(13);
                    }


                    Intent intent = new Intent(CuisineResultActivity.this,BookActivity.class);

                    intent.putExtra("res_id",rId);
                    intent.putExtra("Title",crestName);
                    intent.putExtra("Details",crestAddress);
                    intent.putExtra("thumbnail",crestRestImage);
                    intent.putExtra("phoneNumber",crestPhoneNumber);
                    intent.putExtra("restStatus",crestStatus);
                    intent.putExtra("openingTime",crestOpeningTime);
                    intent.putExtra("closingTime",crestClosingTime);
                    intent.putExtra("Badge",crestRating);
                    startActivity(intent);
                }
            });

        }
        else if(type.equals(" food")){
            //Toast.makeText(this, "food", Toast.LENGTH_SHORT).show();

           // tv.setText(name);

            String searchType = getIntent().getExtras().getString("search_type");

            if(searchType.equals("high to low cost")){
                listForFood = databaseHelper.foodListForHighToLowSearching(name);
            }else if(searchType.equals("low to high cost")){
                listForFood = databaseHelper.foodListForLowToHighSearching(name);
            }else if(searchType.equals("popular restaurants")){
                listForFood = databaseHelper.foodListForPopularSearching(name);
            }


            searchList.setAdapter(new SearchViewAdapter(this,listForFood,type));

            searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    int rId=listForFood.get(position).getRestId();
                    Cursor cursorRest = databaseHelper.searchRest(rId);


                    while(cursorRest.moveToNext()){
                        frestId=cursorRest.getString(0);
                        frestName=cursorRest.getString(1);
                        frestEmail=cursorRest.getString(2);
                        frestPassword=cursorRest.getString(3);
                        frestOwnerType=cursorRest.getString(4);
                        frestPhoneNumber=cursorRest.getInt(5);
                        frestAddress=cursorRest.getString(6);
                        frestAccStatus=cursorRest.getString(7);
                        frestRestImage=cursorRest.getBlob(8);
                        frestRating=cursorRest.getDouble(9);
                        frestStatus=cursorRest.getString(10);
                        frestOpeningTime=cursorRest.getString(11);
                        frestClosingTime=cursorRest.getString(12);
                        ftype=cursorRest.getString(13);
                    }

                    cursorFood = databaseHelper.searchFood(name);

                    while(cursorFood.moveToNext()){
                        foodPrice = cursorFood.getString(2);
                    }



                    Intent intent = new Intent(CuisineResultActivity.this,BookActivity.class);
                    intent.putExtra("res_id",rId);
                    intent.putExtra("Title",frestName);
                    intent.putExtra("r_email",frestEmail);
                    intent.putExtra("r_pass",frestPassword);
                    intent.putExtra("r_ot",frestOwnerType);
                    intent.putExtra("phoneNumber",frestPhoneNumber);
                    intent.putExtra("Details",frestAddress);
                    intent.putExtra("r_accsta",frestAccStatus);
                    intent.putExtra("thumbnail",frestRestImage);
                    intent.putExtra("Badge",frestRating);
                    intent.putExtra("restStatus",frestStatus);
                    intent.putExtra("openingTime",frestOpeningTime);
                    intent.putExtra("closingTime",frestClosingTime);
                    intent.putExtra("r_type",ftype);
                    intent.putExtra("food_price",foodPrice);
                    startActivity(intent);
                }
            });





        }
        else if(type.equals("restaurant")){
            //Toast.makeText(this, "restaurants", Toast.LENGTH_SHORT).show();
            //tv.setText(name);

            listForRestaurants = databaseHelper.RestaurantListForSearching(name);
            searchList.setAdapter(new SearchViewAdapter(this,listForRestaurants,type));
            searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int rId=listForRestaurants.get(position).getRestId();
                    Cursor cursorRest = databaseHelper.searchRest(rId);


                    while(cursorRest.moveToNext()){
                        restId=cursorRest.getString(0);
                        restName=cursorRest.getString(1);
                        restEmail=cursorRest.getString(2);
                        restPassword=cursorRest.getString(3);
                        restOwnerType=cursorRest.getString(4);
                        restPhoneNumber=cursorRest.getInt(5);
                        restAddress=cursorRest.getString(6);
                        restAccStatus=cursorRest.getString(7);
                        restRestImage=cursorRest.getBlob(8);
                        restRating=cursorRest.getDouble(9);
                        restStatus=cursorRest.getString(10);
                        restOpeningTime=cursorRest.getString(11);
                        restClosingTime=cursorRest.getString(12);
                        rtype=cursorRest.getString(13);
                    }


                    Intent intent = new Intent(CuisineResultActivity.this,BookActivity.class);
                    intent.putExtra("res_id",rId);
                    intent.putExtra("Title",restName);
                    intent.putExtra("r_email",restEmail);
                    intent.putExtra("r_pass",restPassword);
                    intent.putExtra("r_ot",restOwnerType);
                    intent.putExtra("phoneNumber",restPhoneNumber);
                    intent.putExtra("Details",restAddress);
                    intent.putExtra("r_accsta",restAccStatus);
                    intent.putExtra("thumbnail",restRestImage);
                    intent.putExtra("Badge",restRating);
                    intent.putExtra("restStatus",restStatus);
                    intent.putExtra("openingTime",restOpeningTime);
                    intent.putExtra("closingTime",restClosingTime);
                    intent.putExtra("r_type",rtype);
                    startActivity(intent);
                }
            });

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
