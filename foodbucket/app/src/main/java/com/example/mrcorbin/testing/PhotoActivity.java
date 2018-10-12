package com.example.mrcorbin.testing;

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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {


    private Button openCamera;
    private ImageView image;
    private DatabaseHelper databaseHelper;

    Button chosePhoto;
    Button deletePhoto;
    Button takePhoto;
    int restId;
    String restEmail;
    Cursor cursorImage;

    InputStream inputStream;
    Bitmap bitmap;
    int img_id;

    Uri uri;


    // image gallery
    Gallery simpleGallery;
    CustomGalleryAdapter customGalleryAdapter;
    ImageView selectedImageView,insertPics;
    List<gallery> galleryList;

    SharedPreferences sessionLogin;

    // array of images
    //int[] images = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five};


    //int[] images={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);




        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        sessionLogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sessionLogin.edit();

        galleryList = new ArrayList<>();
        ///open camera
//        openCamera =findViewById(R.id.open_camera);
//        image = findViewById(R.id.image_id);
//        openCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,99);
//            }
//        });

         //restId = getIntent().getExtras().getInt("restId");

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            restId = bundle.getInt("restId");
            restEmail = bundle.getString("restEmail");
        }

        insertPics = findViewById(R.id.insertImage);

        cursorImage = databaseHelper.getData(restId);

        while(cursorImage.moveToNext()) {
            byte[] food_image = cursorImage.getBlob(1);

            galleryList.add(new gallery(food_image));
        }

        // image gallery
        simpleGallery = (Gallery) findViewById(R.id.simpleGallery); // get the reference of Gallery
        selectedImageView = (ImageView) findViewById(R.id.selectedImageView); // get the reference of ImageView
        customGalleryAdapter = new CustomGalleryAdapter(getApplicationContext(), galleryList); // initialize the adapter
        simpleGallery.setAdapter(customGalleryAdapter); // set the adapter
        simpleGallery.setSpacing(10);
        // perform setOnItemClickListener event on the Gallery
        simpleGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set the selected image in the ImageView
                //selectedImageView.setImageResource(images[position]);
                byte[] f_img = galleryList.get(position).getImagePhoto();
                Bitmap bitmap = BitmapFactory.decodeByteArray(f_img, 0, f_img.length);

                selectedImageView.setImageBitmap(bitmap);

            }
        });


        // delete picture from image gallery

        simpleGallery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {




                String email = sessionLogin.getString("user_email", null);
                //Toast.makeText(PhotoActivity.this, email+" -> "+restEmail, Toast.LENGTH_SHORT).show();

                if(restEmail.equals(email) && email != null) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(PhotoActivity.this);
                    v = getLayoutInflater().inflate(R.layout.delete_photo,null);


                    galleryList = databaseHelper.loadImageGallery(restId);


                    deletePhoto = v.findViewById(R.id.delete_pic);
                     img_id = galleryList.get(position).getImageId();

                    deletePhoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Toast.makeText(PhotoActivity.this, "Image id - "+img_id, Toast.LENGTH_SHORT).show();

                            int retVal = databaseHelper.deletePhoto(String.valueOf(img_id));

                            if (retVal == 1) {
                                Toast.makeText(PhotoActivity.this, "Picture deleted", Toast.LENGTH_SHORT).show();
                               // Toast.makeText(PhotoActivity.this, String.valueOf(retVal), Toast.LENGTH_SHORT).show();
                                onRestart();
                            } else {
                                Toast.makeText(PhotoActivity.this, "Action not performed", Toast.LENGTH_SHORT).show();
                            }



                        }
                    });


                    builder.setView(v);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Toast.makeText(PhotoActivity.this, "You're not authorized for furthur action", Toast.LENGTH_SHORT).show();
                }



                return false;
            }
        });


    }

//    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////
////        if(requestCode == 99 && resultCode == RESULT_OK && data != null){
////            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
////            image.setImageBitmap(bitmap);
////        }
////
////
////
////    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        if (id == R.id.mybutton) {
            // do something here
//            final AlertDialog.Builder mBuilder= new AlertDialog.Builder(this);
//            View v = getLayoutInflater().inflate(R.layout.photo_add,null);


            //takePhoto = v.findViewById(R.id.take_photo);
            /*takePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String owner_email = sessionLogin.getString("user_email", null);
                    String foodie_email = sessionLogin.getString("foodie_email", null);

                    //take();

                    String type = sessionLogin.getString("foodies_type", null);

                    if(owner_email != null && restEmail.equals(owner_email)) {
                        take();
                    } else if(type != null) {
                        take();
                    } else {
                        Toast.makeText(PhotoActivity.this, "You're not authorized for furthur action", Toast.LENGTH_SHORT).show();
                    }


                }
            });*/

//            chosePhoto = v.findViewById(R.id.choose_photo);
//            chosePhoto.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String owner_email = sessionLogin.getString("user_email", null);
//                    //String foodie_email = sessionLogin.getString("foodie_email", null);
//                    String type = sessionLogin.getString("foodies_type", null);
//
//                    if(owner_email != null && restEmail.equals(owner_email)) {
//                        choose();
//                    } else if(type != null) {
//                        choose();
//                    } else {
//                        Toast.makeText(PhotoActivity.this, "You're not authorized for furthur action", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
//            });

//            takePhoto.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(intent,99);
//
//
//                }
//            });


            String owner_email = sessionLogin.getString("user_email", null);
            //String foodie_email = sessionLogin.getString("foodie_email", null);
            String type = sessionLogin.getString("foodies_type", null);

            if(owner_email != null && restEmail.equals(owner_email)) {
                choose();
            } else if(type != null) {
                choose();
            } else {
                Toast.makeText(PhotoActivity.this, "You're not authorized for furthur action", Toast.LENGTH_SHORT).show();
            }







//
//            mBuilder.setView(v);
//            AlertDialog dialog = mBuilder.create();
//            dialog.show();




        }

        return super.onOptionsItemSelected(item);


    }
    private void choose() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }
    private void take(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {


            try{
                uri = data.getData();

                inputStream = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                insertPics.setImageBitmap(bitmap);
                insert();


            }catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "image size is too big", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void insert() {

        try {

            byte[] image = imageViewToByte(insertPics);
            if(image != null) {
                long rowId = databaseHelper.uploadGallaryImage(String.valueOf(restId), image);
                if (rowId == -1) {
                    Toast.makeText(this, "upload failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "New image uploaded", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "File size is not compatible", Toast.LENGTH_SHORT).show();
            }

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
        }
        else {
            return byteArray;
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


}
