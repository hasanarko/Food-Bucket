package com.example.mrcorbin.testing;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class EditCusineActivity extends AppCompatActivity {

    ListView cuisineListView;
    DatabaseHelper databaseHelper;
    List<MenuItem> menuItem;
   // List<MenuItem> seletedMenu;

    private Button btnAddCuisine;
    private TextInputLayout editCuisine;

    String name; // cuisine name
    int cuisineId;
     int restaurant_id;
     int check;
     int checkTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cusine);

        ///add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // database
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        // find view
        cuisineListView = findViewById(R.id.cusine_list_id);

        // load cuisine in listview
        restaurant_id = getIntent().getExtras().getInt("rest_id");
//       menuItem = databaseHelper.cuisineList(restaurant_id);


        menuItem = databaseHelper.loadCuisinesNames();
        cuisineListView.setAdapter(new CustomMenuAdapter(this, menuItem));

        // click on cuisine listview to load it's food list
//        cuisineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                name = menuItem.get(position).getCuisineName();
//                cuisineId = menuItem.get(position).getCuisineId();
//                //int age = personList.get(position).getPerson_age();
//                //String birth = personList.get(position).getPerson_birthDate();
//                Intent intent = new Intent(EditCusineActivity.this, EditFoodItemActivity.class);
//                intent.putExtra("cuisine_id", cuisineId);
//                startActivity(intent);
//            }
//        });

//        cuisineListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                return false;
//            }
//        });

        cuisineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name = menuItem.get(position).getCuisineName();
                showMenuDialouge(String.valueOf(menuItem.get(position).getCuisineId()),menuItem.get(position).getCuisineName(),String.valueOf(restaurant_id));

            }
        });

        ///add cuisine
        btnAddCuisine = findViewById(R.id.add_cusine_id);
       // editCuisine = findViewById(R.id.enter_cuisine_id);

        btnAddCuisine.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {

                Intent intent = new Intent(EditCusineActivity.this,ExploreCuisines.class);
                intent.putExtra("rest_id", restaurant_id);
                startActivity(intent);


        }
    });

    }

    //show alert dialouge for selecting and updating cuisine item

    private void showMenuDialouge(final String cuisineId, final String cusineName, final String restId){

        AlertDialog.Builder dialougeBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialougeView = inflater.inflate(R.layout.cuisine_dialouge,null);

        dialougeBuilder.setView(dialougeView);

       // final EditText editCuisineName = dialougeView.findViewById(R.id.edit_cuisine_id);
        final Button btnUpdateCuisine = dialougeView.findViewById(R.id.update_cuisine_id);
        //final Button btnDeleteCuisine = dialougeView.findViewById(R.id.delete_cuisine_id);
        final TextView cuisine = dialougeView.findViewById(R.id.cusine_id);
        cuisine.setText(String.valueOf(cuisineId));
        cuisine.setVisibility(View.INVISIBLE);



        dialougeBuilder.setTitle("select your favourite cuisines");

        btnUpdateCuisine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
//                boolean getRow = databaseHelper.updateCuisine(cuisineId,name,String.valueOf(restaurant_id));
//                if(getRow == true){
//                    Toast.makeText(EditCusineActivity.this, "updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = getIntent();
//                    finish();
//                    startActivity(intent);
//                }
//                else{
//                    Toast.makeText(EditCusineActivity.this, "not updated", Toast.LENGTH_SHORT).show();
//                }




                Cursor cursor = databaseHelper.loadSelectedCuisine(restaurant_id);
                while(cursor.moveToNext()){
                    if(cursor.getString(1).equals(name)){
                        //Toast.makeText(EditCusineActivity.this, "already exists", Toast.LENGTH_SHORT).show();
                      check++;
                    }


                }

                if(check >= 1){
                    Toast.makeText(EditCusineActivity.this, "already exists", Toast.LENGTH_SHORT).show();
                    check--;
                }else{
                    long rowCuisine = databaseHelper.insertCusine(name,restaurant_id);

                        if(rowCuisine == -1){
                        Toast.makeText(EditCusineActivity.this, "cuisine not selected", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(EditCusineActivity.this, "cuisine selected", Toast.LENGTH_SHORT).show();
                           // Toast.makeText(EditCusineActivity.this, rowCuisine+"cuisine not selected", Toast.LENGTH_SHORT).show();
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);

                        }
                }
//                while (cursor.moveToNext()){
//                    if(cursor.getString(1).equals(name)){
//                        check++;
//                    }
//                    if(cursor.getInt(2) == restaurant_id){
//                        checkTwo++;
//                    }
//
//                }
//
//                if(check ==0 && checkTwo == 0){
//                    long rowCuisine = databaseHelper.insertCusine(name,restaurant_id);
//                    if(rowCuisine == -1){
//                        //f(name)
//
//
//
//                        Toast.makeText(EditCusineActivity.this, "cuisine not selected", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                    else {
//                        Toast.makeText(EditCusineActivity.this, "cuisine selected", Toast.LENGTH_SHORT).show();
//                       // Toast.makeText(EditCusineActivity.this, rowCuisine+"cuisine not selected", Toast.LENGTH_SHORT).show();
//                        Intent intent = getIntent();
//                        finish();
//                        startActivity(intent);
//
//                    }
//
//                }
//                if(check >= 1 && checkTwo >= 1){
//                    Toast.makeText(EditCusineActivity.this, "already exist", Toast.LENGTH_SHORT).show();
//                    check--;
//                    checkTwo--;
//                }
//


            }
        });

//        btnDeleteCuisine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(EditCusineActivity.this, "delete cuisine", Toast.LENGTH_SHORT).show();
//
//
//                int deleteRow = databaseHelper.deleteCuisine(cuisine.getText().toString());
//                if(deleteRow<0){
//                    Toast.makeText(EditCusineActivity.this, "value not deleted", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(EditCusineActivity.this, "value deleted", Toast.LENGTH_SHORT).show();
//
//                    //Intent intent=new Intent(EditCusineActivity.this,EditCusineActivity.class);
//                    Intent intent = getIntent();
//                    finish();
//                    startActivity(intent);
//                }
//
//
//
//            }
//        });


        AlertDialog alertDialog = dialougeBuilder.create();
        alertDialog.show();

    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);


    }

}
