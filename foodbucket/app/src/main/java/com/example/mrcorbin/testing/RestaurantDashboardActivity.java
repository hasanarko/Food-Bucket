package com.example.mrcorbin.testing;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDashboardActivity extends AppCompatActivity {

///session
    SharedPreferences sessionLogin;
    SharedPreferences.Editor editor;

    // setting values for profiles
    private TextView textViewName, textViewEmail, textViewPhone, textViewAddress ,textEditPhone,textPassword,textStatus,textTime;
    //private ImageView profileImage;
    private ImageView imageView;
    //edit button for update
    private TextView editRestName,editRestPhoneNumber,editRestAddress,editPassword;
    private TextInputLayout editNewRestName,editNewRestPhoneNumber,editNewRestAddress,editNewPassword;


    //update buttons for alertdialouge
    private Button saveNewRestName,saveNewRestPhoneNumber,saveNewRestAddress;

    //setting textView for findging restaurants id
    private TextView restId;
    private TextView Id;
    private TextView IdforAddress,IdforPass;
    private ImageView restaurantProfileImage;

     Button chosePhoto;
     Button takePhoto;

     Button restaurantOpen,restaurantClose,saveRestaurantTime,saveNewPass;
     EditText editOpeningTime,editClosingTime;
    private static final int REQUEST_CODE_GALLERY = 10001;






    //private EditText editCusine, editFoodItem;



    private Button btnAddMenu;

    Button updatePhone,choosePics;
    DatabaseHelper databaseHelper;
    int id;
    String email;
    String phone;
    String name;
    String password;
    String address;
    String status;
    String OpeningTime;
    String ClosingTime;
    byte[] profile;
    Bitmap restImage;
    Cursor restaurantCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_dashboard);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        //starting the session
        sessionLogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sessionLogin.edit();

        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        textViewName = findViewById(R.id.restaurant_name);
        textViewEmail = findViewById(R.id.rest_email_id);
        textViewPhone = findViewById(R.id.rest_mobile_id);
        textViewAddress = findViewById(R.id.restaurant_location_id);
        textPassword = findViewById(R.id.restaurant_password_id);
        textStatus = findViewById(R.id.restaurant_status_id);
        textTime = findViewById(R.id.restaurant_time_id);






      //  profileImage = findViewById(R.id.p_id);
        imageView = findViewById(R.id.imageId);
        //choosePics = findViewById(R.id.click_pics);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose();
            }
        });

        btnAddMenu = findViewById(R.id.add_menu_item);




        // set restaurant profile
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
             id = bundle.getInt("id");


        }

        restaurantCursor = databaseHelper.findRestaurantById(id);
        while(restaurantCursor.moveToNext()) {
            name = restaurantCursor.getString(1);
            email = restaurantCursor.getString(2);
            password = restaurantCursor.getString(3);
            phone = restaurantCursor.getString(5);
            address = restaurantCursor.getString(6);
            //profile = bundle.getByteArray("image");
            status = restaurantCursor.getString(10);
            OpeningTime = restaurantCursor.getString(11);
            ClosingTime = restaurantCursor.getString(12);
            profile = restaurantCursor.getBlob(8);
        }

            textViewName.setText(name);
            textViewEmail.setText(email);
            textViewPhone.setText(phone);
            textViewAddress.setText(address);
            textPassword.setText(password);
            textStatus.setText(status);
            textTime.setText(OpeningTime+" to "+ClosingTime);

            ByteArrayInputStream imageStream = new ByteArrayInputStream(profile);
            restImage = BitmapFactory.decodeStream(imageStream);
            imageView.setImageBitmap(restImage);



        ///--------------updating restaurant name ----------------------

        editRestName = findViewById(R.id.edit_restaurant_name);
        editRestName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantDashboardActivity.this);
                v = getLayoutInflater().inflate(R.layout.edit_restaurant_name,null);

                editNewRestName = v.findViewById(R.id.new_restaurant_name);


                restId = v.findViewById(R.id.rest_id);
              //  String restaurantId = String.valueOf(id);
               // restId.setText(restaurantId);
                restId.setText(String.valueOf(id));
                restId.setVisibility(View.INVISIBLE);



                saveNewRestName= v.findViewById(R.id.update_restaurants_name);
                saveNewRestName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                       if(!editNewRestName.getEditText().getText().toString().trim().isEmpty()){


                           if(editNewRestName.getEditText().getText().toString().trim().equals(textViewName.getText().toString().trim())){
                               Toast.makeText(RestaurantDashboardActivity.this, "new restaurant name required", Toast.LENGTH_SHORT).show();
                           }else{
                               boolean update = databaseHelper.updateRestaurantName(restId.getText().toString().trim(),editNewRestName.getEditText().getText().toString().trim());
//                        Toast.makeText(RestaurantDashboardActivity.this, restId.getText().toString()+" : "+editNewRestName.getText().toString(), Toast.LENGTH_SHORT).show();
                               if(update == true){
                                   editor.putString("rest_name", editNewRestName.getEditText().getText().toString().trim());
                                   Toast.makeText(RestaurantDashboardActivity.this, "updated", Toast.LENGTH_SHORT).show();
                                   onRestart();
                                   //reset();

                               }
                               else {
                                   Toast.makeText(RestaurantDashboardActivity.this, "not updated", Toast.LENGTH_SHORT).show();
                               }
                           }


                       }else{
                           Toast.makeText(RestaurantDashboardActivity.this, "name required", Toast.LENGTH_SHORT).show();
                       }

                    }
                });

                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        /// updating restaurant phone number

        editRestPhoneNumber = findViewById(R.id.edit_phone_number);
        editRestPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantDashboardActivity.this);
                v = getLayoutInflater().inflate(R.layout.edit_phone_number,null);

                editNewRestPhoneNumber = v.findViewById(R.id.new_phone_number);
                Id =v.findViewById(R.id.r_id);
                Id.setText(String.valueOf(id));
                Id.setVisibility(View.INVISIBLE);

                saveNewRestPhoneNumber = v.findViewById(R.id.update_phone_number);

                saveNewRestPhoneNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!editNewRestPhoneNumber.getEditText().getText().toString().trim().isEmpty()){

                            if(editNewRestPhoneNumber.getEditText().getText().toString().trim().equals(textViewPhone.getText().toString())){
                                Toast.makeText(RestaurantDashboardActivity.this, "new number required", Toast.LENGTH_SHORT).show();
                            }else{
                                boolean update = databaseHelper.updateResttaurantPhoneNumber(Id.getText().toString(),editNewRestPhoneNumber.getEditText().getText().toString());
                                if(update = true){
                                    Toast.makeText(RestaurantDashboardActivity.this, "restaurant phone number updated", Toast.LENGTH_SHORT).show();
                                    //reset();
                                    onRestart();
                                    //onResume();
                                    //recreate();
                                }else{
                                    Toast.makeText(RestaurantDashboardActivity.this, "restaurant phone number not updated", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }else{
                            Toast.makeText(RestaurantDashboardActivity.this, "restaurant phone number required", Toast.LENGTH_SHORT).show();
                        }



                    }
                });

                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        ///updating restaurant address
        editRestAddress = findViewById(R.id.edit_address);
        editRestAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantDashboardActivity.this);
                v = getLayoutInflater().inflate(R.layout.edit_address,null);

                editNewRestAddress = v.findViewById(R.id.new_address);
                IdforAddress =v.findViewById(R.id.r2_id);
                IdforAddress.setText(String.valueOf(id));
                IdforAddress.setVisibility(View.INVISIBLE);

                saveNewRestAddress = v.findViewById(R.id.update_address);

                saveNewRestAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       if(!editNewRestAddress.getEditText().getText().toString().trim().isEmpty()){

                          if(editNewRestAddress.getEditText().getText().toString().trim().equals(textViewAddress.getText().toString().trim())){
                              Toast.makeText(RestaurantDashboardActivity.this, "new address required", Toast.LENGTH_SHORT).show();
                          }else {
                              boolean update = databaseHelper.updateResttaurantAddress(IdforAddress.getText().toString(),editNewRestAddress.getEditText().getText().toString());
                              if(update = true){
                                  Toast.makeText(RestaurantDashboardActivity.this, "restaurant address updated", Toast.LENGTH_SHORT).show();

                                  onRestart();
                              }else{
                                  Toast.makeText(RestaurantDashboardActivity.this, "restaurant address not updated", Toast.LENGTH_SHORT).show();
                              }
                          }


                       }else{
                           Toast.makeText(RestaurantDashboardActivity.this, "restaurant address required", Toast.LENGTH_SHORT).show();

                       }




                    }
                });

                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });





        ///updating restaurant password
        editPassword = findViewById(R.id.edit_rest_password);
        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantDashboardActivity.this);
                v = getLayoutInflater().inflate(R.layout.edit_rest_password,null);

                editNewPassword = v.findViewById(R.id.new_rest_pass);
                IdforPass =v.findViewById(R.id.r3_id);
                IdforPass.setText(String.valueOf(id));
                IdforPass.setVisibility(View.INVISIBLE);

                saveNewPass = v.findViewById(R.id.update_rest_pass);

                saveNewPass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if(!editNewPassword.getEditText().getText().toString().trim().isEmpty()){

                            if(editNewPassword.getEditText().getText().toString().trim().equals(textPassword.getText().toString().trim())){
                                Toast.makeText(RestaurantDashboardActivity.this, "new password required", Toast.LENGTH_SHORT).show();
                            }else{
                                boolean update = databaseHelper.updateRestaurantPassword(IdforPass.getText().toString(),editNewPassword.getEditText().getText().toString());
                                boolean updateLogin = databaseHelper.updateRestaurantAddressToLoginTable(email,editNewPassword.getEditText().getText().toString());

                                if(update = true){
                                    Toast.makeText(RestaurantDashboardActivity.this, "restaurant password updated", Toast.LENGTH_SHORT).show();

                                    onRestart();
                                }else{
                                    Toast.makeText(RestaurantDashboardActivity.this, "restaurant password not updated", Toast.LENGTH_SHORT).show();
                                }
                            }


                        }else{
                            Toast.makeText(RestaurantDashboardActivity.this, "restaurant password required", Toast.LENGTH_SHORT).show();

                        }


//                        boolean update = databaseHelper.updateRestaurantPassword(IdforPass.getText().toString(),editNewPassword.getEditText().getText().toString());
//                        boolean updateLogin = databaseHelper.updateRestaurantAddressToLoginTable(email,editNewPassword.getEditText().getText().toString());
//
//                        if(update = true){
//                            Toast.makeText(RestaurantDashboardActivity.this, "restaurant password updated", Toast.LENGTH_SHORT).show();
//
//                            onRestart();
//                        }else{
//                            Toast.makeText(RestaurantDashboardActivity.this, "restaurant password not updated", Toast.LENGTH_SHORT).show();
//                        }

                    }
                });

                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        //entering menu section
        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int restId = getIntent().getExtras().getInt("id");

                Intent intent = new Intent(RestaurantDashboardActivity.this,EditCusineActivity.class);
                intent.putExtra("rest_id",restId);
                startActivity(intent);
            }
        });

        //restaurant status
        restaurantOpen = findViewById(R.id.open);
        restaurantOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean open = databaseHelper.updateOpenRestStatus(String.valueOf(id),"open");
                if(open == true){
                    Toast.makeText(RestaurantDashboardActivity.this, "restaurant is opened", Toast.LENGTH_SHORT).show();
                    onRestart();
                }
                else{
                    Toast.makeText(RestaurantDashboardActivity.this, "restaurant is still closed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        restaurantClose = findViewById(R.id.close);
        restaurantClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                boolean close = databaseHelper.updateCloseRestStatus(String.valueOf(id),"close");
                if(close == true){
                    Toast.makeText(RestaurantDashboardActivity.this, "restaurant is closed", Toast.LENGTH_SHORT).show();
                    onRestart();
                }
                else{
                    Toast.makeText(RestaurantDashboardActivity.this, "restaurant is still opened", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /// set opening and closing time

        editOpeningTime = findViewById(R.id.opening_time);
        editClosingTime = findViewById(R.id.closing_time);
        saveRestaurantTime = findViewById(R.id.save_time);


            saveRestaurantTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!editOpeningTime.getText().toString().trim().isEmpty() && editClosingTime.getText().toString().trim().isEmpty() ){
                        boolean time = databaseHelper.updateOpeningTime(String.valueOf(id),editOpeningTime.getText().toString());
                        if(time == true){
                            Toast.makeText(RestaurantDashboardActivity.this, "Opening time set successfully", Toast.LENGTH_SHORT).show();
                            onRestart();
                        }else{
                            Toast.makeText(RestaurantDashboardActivity.this, "Opening time set unsuccessfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(editOpeningTime.getText().toString().trim().isEmpty() && !editClosingTime.getText().toString().trim().isEmpty() ){
                        boolean time = databaseHelper.updateClosingTime(String.valueOf(id),editClosingTime.getText().toString());
                        if(time == true){
                            Toast.makeText(RestaurantDashboardActivity.this, "Closing time set successfully", Toast.LENGTH_SHORT).show();
                            onRestart();
                        }else{
                            Toast.makeText(RestaurantDashboardActivity.this, "Closing time set unsuccessfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(!editOpeningTime.getText().toString().trim().isEmpty() && !editClosingTime.getText().toString().trim().isEmpty() ){
                        boolean time = databaseHelper.updateTime(String.valueOf(id),editOpeningTime.getText().toString(),editClosingTime.getText().toString());
                        if(time == true){
                            Toast.makeText(RestaurantDashboardActivity.this, "time set successfully", Toast.LENGTH_SHORT).show();
                            onRestart();
                        }else{
                            Toast.makeText(RestaurantDashboardActivity.this, "time set unsuccessfull", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RestaurantDashboardActivity.this, "both field required", Toast.LENGTH_SHORT).show();

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


    // refresh with backpress

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        Intent intent = getIntent();
//        finish();
//        startActivity(intent);
//    }

//    public void reset() {
//        Intent intent = new Intent(RestaurantDashboardActivity.this,RestaurantDashboardActivity.class);
//        //finish();
//        startActivity(intent);
//    }


    private void choose() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();

            try{

                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
                insert();

            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // insert/ update restaurant image
    private void insert() {

        try {
            byte[] img = imageViewToByte(imageView);
            if(img != null) {
                databaseHelper.updateRestaurantProfileImage(String.valueOf(id), img);
                Toast.makeText(this, "New Profile image updated", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "File size is not compatible", Toast.LENGTH_SHORT).show();
            }

            onRestart();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // convert bitmap imageview into byte array
    private byte[] imageViewToByte(ImageView img) {

        Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        if(byteArray.length / 1024 > 800) {
            return null;
        } else {
            return byteArray;
        }
    }
}
