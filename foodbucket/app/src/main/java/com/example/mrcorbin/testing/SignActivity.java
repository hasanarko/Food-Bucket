package com.example.mrcorbin.testing;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignActivity extends AppCompatActivity {

    private Button joinHere,learnMore,login;


    private DatabaseHelper databaseHelper;
    NavigationView navigationView;



    //private EditText userName,password;

    private TextInputLayout eEmail, eUsername, ePassword ,ePhoneNumber,loginEmail,passwordLogin;

    SharedPreferences sessionLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        /// buttons in sign in activity
        joinHere = findViewById(R.id.joinhere_id);
        learnMore = findViewById(R.id.learnmore_id);
        login = findViewById(R.id.login_id);



        ///database connection
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        // session declare
        sessionLogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sessionLogin.edit();


        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //foodies list declare


        joinHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent = new Intent(SignActivity.this,JoinHereActivity.class);
               // startActivity(intent);
                final AlertDialog.Builder mBuilder= new AlertDialog.Builder(SignActivity.this);
                View view = getLayoutInflater().inflate(R.layout.join_here,null);
                 eEmail = view.findViewById(R.id.join_email_id);
                 eUsername = view.findViewById(R.id.join_username_id);
                 ePassword = view.findViewById(R.id.join_pass_id);
                // eConfPass = view.findViewById(R.id.join_conf_pass_id);
                 ePhoneNumber = view.findViewById(R.id.join_phone_id);
               // Button joinButton = view.findViewById(R.id.join_app_server_id);
/*
                joinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!eEmail.getText().toString().isEmpty() && !eUsername.getText().toString().isEmpty() && !ePassword.getText().toString().isEmpty() && !eConfPass.getText().toString().isEmpty()){
                            Toast.makeText(SignActivity.this, "joining successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Si)

                        }
                        else{
                            Toast.makeText(SignActivity.this, "joining unsuccessfull", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

*/

              ///inserting foodiez data

                mBuilder.setPositiveButton("Sign Up",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //dialog.cancel();
                                //
        String email = eEmail.getEditText().getText().toString().trim();
        String fullname = eUsername.getEditText().getText().toString().trim();
        String phoneNumber = ePhoneNumber.getEditText().getText().toString().trim();
       // String confPassword = eConfPass.getEditText().getText().toString().trim();
        String password = ePassword.getEditText().getText().toString().trim();





                        if(!eEmail.getEditText().getText().toString().trim().isEmpty()
                            && !eUsername.getEditText().getText().toString().trim().isEmpty()
                            && !ePassword.getEditText().getText().toString().trim().isEmpty()
                                && !ePhoneNumber.getEditText().getText().toString().trim().isEmpty()){




                            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                             if(eEmail.getEditText().getText().toString().matches(emailPattern) && phoneNumber.length() == 11){
                                 //userRegistration();

                                 long insert = databaseHelper.insertUsers(email,password,"foodie");

                                 if(insert == -1){
                                     Toast.makeText(SignActivity.this, "same email exist choose another one", Toast.LENGTH_SHORT).show();

                                 }else{
                                     int phone = Integer.parseInt(phoneNumber);

                                     long rowid = databaseHelper.insertFoodiesData(fullname,email,password,phone);

                                     if(rowid == -1){
                                         Toast.makeText(SignActivity.this, "user email already exists", Toast.LENGTH_SHORT).show();
                                     }
                                     else{
                                         Toast.makeText(SignActivity.this, "registration sucessfully done", Toast.LENGTH_SHORT).show();

                                     }
                                 }



                             }else{
                                 Toast.makeText(SignActivity.this, "invalid registration detected", Toast.LENGTH_SHORT).show();

                             }


                        }
                        else{
                            Toast.makeText(SignActivity.this, "fill up all field for completing sign up", Toast.LENGTH_SHORT).show();

                        }

                    }
                });






                mBuilder.setView(view);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

                //getting the button in center
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                LinearLayout parent = (LinearLayout) positiveButton.getParent();
                parent.setGravity(Gravity.CENTER_HORIZONTAL);
               // parent.setMinimumWidth(15);

              //  parent.setBackgroundResource(R.drawable.join_here_button_style);
                View leftSpacer = parent.getChildAt(1);
                leftSpacer.setVisibility(View.GONE);
            }
        });


        learnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignActivity.this,LearnMoreActivity.class);
                startActivity(intent);
            }
        });

        ///login foodiez and restaurant owners

        loginEmail =findViewById(R.id.email_login_id);
        passwordLogin = findViewById(R.id.password_login_id);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //login verification for foodies and restaurant owner
                String email = loginEmail.getEditText().getText().toString().trim();
                String password = passwordLogin.getEditText().getText().toString().trim();

                Cursor cursor = databaseHelper.displayDataForLogin(email,password);


                if(!email.isEmpty() && !password.isEmpty()){
                    if(cursor.getCount() == 0){
                        Toast.makeText(SignActivity.this, "user not exists", Toast.LENGTH_SHORT).show();
                    }
                    else{


                        while (cursor.moveToNext()){

                            if(cursor.getString(3).equals("foodie")){
                                int foodieId =0;
                                String foodie_email = cursor.getString(1);
                                String foodie_name = "";
                                String foodie_phoneNumber="";
                                String type = "";
                                Cursor check_foodie = databaseHelper.queryOnFoodieTable(foodie_email);

                                while(check_foodie.moveToNext()) {
                                    foodieId=check_foodie.getInt(0);
                                    foodie_name = check_foodie.getString(1);
                                    foodie_phoneNumber = check_foodie.getString(4);
                                    type = check_foodie.getString(3);
                                }

                                Intent intent = new Intent(SignActivity.this,MainActivity.class);
                                Toast.makeText(SignActivity.this, "you are logged in", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                                /*  Intent intent = new Intent(SignActivity.this,DashBoardActivity.class);
                                intent.putExtra("foodie_id",foodieId);
                                intent.putExtra("foodieName", foodie_name);
                                intent.putExtra("foodieEmail", foodie_email);
                                intent.putExtra("foodiePhoneNumber", foodie_phoneNumber);
                                intent.putExtra("foodie_type", type);
                                startActivity(intent);
*/
                                editor.putInt("foodies_id", foodieId);
                                editor.putString("user_email", foodie_email);
                                editor.putString("foodie", cursor.getString(3));
                                editor.putString("foodie_phoneNumber",foodie_phoneNumber);
                                editor.putString("foodie_name",foodie_name);
                                editor.putString("foodies_type",type);

                            }
                            else if(cursor.getString(3).equals("restaurants_owner")){
                                String restaurant_email = cursor.getString(1);
                                int restId = 0;
                                String restaurant_name = "";
                                String restaurant_phoneNumber="";
                                String restaurant_address = "";
                                String type="";
                                byte [] restaurant_image={};

                                Cursor check_restaurant = databaseHelper.queryOnRestaurantTable(restaurant_email);

                                while(check_restaurant.moveToNext()) {
                                    restId = check_restaurant.getInt(0);
                                    restaurant_name = check_restaurant.getString(1);
                                    restaurant_phoneNumber = check_restaurant.getString(5);
                                    restaurant_address = check_restaurant.getString(6);
                                    restaurant_image = check_restaurant.getBlob(8);
                                    type = check_restaurant.getString(3);
                                    //restaurant_address = check_restaurant.getString()
                                }

                                Intent intent = new Intent(SignActivity.this,MainActivity.class);
                                Toast.makeText(SignActivity.this, "you are logged in", Toast.LENGTH_SHORT).show();

                                startActivity(intent);



                                // session work

                                editor.putString("rest_id", String.valueOf(restId));
                                editor.putString("rest_name", restaurant_name);
                                editor.putString("user_email", restaurant_email);
                                editor.putString("rest_owner", cursor.getString(3));
                                editor.putString("rest_phone", restaurant_phoneNumber);
                                editor.putString("rest_address", restaurant_address);
                                editor.putString("rest_type", type);


                                // to store image in session
                                ByteArrayInputStream imageStream = new ByteArrayInputStream(restaurant_image);
                                Bitmap restImage = BitmapFactory.decodeStream(imageStream);
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                restImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
                                byte[] b = baos.toByteArray();

                                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                                editor.putString("rest_image", encodedImage);


                            }

                        }

                        editor.commit();

                    }
                }
                else{
                    Toast.makeText(SignActivity.this, "fill email and password field", Toast.LENGTH_SHORT).show();
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


    public void onSubmit(View view) {
    }
}
