package com.example.mrcorbin.testing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;

public class BookActivity extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvcategory,rating,tvPhoneNumber, restEmailView;
    private ImageView img;
    private Button callingToRestaurants,share,photos, review,editProfile, menuBtn,send;
    private String number;
    int restId;
    DatabaseHelper databaseHelper;
    Cursor cursorRes;
    int ratingNum=0;
    String foodieName="";
    int rating_id=0;
    Cursor cursorReview;
    int foodieid;
    String restEmail;
    Context context;


    //int f_id;

    private EditText sendReview;

    TextView restaurantStatus,restOpeningTime,restClosingTime;


    SharedPreferences sessionLogin;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        sendReview = findViewById(R.id.review_message_id);

        // session

        sessionLogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sessionLogin.edit();

        //f_id= sessionLogin.getInt("foodies_id",0);



        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        tvtitle = findViewById(R.id.title_id);
        tvPhoneNumber = findViewById(R.id.phonenumber_id);
        tvdescription = findViewById(R.id.restaurants_address);
        restaurantStatus = findViewById(R.id.status);
        rating = findViewById(R.id.restaurant_number_rating);
        restOpeningTime = findViewById(R.id.o);
        restClosingTime = findViewById(R.id.c);
        restEmailView = findViewById(R.id.restaurant_email_id);


//        tvdescription = findViewById(R.id.description_id);
//        tvcategory = findViewById(R.id.category_id);
        img = findViewById(R.id.bookthumbnail);

        //database connection
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        //Receive data
        Intent intent = getIntent();


        try{
            String title = intent.getExtras().getString("Title");
            restEmail = intent.getExtras().getString("Email");
            restId = intent.getExtras().getInt("res_id");
       String address = intent.getExtras().getString("Details");
            byte[] image = intent.getExtras().getByteArray("thumbnail");
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            Bitmap restImage = BitmapFactory.decodeStream(imageStream);
            double restaurant_rating=intent.getExtras().getDouble("Badge");
            int phoneNumber = intent.getExtras().getInt("phoneNumber");
            String restStatus = intent.getExtras().getString("restStatus");
            String openingTime = intent.getExtras().getString("openingTime");
            String closingTime = intent.getExtras().getString("closingTime");



            //setting values
            tvtitle.setText(title);
            tvPhoneNumber.setText(String.valueOf(phoneNumber));
            tvdescription.setText(address);
            restaurantStatus.setText(restStatus+" now ");
            restOpeningTime.setText(openingTime);
            restClosingTime.setText(closingTime);
            restEmailView.setText(restEmail);

            if(restaurant_rating == 0.0){
                //rating.setText("new");

                rating.setText("new");
                int color = Color.rgb(164, 164, 164);
                rating.setBackgroundColor(color);
                rating.setBackgroundResource(R.drawable.new_restaurants_badge);
            }else{
                rating.setText(String.valueOf(new DecimalFormat("0.0").format(restaurant_rating)));


            }

            img.setImageBitmap(restImage);

        }
        catch(Exception e){
            e.printStackTrace();
        }



        ///menu buttons
        menuBtn = findViewById(R.id.menu_id);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this, MenuActivity.class);
                intent.putExtra("res_id", restId);
                startActivity(intent);
                //Toast.makeText(BookActivity.this, "id:"+restId, Toast.LENGTH_SHORT).show();
            }
        });

        ///map buttons

        ///review buttons
        review = findViewById(R.id.rev_id);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reviewIntent = new Intent(BookActivity.this, ReviewActivity.class);
                reviewIntent.putExtra("rest_id",restId);
                reviewIntent.putExtra("f_id",sessionLogin.getInt("foodies_id",0));
                startActivity(reviewIntent);
            }
        });


        ///share buttons
        share = findViewById(R.id.share_id);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "website under construction";
                String shareSub = "Your subject is here";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(shareIntent,"Share Using"));
            }
        });

        ///photto buttons
        photos = findViewById(R.id.photo_id);
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this,PhotoActivity.class);
                intent.putExtra("restId", restId);
                intent.putExtra("restEmail", restEmail);
                startActivity(intent);
            }
        });

        /// sending reviews and rating in restaurants

            reviews();





        /// calling to Restaurants

        callingToRestaurants = findViewById(R.id.calling);

        callingToRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
//                    number = "01837222121";
                    number = "0"+tvPhoneNumber.getText().toString();
                    Intent call = new Intent(Intent.ACTION_CALL);
                    call.setData(Uri.parse("tel:"+number.toString()));
                    startActivity(call);
                }
                catch(SecurityException e){
                    e.printStackTrace();
                }

            }
        });









    }

    public void reviews(){

        //smily rating bar


        foodieid = sessionLogin.getInt("foodies_id",-1);


        SmileRating smileRating = (SmileRating) findViewById(R.id.smile_rating);

        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        //Toast.makeText(BookActivity.this, "Bad", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                       // Toast.makeText(BookActivity.this, "good", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        //Toast.makeText(BookActivity.this, "great", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                       // Toast.makeText(BookActivity.this, "okay", Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:
                       // Toast.makeText(BookActivity.this, "Terrible", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
               // Toast.makeText(BookActivity.this, "Selected Rating "+level, Toast.LENGTH_SHORT).show();
                //updating rating for restaurant

                ratingNum = level;

                if(foodieid != -1){



                    cursorRes = databaseHelper.readRatingTable(foodieid,restId);
                    // foodieid == cursorRes.getInt(2) && restId == cursorRes.getInt(3)

                    int f_Id = 0;
                    int r_Id=0;
                    int raid=0;
                    while(cursorRes.moveToNext()){
                        f_Id = cursorRes.getInt(2);
                        r_Id = cursorRes.getInt(3);
                        raid = cursorRes.getInt(0);

                    }
//                    foodieid == f_Id && restId == r_Id
                    if(cursorRes.getCount()==1 && !sendReview.getText().toString().isEmpty()){
                        //if(foodieid != -1){
                        // update
                        // long row = databaseHelper.insertRestRating((double) level,foodieid,restId);

                        boolean row = databaseHelper.updateRating(raid,(double) level);
                        if(row == false ){
                            Toast.makeText(BookActivity.this, "rating failed", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(BookActivity.this, "rating updated successfull", Toast.LENGTH_SHORT).show();
                            boolean update = databaseHelper.updateRatingToRestaurantTable(restId);

                        }

                        // }
                        // else{
                        //     Toast.makeText(BookActivity.this, "login required", Toast.LENGTH_SHORT).show();
                        //}
                    }
                    else if(cursorRes.getCount() != 1 && !sendReview.getText().toString().isEmpty()){
                        // if(foodieid != -1){
                        long row = databaseHelper.insertRestRating((double) level,foodieid,restId);
                        if(row == -1 ){
                            Toast.makeText(BookActivity.this, "rating failed", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(BookActivity.this, "rating successfull", Toast.LENGTH_SHORT).show();
                            boolean update = databaseHelper.updateRatingToRestaurantTable(restId);

                        }

                        //  }
                        // else{
                        //Toast.makeText(BookActivity.this, "login required", Toast.LENGTH_SHORT).show();
                        // }
                    }

                    else {
                        Toast.makeText(BookActivity.this, "review required", Toast.LENGTH_SHORT).show();
                    }



                }else{
                    Toast.makeText(BookActivity.this, "foodies login required", Toast.LENGTH_SHORT).show();
                }








            }
        });

        ///sending reviews for a restaurant

        //sendReview = findViewById(R.id.review_message_id);
        send = findViewById(R.id.send_review_id);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cursorReview = databaseHelper.loadDataFromFoodiesAndRating(restId,foodieid);

                while(cursorReview.moveToNext()){
                    foodieName = cursorReview.getString(0);
                    rating_id = cursorReview.getInt(2);
                }

                if(!sendReview.getText().toString().trim().isEmpty()){


                    if(foodieid != -1 && ratingNum > 0 ){


                        long returnValue = databaseHelper.insertDataIntoReviews(foodieid,ratingNum,sendReview.getText().toString(),rating_id);


                        if(returnValue == -1){
                            Toast.makeText(BookActivity.this, "review failed", Toast.LENGTH_SHORT).show();
                        }

                        else{



                            Toast.makeText(BookActivity.this, "review added sucessfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(ratingNum == 0) {
                        Toast.makeText(BookActivity.this, "rating required", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Toast.makeText(BookActivity.this, "foodies login required", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(BookActivity.this, "review required", Toast.LENGTH_SHORT).show();
                }

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
