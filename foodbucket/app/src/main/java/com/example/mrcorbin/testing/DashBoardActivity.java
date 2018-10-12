package com.example.mrcorbin.testing;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoardActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewUserName,textViewEmail, textViewPhoneNumber,textViewPassword;
    private TextView updateFoodieName,updateFoodiePhoneNumber,foodiesId,updateFoodiesPassword;

    private TextInputLayout editFoodieName,editFoodiePhoneNumber,editFoodiesPassword;

    private Button saveFoodieName,saveFoodiePhoneNumber,savefoodiePassword;
    DatabaseHelper databaseHelper;
    int foodieId;
    String email;
    String name;
    int phone;
    String password;


    ///session
    SharedPreferences sessionLogin;
    SharedPreferences.Editor editor;

    Cursor cursorFoodies;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);



        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        //starting the session
        sessionLogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sessionLogin.edit();

        textViewUserName = findViewById(R.id.username);
        textViewEmail = findViewById(R.id.email_id);
        textViewPhoneNumber = findViewById(R.id.mobile_id);
        textViewPassword = findViewById(R.id.password_id);

        updateFoodieName = findViewById(R.id.edit_foodies_name);
        updateFoodiePhoneNumber = findViewById(R.id.edit_foodies_phone);
        updateFoodiesPassword = findViewById(R.id.edit_foodies_password);


        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ///showing email
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            foodieId = bundle.getInt("foodie_id");
//             email = bundle.getString("foodieEmail");
//            String name = bundle.getString("foodieName");
//            String phone = bundle.getString("foodiePhoneNumber");

//            textViewUserName.setText(name);
//            textViewEmail.setText(email);
//            textViewPhoneNumber.setText(phone);

        }


        cursorFoodies = databaseHelper.findFoodieById(foodieId);

        while(cursorFoodies.moveToNext()){
            name = cursorFoodies.getString(1);
            email = cursorFoodies.getString(2);
            password = cursorFoodies.getString(3);
            phone = cursorFoodies.getInt(4);

        }

        textViewUserName.setText(name);
        textViewEmail.setText(email);
        textViewPhoneNumber.setText(String.valueOf(phone));
        textViewPassword.setText(password);






        //profile image

        imageView = findViewById(R.id.profile_pic_id);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.p_icon);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);


        //edit foodies name and update

        updateFoodieName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardActivity.this);
                v = getLayoutInflater().inflate(R.layout.edit_foodie_name,null);

                // write code


                editFoodieName = v.findViewById(R.id.new_foodie_name);
                saveFoodieName = v.findViewById(R.id.update_foodie_name);
                saveFoodieName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!editFoodieName.getEditText().getText().toString().trim().isEmpty()){

                            if(editFoodieName.getEditText().getText().toString().trim().equals(textViewUserName.getText().toString())){
                                Toast.makeText(DashBoardActivity.this, "new name required", Toast.LENGTH_SHORT).show();
                            }else{
                                boolean update = databaseHelper.updateFoodieName(email,editFoodieName.getEditText().getText().toString().trim());
                                if(update == true){
                                    Toast.makeText(DashBoardActivity.this, "user name updated", Toast.LENGTH_SHORT).show();
                                    onRestart();

                                }
                                else {
                                    Toast.makeText(DashBoardActivity.this, "user name not updated", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }else{
                            Toast.makeText(DashBoardActivity.this, "foodie name required", Toast.LENGTH_SHORT).show();
                        }



                    }
                });

                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        //edit foodies name and update

        updateFoodiePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardActivity.this);
                v = getLayoutInflater().inflate(R.layout.edit_foodie_phone_number,null);

                // write code
               // editFoodieName = v.findViewById(R.id.edit_foodies_name);

                editFoodiePhoneNumber = v.findViewById(R.id.new_foodie_phone_number);
                saveFoodiePhoneNumber = v.findViewById(R.id.update_foodie_phone_number);
                saveFoodiePhoneNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                      if(!editFoodiePhoneNumber.getEditText().getText().toString().trim().isEmpty()){

                          if(editFoodiePhoneNumber.getEditText().getText().toString().trim().equals(textViewPhoneNumber.getText().toString())){
                              Toast.makeText(DashBoardActivity.this, "new phone number required", Toast.LENGTH_SHORT).show();
                          }else{
                              boolean update = databaseHelper.updateFoodiePhoneNumber(email,editFoodiePhoneNumber.getEditText().getText().toString().trim());

                              if(update == true){

                                  Toast.makeText(DashBoardActivity.this, "phone number updated", Toast.LENGTH_SHORT).show();
                                  onRestart();


                              }
                              else {
                                  Toast.makeText(DashBoardActivity.this, "phone number not updated", Toast.LENGTH_SHORT).show();
                              }
                          }


                      }else{
                          Toast.makeText(DashBoardActivity.this, "phone number required", Toast.LENGTH_SHORT).show();

                      }


                    }
                });

                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        //edit foodies password and update
        updateFoodiesPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardActivity.this);
                v = getLayoutInflater().inflate(R.layout.edit_foodie_password,null);

                // write code
                // editFoodieName = v.findViewById(R.id.edit_foodies_name);

                editFoodiesPassword = v.findViewById(R.id.new_foodie_password);
                savefoodiePassword = v.findViewById(R.id.update_foodie_password);
                savefoodiePassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if(!editFoodiesPassword.getEditText().getText().toString().trim().isEmpty()){

                            if(editFoodiesPassword.getEditText().getText().toString().trim().equals(textViewPassword.getText().toString().trim())){
                                Toast.makeText(DashBoardActivity.this, "new password required", Toast.LENGTH_SHORT).show();
                            }else{
                                boolean update = databaseHelper.updateFoodiePassword(email,editFoodiesPassword.getEditText().getText().toString().trim());
                                boolean userUpdate = databaseHelper.updateFoodiePasswordToUserTable(email,editFoodiesPassword.getEditText().getText().toString().trim());

                                if(update == true && userUpdate == true){

                                    Toast.makeText(DashBoardActivity.this, "password updated", Toast.LENGTH_SHORT).show();
                                    onRestart();


                                }
                                else {
                                    Toast.makeText(DashBoardActivity.this, "password not updated", Toast.LENGTH_SHORT).show();
                                }
                            }


                        }else{
                            Toast.makeText(DashBoardActivity.this, "password required", Toast.LENGTH_SHORT).show();

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
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
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
