package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SignupActivity extends AppCompatActivity {

    private EditText restaurantName, restaurantEmail, restaurantPhone, restaurantLocation, restaurantPassword;
    private RadioGroup restaurantOwnerType;
    private RadioButton type;


    private DatabaseHelper databaseHelper;




    private boolean isSubmit = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up);



        ///database connection
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


//             // restaurant name, phone no, location
//        final EditText restaurantName = findViewById(R.id.restaurantName);
//        final EditText restaurantPhoneNo = findViewById(R.id.restaurantPhoneNo);




        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


      //  final EditText restaurantLocation = findViewById(R.id.restaurantLocation);


        // check whether edittext are empty or not
//        Button buttonSubmit = findViewById(R.id.submitRestaurantInfo);
//        buttonSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (TextUtils.isEmpty(restaurantName.getText().toString())) {
//                    isSubmit = false;
//                    Toast.makeText(SignupActivity.this, "Restaurant Name must be filled", Toast.LENGTH_SHORT).show();
//                }
//
//                else if(TextUtils.isEmpty(restaurantPhoneNo.getText().toString())) {
//                    isSubmit = false;
//                    Toast.makeText(SignupActivity.this, "Restaurant Phone Number must be filled", Toast.LENGTH_SHORT).show();
//                }
//
//                else if(TextUtils.isEmpty(restaurantLocation.getText().toString())) {
//                    isSubmit = false;
//                    Toast.makeText(SignupActivity.this, "Restaurant Location must be filled", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);


    }



    public void onSubmit(View view) {

        restaurantName = findViewById(R.id.restaurantName);
        restaurantEmail = findViewById(R.id.restaurantEmail);
        restaurantPhone = findViewById(R.id.restaurantPhoneNo);
        restaurantLocation = findViewById(R.id.restaurantLocation);
        restaurantPassword = findViewById(R.id.restaurantPassword);
        restaurantOwnerType = findViewById(R.id.radiobtn);

        int selected = restaurantOwnerType.getCheckedRadioButtonId();
        type = findViewById(selected);


        String name = restaurantName.getText().toString();
        String email = restaurantEmail.getText().toString();
        String phoneNumber = restaurantPhone.getText().toString();
        String location = restaurantLocation.getText().toString();
        String password = restaurantPassword.getText().toString();
        String owner_type = type.getText().toString();
       // byte [] img ={(byte) R.drawable.rest};
        ImageView image=findViewById(R.id.restaurant_image_id);
        String type = "restaurant";






        if(!name.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty() && !location.isEmpty() && !password.isEmpty()){



            //holder.tvRestImage.setImageBitmap(image);
            image.setImageResource(R.drawable.rest);

            Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();




            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


            if(email.matches(emailPattern) && phoneNumber.length() == 11){



                long insert = databaseHelper.insertUsers(email,password,"restaurants_owner");

                if(insert == -1) {
                    Toast.makeText(this, "same email exist choose another one", Toast.LENGTH_SHORT).show();
                } else {
                    int phone = Integer.parseInt(phoneNumber);

                    long rowId = databaseHelper.insertRestaurantData(name, email, password, owner_type, phone,location,"pending",byteArray,0.0,"n/a","n/a","n/a",type);

                    if(rowId == -1){
                        Toast.makeText(this, "user email already exist", Toast.LENGTH_SHORT).show();


                    }else{

                        Toast.makeText(this, "registration done", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this,SignActivity.class);
                        startActivity(intent);
                    }


                   // Toast.makeText(this, "registration done", Toast.LENGTH_SHORT).show();


                }
            }else{
                Toast.makeText(this, "Invalid registration detected", Toast.LENGTH_SHORT).show();
            }




        }
        else{
            Toast.makeText(this, "fill up all requirement for signing up restaurants", Toast.LENGTH_SHORT).show();
        }



    }
}
