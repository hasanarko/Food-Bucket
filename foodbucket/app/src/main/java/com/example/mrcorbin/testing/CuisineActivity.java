package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.List;

public class CuisineActivity extends AppCompatActivity {

    private ListView listView;
    private List<Restaurants> restDetails;
    DatabaseHelper databaseHelper;
    String cuisineName;
    //private int[] res_pic = {R.drawable.img_one, R.drawable.img_two, R.drawable.img_three, R.drawable.img_four, R.drawable.img_five, R.drawable.img_six, R.drawable.img_one, R.drawable.img_two};

//    int [] res_pic={R.drawable.american,R.drawable.appitizer,R.drawable.bakery,R.drawable.bangali_food,
//            R.drawable.bangladeshi_food,R.drawable.salad,R.drawable.bbq,R.drawable.beef,
//            R.drawable.bevarage,R.drawable.biriany,R.drawable.burger,R.drawable.cafe,
//            R.drawable.chinese,R.drawable.chicken,R.drawable.chowmin,R.drawable.thai,
//            R.drawable.desert,R.drawable.dosa,R.drawable.fast_food,R.drawable.grill,
//            R.drawable.ice_cream,R.drawable.indian,R.drawable.italian,R.drawable.japanese,
//            R.drawable.kebab,R.drawable.koream,R.drawable.maxican,R.drawable.misti,
//            R.drawable.mughal,R.drawable.noodles,R.drawable.oriental,R.drawable.pizza,
//            R.drawable.roll,R.drawable.continental,R.drawable.sea_food,R.drawable.snacks,
//            R.drawable.soup,R.drawable.tea};;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        cuisineName = getIntent().getExtras().getString("cuisine_name");

        //Cursor cursorFind = databaseHelper.findRestaurantUnderSelectedCuisine(cuisineName);

        restDetails= databaseHelper.findRestaurantUnderSelectedCuisine(cuisineName);

//        while(cursorFind.moveToNext()){
//            String r_name = cursorFind.getString(0);
//            res_name.add(r_name);
//        }




        // attempt to hide scrollbar in cuisine list
        ScrollView sView = (ScrollView)findViewById(R.id.scrollviewId);
// Hide the Scollbar
        sView.setVerticalScrollBarEnabled(false);
        sView.setHorizontalScrollBarEnabled(false);

        // store restaurant name and cuisinelist
       // res_name = getResources().getStringArray(R.array.rest_name);
        listView = findViewById(R.id.cuisine_list_id);

        CuisineListAdapter adapter = new CuisineListAdapter(restDetails, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //String restaurant_name = res_name[position];
                //int image = res_pic[position];

                //Toast.makeText(CuisineActivity.this, value,Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(MainActivity.this, Country.class);
                //intent.putExtra("tag", value);
                //startActivity(intent);

                Intent intent = new Intent(CuisineActivity.this,BookActivity.class);
               // Intent intent = new Intent(mContext,BookActivity.class);
                intent.putExtra("res_id",restDetails.get(position).getRestId());
                intent.putExtra("Title",restDetails.get(position).getRestName());
                intent.putExtra("Email",restDetails.get(position).getRestEmail());
                intent.putExtra("Details",restDetails.get(position).getRestAddress());
                intent.putExtra("thumbnail",restDetails.get(position).getRestImage());
                intent.putExtra("phoneNumber",restDetails.get(position).getRestPhone());
                intent.putExtra("restStatus",restDetails.get(position).getRestStatus());
                intent.putExtra("openingTime",restDetails.get(position).getRestOpeningTime());
                intent.putExtra("closingTime",restDetails.get(position).getRestClosingTime());
                intent.putExtra("Badge",restDetails.get(position).getRestRating());
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
