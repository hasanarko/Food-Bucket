package com.example.mrcorbin.testing;

        import android.content.ClipData;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.icu.util.MeasureUnit;
        import android.preference.PreferenceManager;
        import android.support.annotation.NonNull;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Base64;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.Gallery;
        import android.widget.GridView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

        import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout navDrawer;
    private ActionBarDrawerToggle mToogle;
    String restEmail="";
    String restName="";
    Button map;


    private Button searchBtn,loginRequired;

    List<Restaurants> lstREcomandRest;
    List<Cusine>cusineList;
    List<TopRestaurants>topResList;
    List<NearbyRestaurants>nearByRest;
    List<NewlyOpened>newRest;

    private NavigationView navigationView;




    private TextView btnRecomandation,btnSeeAllCusines,btnTopRatedRest,btnNewlyOpened;
    DatabaseHelper databaseHelper;

   // private TextView tv_login_required;

    SharedPreferences sessionLogin;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // session

        sessionLogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sessionLogin.edit();

        // restaurants information
       // int [] res_pic = {R.drawable.img_one,R.drawable.img_two,R.drawable.img_three,R.drawable.img_four,R.drawable.img_five,R.drawable.img_six,R.drawable.img_one,R.drawable.img_two};



        String [] res_name = getResources().getStringArray(R.array.rest_name);
        String des = getResources().getString(R.string.res_des);


        // search product button
        searchBtn = findViewById(R.id.serach_button_id);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


          databaseHelper = new DatabaseHelper(this);
          SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // recomandation for foodies


        lstREcomandRest = new ArrayList<>();


//
//        for(int i=0 ;i<res_name.length ; i++){
//            lstREcomandRest.add(new Restaurants(i,res_name[i],"email@gmail.com","123","manager",123455,"salt lake","new",res_pic[i],0));
//        }



       Cursor cursorRecommand = databaseHelper.getRestData();


       // Toast.makeText(this, ""+cursorRecommand.getCount(), Toast.LENGTH_SHORT).show();

        int c=0;
        while(cursorRecommand.moveToNext() && c<5){

            int id = cursorRecommand.getInt(0);
            String name = cursorRecommand.getString(1);
            String email = cursorRecommand.getString(2);
            String password = cursorRecommand.getString(3);
            String ownerType = cursorRecommand.getString(4);
            int phone = cursorRecommand.getInt(5);
            String address = cursorRecommand.getString(6);
            String accountStatus = cursorRecommand.getString(7);
            byte [] img = cursorRecommand.getBlob(8);
            double restaurantRating = cursorRecommand.getDouble(9);
            String restStatus = cursorRecommand.getString(10);
            String openingTime = cursorRecommand.getString(11);
            String closingTime = cursorRecommand.getString(12);
            //double foodPrice=0.0;


                lstREcomandRest.add(new Restaurants(id,name,email,password,ownerType,phone,address,accountStatus,img,restaurantRating,restStatus,openingTime,closingTime));
                c++;


        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myvr = (RecyclerView) findViewById(R.id.recomandation_id);
        RecomandationViewAdapter myAdapter = new RecomandationViewAdapter(this,lstREcomandRest);
        myvr.setLayoutManager(layoutManager);
        // myvr.setLayoutManager(new GridLayoutManager(this,1));
        myvr.setAdapter(myAdapter);



        //see all button for recomandation
        btnRecomandation = findViewById(R.id.see_all_recomandation);
        btnRecomandation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RecomandationActivity.class);
                startActivity(intent);
            }
        });




        // cuisines for customers
        cusineList = new ArrayList<>();

        String [] cusine_name = getResources().getStringArray(R.array.cuisine_name);
        int [] cusine_image = {R.drawable.american,R.drawable.appitizer,R.drawable.bakery,R.drawable.bangali_food,
                R.drawable.bangladeshi_food,R.drawable.bbq,R.drawable.beef,
                R.drawable.bevarage

//,R.drawable.biriany,R.drawable.burger,R.drawable.cafe,
//                R.drawable.chinese,R.drawable.chicken,R.drawable.chowmin,R.drawable.continental,
//
//                R.drawable.desert,R.drawable.dosa,R.drawable.fast_food,R.drawable.grill,
//                R.drawable.ice_cream,R.drawable.indian,R.drawable.italian,R.drawable.japanese,
//                R.drawable.kebab,R.drawable.koream,R.drawable.maxican,R.drawable.misti,
//                R.drawable.mughal,R.drawable.noodles,R.drawable.oriental,R.drawable.pizza,
//                R.drawable.roll,R.drawable.salad,R.drawable.sea_food,R.drawable.snacks,
//                R.drawable.soup,R.drawable.tea,R.drawable.thai
    };






        for(int i = 0 ; i<cusine_name.length;i++){
            cusineList.add(new Cusine(cusine_name[i],cusine_image[i]));
        }

        btnSeeAllCusines = findViewById(R.id.see_all_cusines);
        btnSeeAllCusines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShowAllCuisines.class);
                startActivity(intent);
    }
});


        LinearLayoutManager layoutManagerCusine = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView cusineRec = (RecyclerView) findViewById(R.id.cusine_recycleview_id);
        cusineAdapter myAdapterCusine = new cusineAdapter(this,cusineList);
        cusineRec.setLayoutManager(layoutManagerCusine);
        // myvr.setLayoutManager(new GridLayoutManager(this,1));
        cusineRec.setAdapter(myAdapterCusine);



        int t=0;
        // top rated restaurants
        topResList = new ArrayList<>();
        Cursor cursorTopRated = databaseHelper.getRestDataForTop();
        while(cursorTopRated.moveToNext() && t<5){
            int id = cursorTopRated.getInt(0);
            String name = cursorTopRated.getString(1);
            String email = cursorTopRated.getString(2);
            String password = cursorTopRated.getString(3);
            String ownerType = cursorTopRated.getString(4);
            int phone = cursorTopRated.getInt(5);
            String address = cursorTopRated.getString(6);
            String accountStatus = cursorTopRated.getString(7);
            byte [] img = cursorTopRated.getBlob(8);
            double restaurantRating = cursorTopRated.getDouble(9);
            String restStatus = cursorTopRated.getString(10);
            String openingTime = cursorTopRated.getString(11);
            String closingTime = cursorTopRated.getString(12);

            topResList.add(new TopRestaurants(id,name,email,password,ownerType,phone,address,accountStatus,img,restaurantRating,restStatus,openingTime,closingTime));
                t++;
        }


        LinearLayoutManager layoutManagerTopRes = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView topResRec = (RecyclerView) findViewById(R.id.top_rated_rest_recyc_id);
        TopRestaurantsAdapter myAdapterTopRest = new TopRestaurantsAdapter(this,topResList);
        topResRec.setLayoutManager(layoutManagerTopRes);
        // myvr.setLayoutManager(new GridLayoutManager(this,1));
        topResRec.setAdapter(myAdapterTopRest);


        btnTopRatedRest = findViewById(R.id.see_all_top_rated_rest);
        btnTopRatedRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TopRatedRestActivity.class);
                startActivity(intent);
            }
        });


        //nearby restaurants

//        nearByRest = new ArrayList<>();
//        Cursor cursorNearby = databaseHelper.getRestData();
//        while(cursorNearby.moveToNext() && c<5){
//
//            int id = cursorNearby.getInt(0);
//            String name = cursorNearby.getString(1);
//            String email = cursorNearby.getString(2);
//            String password = cursorNearby.getString(3);
//            String ownerType = cursorNearby.getString(4);
//            int phone = cursorNearby.getInt(5);
//            String address = cursorNearby.getString(6);
//            String accountStatus = cursorNearby.getString(7);
//            byte [] img = cursorNearby.getBlob(8);
//            String restaurantRating = cursorNearby.getString(9);
//
//            nearByRest.add(new NearbyRestaurants(id,name,email,password,ownerType,phone,address,accountStatus,img,restaurantRating));
//            c++;
//        }
//
//
//        LinearLayoutManager layoutManagerNearByRes = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView nearby = (RecyclerView) findViewById(R.id.nearby_res_id);
//        NearByAdapter myAdapterNearBy = new NearByAdapter(this,nearByRest);
//        nearby.setLayoutManager(layoutManagerNearByRes);
//        // myvr.setLayoutManager(new GridLayoutManager(this,1));
//        nearby.setAdapter(myAdapterNearBy);





        //newly opened restaurants

        newRest = new ArrayList<>();
        String status = "new";

        int n=0;

        Cursor cursorNewlyOpened = databaseHelper.getNewRestData();
        while(cursorNewlyOpened.moveToNext() && n<5){

            int id = cursorNewlyOpened.getInt(0);
            String name = cursorNewlyOpened.getString(1);
            String email = cursorNewlyOpened.getString(2);
            String password = cursorNewlyOpened.getString(3);
            String ownerType = cursorNewlyOpened.getString(4);
            int phone = cursorNewlyOpened.getInt(5);
            String address = cursorNewlyOpened.getString(6);
            String accountStatus = cursorNewlyOpened.getString(7);
            byte [] img = cursorNewlyOpened.getBlob(8);
            double restaurantRating = cursorNewlyOpened.getDouble(9);
            String restStatus = cursorNewlyOpened.getString(10);
            String openingTime = cursorNewlyOpened.getString(11);
            String closingTime = cursorNewlyOpened.getString(12);

            newRest.add(new NewlyOpened(id,name,email,password,ownerType,phone,address,accountStatus,img,String.valueOf(restaurantRating),restStatus,openingTime,closingTime));
              n++;
        }



        LinearLayoutManager layoutManagerNewRes = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView newRes = (RecyclerView) findViewById(R.id.new_rest_recyc_id);
        NewlyOpenedAdapter myAdapterNewRes = new NewlyOpenedAdapter(this,newRest);
        newRes.setLayoutManager(layoutManagerNewRes);
        // myvr.setLayoutManager(new GridLayoutManager(this,1));
        newRes.setAdapter(myAdapterNewRes);

        btnNewlyOpened = findViewById(R.id.see_all_newly_opened_rest);
        btnNewlyOpened.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewlyOpenedActivity.class);
                startActivity(intent);

            }
        });




        ///Navigation Drawer

        navDrawer = findViewById(R.id.drawer);
        mToogle = new ActionBarDrawerToggle(this,navDrawer,R.string.open,R.string.close);
        navDrawer.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        ///adding listener for navigation drawer
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_menu = navigationView.getMenu();


        // ***  navigation drawer dynamically view for both foodies and restaurants owner ***
        String user_email = sessionLogin.getString("user_email", null);

        String foodies_type = sessionLogin.getString("foodies_type", null);

        if(user_email == null) {

            nav_menu.findItem(R.id.db).setVisible(false);
           // nav_menu.findItem(R.id.setting).setVisible(false);
            nav_menu.findItem(R.id.signout).setVisible(false);
        } else {

             if(foodies_type != null){
                nav_menu.findItem(R.id.db).setTitle("profile");
            }
            if(foodies_type == null){
                nav_menu.findItem(R.id.db).setTitle("restaurant dashboard");
            }
           // nav_menu.findItem(R.id.db).setTitle("profile");
            nav_menu.findItem(R.id.db).setVisible(true);
            nav_menu.findItem(R.id.username).setVisible(false);
            nav_menu.findItem(R.id.add_a_place).setVisible(false);
           // nav_menu.findItem(R.id.setting).setVisible(true);
            nav_menu.findItem(R.id.signout).setVisible(true);


        }

        navigationView.setNavigationItemSelectedListener(this);


        map = findViewById(R.id.location_finder_id);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });


    }

    //navigation toogle animation

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToogle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);

      //  return mToogle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }




    // alert dialog for exit app
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialougeBuiler;
        alertDialougeBuiler = new AlertDialog.Builder(MainActivity.this);

        alertDialougeBuiler.setIcon(R.drawable.que_icon);
        alertDialougeBuiler.setTitle(R.string.title_text);
        alertDialougeBuiler.setMessage(R.string.message_text);
        alertDialougeBuiler.setCancelable(false);
        alertDialougeBuiler.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                       finish();
            }
        });

        alertDialougeBuiler.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog alertDialog = alertDialougeBuiler.create();
        alertDialog.show();


    }


     ///navigation drawer all components


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        String foodie = sessionLogin.getString("foodie", null);
        String rest_owner = sessionLogin.getString("rest_owner", null);

        if(id == R.id.username){
            if(foodie==null ){
                Intent intent = new Intent(MainActivity.this,SignActivity.class);
                startActivity(intent);
           }else if (rest_owner == null){
                Intent intent = new Intent(MainActivity.this,SignActivity.class);
                startActivity(intent);
            }


        }

        if(id == R.id.db){


            if(foodie != null) {

                Intent intent = new Intent(MainActivity.this,DashBoardActivity.class);
                intent.putExtra("foodie_id", sessionLogin.getInt("foodies_id", 0));
                startActivity(intent);
            } else if(rest_owner != null) {

                Intent intent = new Intent(MainActivity.this,RestaurantDashboardActivity.class);

                String previouslyEncodedImage = sessionLogin.getString("rest_image", null);
                byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);

                intent.putExtra("id", Integer.parseInt(sessionLogin.getString("rest_id", null)));

                startActivity(intent);
            }

        }



        if(id == R.id.add_a_place){
            //Toast.makeText(this, "this is setting", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        }


        if(id == R.id.signout){
            editor.clear();
            editor.commit();
            Toast.makeText(this, "Logout succesfully", Toast.LENGTH_SHORT).show();
            Intent intent =getIntent();
            finish();
            startActivity(intent);
        }


        return false;
    }


    // refresh with backpress
    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
